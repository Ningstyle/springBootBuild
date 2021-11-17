package com.small.common.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @ClassName ExcelImport
 * @Description: excel 导入
 * @Author xaq
 * @Date 2019/10/14
 * @Version V1.0
 **/
public class ExcelImport {

    private static String pattern = "yyyy-MM-dd HH:mm:ss";
    /**
     * 解析导入模板
     */
    public static Sheet parseFile(MultipartFile file) {
        InputStream in = null;
        Workbook workBook = null;
        try {
          //  String suffix = file.getName().substring(file.getName().indexOf(".") + 1);
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);

            // in = new FileInputStream(file);
            in=file.getInputStream();

            workBook = suffix.equals("xlsx") ? new XSSFWorkbook(in) : new HSSFWorkbook(in);
            return workBook.getSheetAt(0);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {}
            }
        }
    }

    /**
     * 返回指定单元格的数据
     */
    public static String getCellValue(Sheet sheet, int rowIndex, int cellIndex) {
        return getCellValue(sheet.getRow(rowIndex).getCell(cellIndex));
    }

    /**
     * 格式化解析的数据
     */
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: // 数值类型
                    if(DateUtil.isCellDateFormatted(cell)) {
                        cellValue = getDateStr(cell.getDateCellValue(), pattern);
                    } else {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cellValue = cell.getStringCellValue();
                    }
                    break;
                case Cell.CELL_TYPE_STRING: // 字符串类型
                    cellValue = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN: // 布尔类型
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA: // 公式类型
                    cellValue = String.valueOf(cell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_BLANK: // 空白类型
                    cellValue = "";
                    break;
                case Cell.CELL_TYPE_ERROR:
                    cellValue = "";
                    break;
                default:
                    cellValue = cell.toString().trim();
                    break;
            }
        }
        return cellValue.trim();
    }

    public static Date getDate(String dateStr, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String getDateStr(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }
}
