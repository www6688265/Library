package cn.work.controller;

import cn.work.pojo.Book;
import cn.work.pojo.BookExt;
import cn.work.pojo.Bookloc;
import cn.work.service.BookService;
import cn.work.util.FIleUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "addBook")
    @ResponseBody
    public Map addBook(Book book, Bookloc loc, MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<>();
        String tempPic = "/";
        if (!bookService.checkBookExist(book)) {
            result.put("result", "error");
            result.put("msg", "该图书已存在");
            return result;
        }
        if (file != null) {
            result = uploadFile(file, result);
            if (result.get("result") != null)
                return result;
            tempPic += result.get("picName").toString();
            result.remove("picName");
            book.setPic("upload/" + tempPic);
        }
        book.setLeft(book.getTotal());
        bookService.addBook(book, loc);
        if (file != null) {
            moveFileFromTemp(tempPic);
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
                boolean hasTemp = pic.contains("temp");
                if (hasTemp) {
                    moveFileFromTemp(pic.substring(pic.lastIndexOf("/")));
                    String path = pic.replaceFirst("temp", "upload");
                    book.setPic(path);
                }
            }
            BookExt old = bookService.getBook(book.getBookid());
            if (old.getPic() != null && !old.getPic().equals("")) {
                if (upload == null) {
                    delFile(old.getPic());
                }
            }
            bookService.updateBook(book, loc);
            BookExt returnBook = bookService.getBook(book.getBookid());
            List list = new ArrayList();
            list.add(returnBook);
            result.put("data", list);
            return result;
        }
    }

    @RequestMapping(value = "getBook")
    @ResponseBody
    public BookExt getBook(String id) {
        BookExt book = bookService.getBook(Integer.parseInt(id));
        return book;
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
            File file=new File("");
            try{
                file = ResourceUtils.getFile("classpath:static/" + pic);
            }
            catch (FileNotFoundException e){

            }
            if (file.exists())
                FileUtils.forceDelete(file);
        }
        result.put("result", "success");
        return result;
    }


    public Map<String, Object> uploadFile(MultipartFile upload, Map<String, Object> result) throws FileNotFoundException {
        String fileAllName = upload.getOriginalFilename();
        fileAllName = FIleUtil.getUniqueFileName(fileAllName);
        File targetFileDir = getTempFile();
        String targetDir = targetFileDir.getPath();
        File targetFile = new File(targetDir, fileAllName);
        String picName = fileAllName;
        String pic = "/temp/" + picName;
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

    public void moveFileFromTemp(String fileName) throws IOException {
        File file = ResourceUtils.getFile("classpath:static/temp" + fileName);
        if (file.exists()) {
            File FileDir = ResourceUtils.getFile("classpath:static/upload/1.txt");
            File uploadFileDir = FileDir.getParentFile();
            File targetFileDir = new File(uploadFileDir.getPath(), file.getName());
            FileUtil.copyFile(file, targetFileDir);
            FileUtils.forceDelete(file);
        }
    }

    public void delFile(String fileName) throws IOException {
        File file = ResourceUtils.getFile("classpath:static" + fileName);
        FileUtils.forceDelete(file);
    }

    public File getTempFile() throws FileNotFoundException {
        File targetFileDir = ResourceUtils.getFile("classpath:static/upload/1.txt");
        File staticFile = targetFileDir.getParentFile().getParentFile();
        File[] files = staticFile.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.equals("temp") && new File(dir, name).isDirectory();
            }
        });
        String tempDir = files[0].getPath();
        return new File(tempDir);
    }
}
