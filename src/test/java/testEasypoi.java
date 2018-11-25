
import cn.work.pojo.BookExcel;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.junit.jupiter.api.Test;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.work.spring.config.LibraryConfig.projectPath;

public class testEasypoi {

    public void testEasypoi() throws IOException {
        File xlsFile = new File(projectPath + "/图书模板原形.xls");
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(xlsFile);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);
        String[] subjects = new String[]{"JAVA", "C++", "JS"};

        DataValidationHelper helper = sheet.getDataValidationHelper();

        CellRangeAddressList addressList = new CellRangeAddressList(1, 500, 2, 2);

        DataValidationConstraint constraint = helper.createExplicitListConstraint(subjects);
        DataValidation dataValidation = helper.createValidation(constraint, addressList);

        //处理Excel兼容性问题
        if (dataValidation instanceof XSSFDataValidation) {
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        } else {
            dataValidation.setSuppressDropDownArrow(false);
        }

        sheet.addValidationData(dataValidation);

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(projectPath + "/新模板.xls");
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOut != null) {
                    fileOut.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void importExcel() throws IOException {
        FileInputStream xlsFile = new FileInputStream(projectPath + "/新模板.xls");
        XSSFWorkbook wb = null;
        wb = new XSSFWorkbook(xlsFile);
        XSSFSheet sheet = wb.getSheetAt(0);
        BookExcel excel = null;
        for (Row row : sheet) {
            int rowNum = row.getRowNum();
            if (rowNum == 0) {
                continue;
            }
            row.getCell(1).setCellType(CellType.STRING);
            row.getCell(5).setCellType(CellType.STRING);
            row.getCell(6).setCellType(CellType.STRING);
            row.getCell(7).setCellType(CellType.STRING);
            row.getCell(8).setCellType(CellType.STRING);
            String bookname = row.getCell(0).getStringCellValue();
            String isbn = row.getCell(1).getStringCellValue();
            String booktype = row.getCell(2).getStringCellValue();
            String press = row.getCell(3).getStringCellValue();
            String author = row.getCell(4).getStringCellValue();
            String t_total = row.getCell(5).getStringCellValue();
            String t_floor = row.getCell(6).getStringCellValue();
            String t_bookcase = row.getCell(7).getStringCellValue();
            String t_layer = row.getCell(8).getStringCellValue();
            String brief = row.getCell(9).getStringCellValue();
            int total = Integer.parseInt(t_total);
            int floor = Integer.parseInt(t_floor);
            int bookcase = Integer.parseInt(t_bookcase);
            int layer = Integer.parseInt(t_layer);

            excel = new BookExcel(bookname, isbn, booktype, press, author,
                    total, floor, bookcase, layer, brief);
        }
        System.out.println(excel);

        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
        for (POIXMLDocumentPart dr : sheet.getRelations()) {
            if (dr instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) dr;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    XSSFPicture pic = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = pic.getPreferredSize();
                    CTMarker ctMarker = anchor.getFrom();
                    String picIndex = ctMarker.getRow() + "";
                    sheetIndexPicMap.put(picIndex, pic.getPictureData());
                }
            }
        }

        PictureData pictureData = sheetIndexPicMap.get("1");
        int type = pictureData.getPictureType();
        String typename = "";
        if (type == 5) {
            typename += ".jpeg";
        } else if (type == 6) {
            typename += ".png";
        }
        byte[] data = pictureData.getData();
        FileOutputStream fos = new FileOutputStream(projectPath + "/test" + typename);
        fos.write(data);
        fos.close();
    }
}
