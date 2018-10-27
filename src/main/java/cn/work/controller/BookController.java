package cn.work.controller;

import cn.work.pojo.*;
import cn.work.service.BookService;
import cn.work.util.FIleUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.aspectj.util.FileUtil;
import org.eclipse.jdt.internal.compiler.batch.ClasspathSourceJar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


@Controller
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "getAllTypes")
    @ResponseBody
    public List<Booktype> getAllTypes() {
        return bookService.getAllTypes();
    }

    @RequestMapping(value = "addBook")
    @ResponseBody
    public Map addBook(Book book, Bookloc loc, MultipartFile file) throws IOException {
        String picPath = "";
        Map<String, Object> result = new HashMap<>();
        if (!bookService.checkBookExist(book)) {
            result.put("result", "error");
            result.put("msg", "该图书已存在");
            return result;
        }
        if (file != null && file.getSize() > 0) {
            result = uploadFile(file, result);
            if (result.get("result") != null)
                return result;
            picPath = projectUploadPath + "/" + result.get("picName").toString();
            result.remove("picName");
            book.setPic(picPath);
        }
        book.setLeft(book.getTotal());
        bookService.addBook(book, loc);
        if (file != null && file.getSize() > 0) {
            moveFileFromTemp(picPath.substring(picPath.lastIndexOf("/")));
        }
        result.put("result", "success");
        return result;
    }

    @RequestMapping(value = "getAllBooks")
    @ResponseBody
    public Map getAllBooks(@RequestBody JSONObject jsonParam) {
        Map<String, Object> result = new HashMap<>();
        List<BookExt> bookList;
        dataTablePage page = new dataTablePage(jsonParam, "Book");
        if (page.getBook() != null) {
            PageHelper.startPage(page.getStart() / page.getLength() + 1, page.getLength(), page.getOrder());
            bookList = bookService.getBooks(page.getBook());
        } else {
            PageHelper.startPage(page.getStart() / page.getLength() + 1, page.getLength(), page.getOrder());
            bookList = bookService.getAllBooks();
        }
        PageInfo pageInfo = new PageInfo<>(bookList);
        result.put("draw", page.getDraw() + 1);
        result.put("recordsTotal", pageInfo.getTotal());
        result.put("recordsFiltered", pageInfo.getTotal());
        result.put("data", bookList);
        return result;
    }

    @RequestMapping(value = "updateBook")
    @ResponseBody
    public Map updateBook(Book book, Bookloc loc, MultipartFile upload) throws IOException {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> errorMap = new HashMap<>();
        if (upload != null) {
            String name = upload.getOriginalFilename();
            System.out.println(name);
            String type = name.substring(name.lastIndexOf(".") + 1);
            long size = upload.getSize();
            if (!type.equals("jpg") && !type.equals("png") && !type.equals("gif")) {
                errorMap.put("error", "文件类型应为jpg,png,gif");
                return errorMap;
            }
            if (size > 1024000) {
                errorMap.put("error", "文件大小不能超过1M");
                return errorMap;
            }
            uploadFile(upload, result);
            return result;
        } else {
            errorMap = bookValidator(book);
            if (errorMap != null)
                return errorMap;
            String isbn = book.getIsbn();
            Book aBook = bookService.getBookByISBN(isbn);
            if (aBook != null && !aBook.getIsbn().equals(book.getIsbn())) {
                errorMap = new HashMap<>();
                errorMap.put("error", "已存在相同isbn的图书！");
                return errorMap;
            }
            String pic = book.getPic();
            if (pic != null && !pic.equals("")) {
                boolean hasTemp = pic.contains("Temp");
                if (hasTemp) {
                    String fileName = moveFileFromTemp(pic.substring(pic.lastIndexOf("/")));
                    book.setPic(fileName);
                }
            }
            bookService.updateBook(book, loc);
            BookExt returnBook = bookService.getBook(book.getBookid());
            List<BookExt> list = new ArrayList<BookExt>();
            list.add(returnBook);
            result.put("data", list);
            return result;
        }
    }

    @RequestMapping(value = "getBook")
    @ResponseBody
    public BookExt getBook(String id) {
        return bookService.getBook(Integer.parseInt(id));
    }

    @RequestMapping(value = "delBook")
    @ResponseBody
    public Map<String, Object> delBook(String id, String pic) throws IOException {
        Map<String, Object> result = new HashMap<>();
        try {
            bookService.delBook(Integer.parseInt(id));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", "error");
            result.put("msg", e.getMessage());
            return result;
        }
        if (pic != null && !pic.equals("") && !pic.equals("null")) {
            File file = new File(projectPath + pic);
            if (file.exists())
                FileUtils.forceDelete(file);
        }
        result.put("result", "success");
        return result;
    }

    @RequestMapping(value = "showBook")
    @ResponseBody
    public Map<String, Object> showBook(String id, boolean display) throws IOException {
        Map<String, Object> result = new HashMap<>();
        try {
            Book book = new Book();
            book.setBookid(Integer.parseInt(id));
            int show = display ? 1 : 0;
            book.setDisplay(show);
            bookService.updateBook(book, null);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", "error");
            result.put("msg", e.getMessage());
            return result;
        }
        result.put("result", "success");
        return result;
    }

    @RequestMapping(value = "getList")
    @ResponseBody
    public PageInfo<BookExt> getList(String bookname, String author, String type, int page, int pageSize) {
        if (StringUtil.isEmpty(bookname) && StringUtil.isEmpty(author) && StringUtil.isEmpty(type)) {
            PageHelper.startPage(page, pageSize);
            List<BookExt> list = bookService.getAllBooks();
            return new PageInfo<>(list);
        }
        if (!StringUtil.isEmpty(bookname) || !StringUtil.isEmpty(author)) {
            PageHelper.startPage(page, pageSize);
            List<BookExt> list = bookService.getBooksByNameOrAuthor(bookname, author);
            return new PageInfo<>(list);
        }
        if (!StringUtil.isEmpty(type)) {
            PageHelper.startPage(page, pageSize);
            List<BookExt> list = bookService.getBooksByType(type);
            return new PageInfo<>(list);
        } else return null;
    }


    public Map<String, Object> uploadFile(MultipartFile upload, Map<String, Object> result) throws IOException {
        String fileAllName = upload.getOriginalFilename();
        fileAllName = FIleUtil.getUniqueFileName(fileAllName);
        File targetFileDir = new File(projectPath + projectCachePath);
        String targetDir = targetFileDir.getPath();
        File targetFile = new File(targetDir, fileAllName);
        String picName = fileAllName;
        String pic = projectCachePath + "/" + picName;
        try {
            InputStream isRef = upload.getInputStream();
            FileOutputStream fosRef = new FileOutputStream(targetFile);
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
