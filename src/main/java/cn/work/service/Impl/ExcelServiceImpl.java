package cn.work.service.Impl;

import cn.work.dao.BookMapper;
import cn.work.dao.BooktypeMapper;
import cn.work.dao.InventoryMapper;
import cn.work.pojo.*;
import cn.work.service.ExcelService;
import cn.work.util.Validator;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.work.spring.config.LibraryConfig.projectPath;
import static cn.work.spring.config.LibraryConfig.projectUploadPath;

/**
 * @program: libraryOs
 * @description: Excel相关服务实现类
 * @author: Aaron Ke
 * @create: 2018-11-12 22:47
 **/
@Service
public class ExcelServiceImpl implements ExcelService {
    private String BookOriginTemplatePath = projectPath + "/图书模板原形.xls";
    private String BookTemplatePath = projectPath + "/图书模板.xls";

    @Resource
    BooktypeMapper booktypeMapper;

    @Resource
    BookMapper bookMapper;

    @Resource
    InventoryMapper inventoryMapper;

    @Override
    public String createNewTemplate(String type) {
        File xlsFile = null;
        String path = "";
        if ("图书".equals(type)) {
            path += BookTemplatePath;
            xlsFile = new File(BookOriginTemplatePath);
            Workbook wb = null;
            try {
                wb = WorkbookFactory.create(xlsFile);
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Sheet sheet = wb.getSheetAt(0);
            List<Booktype> booktypeList = booktypeMapper.getAllTypes();
            String[] subjects = new String[booktypeList.size()];
            for (int i = 0; i < booktypeList.size(); i++) {
                subjects[i] = booktypeList.get(i).getBooktype();
            }
            DataValidationHelper helper = sheet.getDataValidationHelper();

            CellRangeAddressList addressList = new CellRangeAddressList(1, 500, 2, 2);

            DataValidationConstraint constraint = helper.createExplicitListConstraint(subjects);
            DataValidation dataValidation = helper.createValidation(constraint, addressList);
            sheet.addValidationData(dataValidation);

            //处理Excel兼容性问题
            if (dataValidation instanceof XSSFDataValidation) {
                dataValidation.setSuppressDropDownArrow(true);
                dataValidation.setShowErrorBox(true);
            } else {
                dataValidation.setSuppressDropDownArrow(false);
            }


            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream(path);
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
            return path;
        } else if ("读者".equals(type)) {
            return null;
        } else
            return null;
    }

    @Override
    public Map<String, Object> insertBookRec(InputStream inputStream) {
        ExcelResult result = new ExcelResult();
        List<Booktype> booktypeList = booktypeMapper.getAllTypes();
        Map<String, String> booktypeMap = new HashMap<>();
        int passNum = 0;
        int sumRow = 0;
        for (Booktype booktype : booktypeList) {
            booktypeMap.put(booktype.getBooktype(), booktype.getId());
        }
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            result.addError("无法打开excel文件");
            return result.getResult();
        }
        if (wb.getNumberOfSheets() == 0) {
            result.addError("没有找到Sheet");
            return result.getResult();
        }
        //得到Sheet
        XSSFSheet sheet = wb.getSheetAt(0);
        //得到图片信息
        Map<Integer, PictureData> sheetIndexPicMap = getPicData(sheet);

        //验证图片
        for (Map.Entry<Integer, PictureData> entry : sheetIndexPicMap.entrySet()) {
            int row = entry.getKey();
            PictureData picData = entry.getValue();
            int type = picData.getPictureType();
            byte[] data = picData.getData();
            if (5 != type && 6 != type && 8 != type) {
                result.addRowError(row, "图片类型只能为jpg,png,gif");
            }
            if (data.length > 1024 * 1024) {
                result.addRowError(row, "图片大小最大为1M");
            }
        }

        for (Row row : sheet) {
            int rowNum = row.getRowNum();
            if (rowNum == 0) {
                continue;
            }
            sumRow++;
            row.getCell(0).setCellType(CellType.STRING);
            row.getCell(1).setCellType(CellType.STRING);
            row.getCell(2).setCellType(CellType.STRING);
            row.getCell(3).setCellType(CellType.STRING);
            row.getCell(4).setCellType(CellType.STRING);
            row.getCell(5).setCellType(CellType.STRING);
            row.getCell(6).setCellType(CellType.STRING);
            row.getCell(7).setCellType(CellType.STRING);
            row.getCell(8).setCellType(CellType.STRING);
            row.getCell(9).setCellType(CellType.STRING);
            String bookname = row.getCell(0).getStringCellValue();
            String isbn = row.getCell(1).getStringCellValue();
            String booktype = row.getCell(2).getStringCellValue();
            String press = "" + row.getCell(3).getStringCellValue().trim();
            String author = "" + row.getCell(4).getStringCellValue().trim();
            String t_total = "" + row.getCell(5).getStringCellValue().trim();
            String t_floor = "" + row.getCell(6).getStringCellValue().trim();
            String t_bookcase = "" + row.getCell(7).getStringCellValue().trim();
            String t_layer = "" + row.getCell(8).getStringCellValue().trim();
            String brief = "" + row.getCell(9).getStringCellValue().trim();
            Integer total = null;
            Integer floor = null;
            Integer bookcase = null;
            Integer layer = null;
            try {
                total = Integer.parseInt(t_total);
            } catch (Exception e) {
                total = -1;
            }
            try {
                floor = Integer.parseInt(t_floor);
            } catch (Exception e) {
                floor = -1;
            }
            try {
                bookcase = Integer.parseInt(t_bookcase);
            } catch (Exception e) {
                bookcase = -1;
            }
            try {
                layer = Integer.parseInt(t_layer);
            } catch (Exception e) {
                layer = -1;
            }
            BookExcel excel = new BookExcel(bookname, isbn, booktype, press, author,
                    total, floor, bookcase, layer, brief);
            boolean isPass = Validator.bookValidator(excel, rowNum, result, booktypeMap);
            if (result.getErrorList().get(rowNum) != null) {
                continue;
            }
            if (isPass) {
                String pic = "";
                BookExample bookExample = new BookExample();
                bookExample.createCriteria().andIsbnEqualTo(excel.getIsbn());
                List<Book> list = bookMapper.selectByExample(bookExample);
                Book book = new Book();
                BeanUtils.copyProperties(excel, book);
                book.setDisplay(1);
                if (list.size() > 0) {
                    result.addRowError(rowNum, "该行的ISBN在数据库存在重复数据");
                } else {
                    passNum++;
                    if (sheetIndexPicMap.get(rowNum) != null) {
                        PictureData pictureData = sheetIndexPicMap.get(rowNum);
                        int type = pictureData.getPictureType();
                        String typename = "";
                        if (type == 5) {
                            typename += ".jpeg";
                        } else if (type == 6) {
                            typename += ".png";
                        } else if (type == 8) {
                            typename += ".gif";
                        }
                        byte[] data = pictureData.getData();
                        FileOutputStream fos = null;
                        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
                        String now = format.format(new Date());
                        String fileName = UUID.randomUUID() + now + typename;

                        pic += projectUploadPath + "/" + fileName;
                        try {
                            fos = new FileOutputStream(projectPath + pic);
                            fos.write(data);
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    book.setPic(pic);
                    bookMapper.insertSelective(book);
                    Inventory inventory = new Inventory(book.getBookid(), book.getTotal());
                    inventoryMapper.insert(inventory);
                }
            }
        }
        result.setPassRowNum(passNum);
        result.setSumRowNum(sumRow);
        return result.getResult();
    }

    @Override
    public Map<String, Object> insertUserRec(InputStream inputStream) {
        return null;
    }

    private Map<Integer, PictureData> getPicData(XSSFSheet sheet) {
        Map<Integer, PictureData> sheetIndexPicMap = new HashMap<Integer, PictureData>();
        for (POIXMLDocumentPart dr : sheet.getRelations()) {
            if (dr instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) dr;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    XSSFPicture pic = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = pic.getPreferredSize();
                    CTMarker ctMarker = anchor.getFrom();
                    int picIndex = ctMarker.getRow();
                    sheetIndexPicMap.put(picIndex, pic.getPictureData());
                }
            }
        }
        return sheetIndexPicMap;
    }

}
