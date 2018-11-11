package cn.work.controller;

import cn.work.pojo.*;
import cn.work.pojo.Error;
import cn.work.service.BookService;
import cn.work.util.FIleUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.work.spring.config.LibraryConfig.projectCachePath;
import static cn.work.spring.config.LibraryConfig.projectPath;
import static cn.work.spring.config.LibraryConfig.projectUploadPath;
import static cn.work.util.Validator.bookValidator;

/**
 * @Description: 用于操作图书的控制器
 * @Author: Aaron Ke
 */
@Controller
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    BookService bookService;

    /**
     * @Description: 得到所有图书的类型
     * @Param: 无
     * @return: 图书类型信息
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "getAllTypes")
    @ResponseBody
    public List<Booktype> getAllTypes() {
        return bookService.getAllTypes();
    }


    /**
     * @Description: 增加图书
     * @Param: book:图书的信息， file：图书封面
     * @return: 结果信息
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "addBook")
    @ResponseBody
    public Map addBook(BookExt book, MultipartFile file) throws IOException {
        String picPath = "";
        Map<String, Object> result = new HashMap<>();
        //检查图书是否存在
        if (!bookService.checkBookExist(book)) {
            result.put("result", "error");
            result.put("msg", "该图书已存在");
            return result;
        }
        //判断文件是否为空
        if (file != null && file.getSize() > 0) {
            //上传文件
            result = uploadFile(file, result);
            if (result.get("result") != null)
                return result;
            picPath = projectUploadPath + "/" + result.get("picName").toString();
            //删除返回结果中的图片名字
            result.remove("picName");
            //存放图片的路径
            book.setPic(picPath);
        }
        //增加图书信息
        bookService.addBook(book);
        //再次判断文件是否为空
        if (file != null && file.getSize() > 0) {
            //将文件从缓存目录移动到图片目录
            moveFileFromTemp(picPath.substring(picPath.lastIndexOf("/")));
        }
        result.put("result", "success");
        return result;
    }

    /**
     * @Description: 得到所有图书信息
     * @Param: page:用于接受前台的一些参数，例如：当前分页的页数，长度，搜索的内容，排序信息等
     * @return: 返回图书信息及分页信息
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "getAllBooks")
    @ResponseBody
    //需要加@RequestBody是因为传过来的对象是一个JSON对象。需要用此注解转换为实体对象
    public Map getAllBooks(@RequestBody DataTablePage page) {
        Map<String, Object> result = new HashMap<>();
        List<BookExt> bookList;
        BookExt searchBook = new BookExt();
        String searchContent = page.getSearch().getValue();
        //字符串判空
        if (!StringUtils.isEmpty(searchContent))
            //将需要搜索的内容转化为Book对象
            //下面是前端传过来的JSON对象
            //search : {"value":"{\"bookname\":null,\"author\":null,\"type\":\"B\",\"press\":null,\"isbn\":null,\"total\":null,\"left\":null,\"display\":null}","regex":false}
            searchBook = JSON.parseObject(searchContent, new TypeReference<BookExt>() {
            });
        //是否有需要搜索的内容
        if (searchBook != null) {
            PageHelper.startPage(page.getStart() / page.getLength() + 1, page.getLength(), page.getOrderList());
            //得到图书信息
            bookList = bookService.getBooks(searchBook);

            //如果没有需要所有的内容则返回所有图书信息
        } else {
            PageHelper.startPage(page.getStart() / page.getLength() + 1, page.getLength(), page.getOrderList());
            bookList = bookService.getAllBooks();
        }
        //新建一个分页对象
        PageInfo pageInfo = new PageInfo<>(bookList);
        result.put("draw", page.getDraw() + 1);
        result.put("recordsTotal", pageInfo.getTotal());
        result.put("recordsFiltered", pageInfo.getTotal());
        result.put("data", bookList);
        return result;
    }

    /**
     * @Description: 更新图书，注：这里的图片上传和图书信息更新是分开操作的。所以用if...else...分开
     * @Param: book:图书的信息， file：图书封面
     * @return: 操作结果
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "updateBook")
    @ResponseBody
    public Map updateBook(BookExt book, MultipartFile upload) throws IOException {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> errorMap = new HashMap<>();
        if (upload != null) {
            //得到文件名
            String name = upload.getOriginalFilename();
            //取得文件后缀名
            String type = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
            //得到文件的大小
            long size = upload.getSize();
            //判断文件类型
            if (!type.equals("jpg") && !type.equals("png") && !type.equals("gif")) {
                errorMap.put("error", "文件类型应为jpg,png,gif");
                return errorMap;
            }
            //判断文件大小
            if (size > 1024000) {
                errorMap.put("error", "文件大小不能超过1M");
                return errorMap;
            }
            //上传文件
            uploadFile(upload, result);
            return result;
        } else {
            //验证图书信息是否合法
            errorMap = bookValidator(book);
            if (errorMap != null)
                return errorMap;
            String isbn = book.getIsbn();
            //得到用户填写的ISBN编号并验证
            Book aBook = bookService.getBookByISBN(isbn);
            if (aBook != null && !aBook.getIsbn().equals(book.getIsbn())) {
                errorMap = new HashMap<>();
                errorMap.put("error", "已存在相同isbn的图书！");
                return errorMap;
            }
            //得到用户填写的库存信息，并判断其合法性
            if (book.getTotal() != null) {
                if (book.getLeft_num() > book.getTotal()) {
                    Error error = new Error();
                    error.addError("total", "库存不可以小于剩余数量");
                    return error.getFieldErrors();
                }
            }
            //判断是否有上传图片，所有有移动文件到图书目录
            String pic = book.getPic();
            if (pic != null && !pic.equals("")) {
                boolean hasTemp = pic.contains("Temp");
                if (hasTemp) {
                    String fileName = moveFileFromTemp(pic.substring(pic.lastIndexOf("/")));
                    book.setPic(fileName);
                }
            }
            //更新图书信息
            bookService.updateBook(book);
            //返回更新完的图书信息
            BookExt returnBook = bookService.getBook(book.getBookid());
            //这里设置成List是因为前端接受的JSON函数为List
            List<BookExt> list = new ArrayList<BookExt>();
            list.add(returnBook);
            result.put("data", list);
            return result;
        }
    }

    /**
     * @Description: 得到单本图书的信息
     * @Param: 图书的图书编号
     * @return: 图书信息
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "getBook")
    @ResponseBody
    public BookExt getBook(String id) {
        return bookService.getBook(Integer.parseInt(id));
    }

    /**
     * @Description: 上下架图书
     * @Param: id:图书的信息，display:true为上架，false为下架
     * @return: 操作结果信息
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "showBook")
    @ResponseBody
    public Map<String, Object> showBook(String id, boolean display) throws IOException {
        Map<String, Object> result = new HashMap<>();
        try {
            BookExt book = new BookExt();
            //设置需要上/下架的图书编号
            book.setBookid(Integer.parseInt(id));
            //设置需要的操作
            int show = display ? 1 : 0;
            book.setDisplay(show);
            //更新图书信息
            bookService.updateBook(book);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", "error");
            result.put("msg", e.getMessage());
            return result;
        }
        result.put("result", "success");
        return result;
    }

    /**
     * @Description: 前台得到的图书信息，和getAllBooks的区别在于是否展示已下架的书籍
     * @Param: bookname:搜索的图书名字，author：搜索的作者名字,type:搜索的图书类型，page：当前所在页数，pageSize:一共页数
     * @return:
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "getList")
    @ResponseBody
    public PageInfo<BookExt> getList(String bookname, String author, String type, int page, int pageSize) {
        if (StringUtil.isEmpty(bookname) && StringUtil.isEmpty(author) && StringUtil.isEmpty(type)) {
            PageHelper.startPage(page, pageSize);
            List<BookExt> list = bookService.getAllBooks();
            return new PageInfo<>(list);
        }
        //判断搜索的书名或作者是否为空，如果不为空则是搜索图书
        if (!StringUtil.isEmpty(bookname) || !StringUtil.isEmpty(author)) {
            PageHelper.startPage(page, pageSize);
            List<BookExt> list = bookService.getBooksByNameOrAuthor(bookname, author);
            return new PageInfo<>(list);
        }
        //判断图书类型是否为空，如果不为空则是查看图书类型所有的图书
        if (!StringUtil.isEmpty(type)) {
            PageHelper.startPage(page, pageSize);
            List<BookExt> list = bookService.getBooksByType(type);
            return new PageInfo<>(list);
        } else return null;
    }

    /**
     * @Description: 上传文件，封装方法
     * @Param: upload：上传的文件流，result：结果信息
     * @return: 结果信息
     * @Author: Aaron Ke
     */
    public Map<String, Object> uploadFile(MultipartFile upload, Map<String, Object> result) throws IOException {
        String fileAllName = upload.getOriginalFilename();
        //生成唯一的文件名，避免文件名相同
        fileAllName = FIleUtil.getUniqueFileName(fileAllName);
        //得到目标文件
        File targetFileDir = new File(projectPath + projectCachePath);
        String targetDir = targetFileDir.getPath();
        //得到新的文件
        File targetFile = new File(targetDir, fileAllName);
        String picName = fileAllName;
        String pic = projectCachePath + "/" + picName;
        try {
            InputStream isRef = upload.getInputStream();
            FileOutputStream fosRef = new FileOutputStream(targetFile);
            //将上传的文件拷贝到缓存目录
            IOUtils.copy(isRef, fosRef);
        } catch (IOException e) {
            Map<String, String> fieldErrors = new HashMap<>();
            fieldErrors.put("name", "pic");
            fieldErrors.put("status", "文件上传错误！");
            result.put("fieldErrors", fieldErrors);
            e.printStackTrace();
            return result;
        }
        Map<String, Object> files = new HashMap<>();
        Map<String, Object> file = new HashMap<>();
        Map<String, Object> img = new HashMap<>();
        Map<String, Object> uploadinfo = new HashMap<>();
        uploadinfo.put("id", pic);
        img.put("id", pic);
        img.put("filename", fileAllName);
        img.put("web_path", pic);
        file.put(pic, img);
        files.put("files", file);
        result.put("files", files);
        result.put("upload", uploadinfo);
        result.put("picName", picName);
        return result;
    }

    /**
     * @Description: 移动缓存文件到图片目录
     * @Param: fileName：所要移动的文件名
     * @return: 移动后的文件路径
     * @Author: Aaron Ke
     */
    public String moveFileFromTemp(String fileName) {
        File file = null;
        File targetFileDir = null;
        try {
            file = new File(projectPath + projectCachePath + "/" + fileName);
            if (file.exists()) {
                targetFileDir = new File(projectPath + projectUploadPath, file.getName());
                FileUtil.copyFile(file, targetFileDir);
                FileUtils.forceDelete(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return projectUploadPath + "/" + file.getName();
    }
}
