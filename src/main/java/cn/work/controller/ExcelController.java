package cn.work.controller;

import cn.work.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import static cn.work.util.WebUtil.export;

/**
 * @program: libraryOs
 * @description: 处理Excel导入的控制器
 * @author: Aaron Ke
 * @create: 2018-11-12 22:28
 **/

@Controller
@RequestMapping(value = "/excel")
public class ExcelController {
    @Autowired
    ExcelService excelService;

    @RequestMapping("/getTemplate")
    public ResponseEntity<FileSystemResource> getTemplate(String type) throws UnsupportedEncodingException {
        String path = excelService.createNewTemplate(type);
        File file = new File(path);
        return export(file);
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(String type, MultipartFile file) throws IOException {
        return excelService.insertBookRec(file.getInputStream());
    }
}
