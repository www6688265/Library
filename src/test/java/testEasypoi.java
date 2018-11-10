
import cn.work.pojo.BookExcel;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cn.work.spring.config.LibraryConfig.projectPath;

public class testEasypoi {

    @Test
    public void testEasypoi() throws IOException {
        File file = new File(projectPath + "/1.xls");
        if (file.exists()) {
            System.out.println(file.getName());
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(0);
        List<BookExcel> list = ExcelImportUtil.importExcel(file, BookExcel.class, params);
        System.out.println(list.size());
        for (BookExcel Book : list) {
            System.out.println(Book);
        }
    }

}
