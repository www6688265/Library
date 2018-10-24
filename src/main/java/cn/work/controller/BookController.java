package cn.work.controller;

import cn.work.pojo.Book;
import cn.work.pojo.BookExt;
import cn.work.pojo.Bookloc;
import cn.work.pojo.Booktype;
import cn.work.service.BookService;
import cn.work.util.FIleUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.aspectj.util.FileUtil;
import org.eclipse.jdt.internal.compiler.batch.ClasspathSourceJar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
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
        if (file != null) {
            result = uploadFile(file, result);
            if (result.get("result") != null)
                return result;
            picPath = projectUploadPath + "/" + result.get("picName").toString();
            result.remove("picName");
            book.setPic(picPath);
        }
        book.setLeft(book.getTotal());
        bookService.addBook(book, loc);
        if (file != null) {
            moveFileFromTemp(picPath.substring(picPath.lastIndexOf("/")));
        }
        result.put("result", "success");
        return result;
    }

    @RequestMapping(value = "getAllBooks")
    @ResponseBody
    public Map getAllBooks() {
        Map<String, Object> result = new HashMap<>();
        List<BookExt> bookList = bookService.getAllBooks();
        result.put("data", bookList);
        return result;
    }

    @RequestMapping(value = "updateBook")
    @ResponseBody
    public Map updateBook(Book book, Bookloc loc, MultipartFile upload) throws IOException {
        Map<String, Object> result = new HashMap<>();
        if (upload != null) {
            uploadFile(upload, result);
            return result;
        } else {
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
            return new PageInfo(list);
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
