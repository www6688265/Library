package cn.work.service;

import cn.work.pojo.DataTablePage;

import java.io.InputStream;
import java.util.Map;

/**
 * @program: libraryOs
 * @description: Excel相关服务
 * @author: Aaron Ke
 * @create: 2018-11-12 22:46
 **/

public interface ExcelService {
    String createNewTemplate(String type);

    Map<String, Object> insertBookRec(InputStream inputStream);

    Map<String, Object> insertUserRec(InputStream inputStream);
}
