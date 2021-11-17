package com.small.business.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * @author mbq
 * @date 2019/6/13 10:07
 */
public class ExcelUtils {

    public static XSSFWorkbook exportExcelTemplate(List<String> tableHeaders){
        List<List<String>> lists = new ArrayList<>();
        return exportExcel(tableHeaders,lists);
    }
    
	public static byte[] getExcelTemplateByte(List<String> tableHeaders) throws IOException{
    	XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        for(String title : tableHeaders){
            int cellNum = row.getLastCellNum();
            if(cellNum<0){
            	cellNum=0;
            }
			row.createCell(cellNum).setCellValue(title);
        }
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		return os.toByteArray();
    }
	/**
	 * @param tableHeaders
	 * @param valueList
	 * @return
	 */
	public static XSSFWorkbook exportExcel(List<String> tableHeaders, List<List<String>> valueList) {
		XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row head = sheet.createRow(0);
        for(int i=0; i < tableHeaders.size(); i++){
            Cell cell = head.createCell(i);
            cell.setCellValue(tableHeaders.get(i));
        }
        for(int i = 0; i < valueList.size(); i++){
            Row row = sheet.createRow(i+1);
            for(int j=0; j < valueList.get(i).size(); j++){
                Cell cell = row.createCell(j);
                cell.setCellValue(valueList.get(i).get(j));
            }
        }
		return wb;
	}
    /**
     * 多sheet页导出
     * @param multipleMap
     * @param
     * @return
     */
    public static XSSFWorkbook exportMultipleSheetExcel(Map<String,Object> multipleMap){
        XSSFWorkbook wb = new XSSFWorkbook();
        for (Map.Entry<String, Object> entry : multipleMap.entrySet()) {
            Map<String, Object> dataObject = (Map<String, Object>) entry.getValue();
            List<String> tableHeaders = (List<String>) dataObject.get("tableHeaders");
            List<List<String>> valueList = (List<List<String>>) dataObject.get("valueList");
            Sheet sheet = wb.createSheet(entry.getKey());
            Row head = sheet.createRow(0);
            for(int i=0; i < tableHeaders.size(); i++){
                Cell cell = head.createCell(i);
                cell.setCellValue(tableHeaders.get(i));
            }
            for(int i = 0; i < valueList.size(); i++){
                Row row = sheet.createRow(i+1);
                for(int j=0; j < valueList.get(i).size(); j++){
                    Cell cell = row.createCell(j);
                    cell.setCellValue(valueList.get(i).get(j));
                }
            }
        }
        return wb;
    }
    
    public static LocalDate getCellLocalDate(Cell cell){
    	if(null == cell){
    		return null;
    	}
    	CellType cellType = cell.getCellTypeEnum();
    	LocalDate cellValue = null;
    	switch (cellType) {
		case NUMERIC:
			if(DateUtil.isCellDateFormatted(cell)){
				Date date = cell.getDateCellValue();
				if(null != date){
					cellValue= date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				}
			}
			break;
		case STRING:
			cellValue= Utils.parseDate(cell.getRichStringCellValue().getString());
			break;
		default:
			break;
		}
        return cellValue;
    }
    public static String getCellString(Cell cell){
    	if(null == cell){
    		return null;
    	}
    	DataFormatter dataFormatter = new DataFormatter();
    	dataFormatter.addFormat("###########", null);
    	CellType cellType = cell.getCellTypeEnum();
    	String cellValue = null;
    	switch (cellType) {
    	case NUMERIC:
    		cellValue = dataFormatter.formatCellValue(cell);
    		break;
    	case STRING:
    		cellValue= cell.getRichStringCellValue().getString();
    		break;
    	default:
    		break;
    	}
    	return cellValue;
    }

    public static String formattingCell(Cell cell){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
    	String cellValue = null;
    	if(null != cell){
    		switch (cell.getCellType()) {
    		case Cell.CELL_TYPE_STRING:// 字符串型
    			cellValue = cell.getRichStringCellValue().getString();
    			break;
    		case Cell.CELL_TYPE_NUMERIC:// 数值型
    			if (DateUtil.isCellDateFormatted(cell)) { // 如果是date类型则 ，获取该cell的date值
    				cellValue = sdf.format(cell.getDateCellValue());
    			} else {// 纯数字
//                        cellValue = cell.getNumericCellValue() + "";  //用这种方式会在手机号码后面加上E10,要用下面的方式
    				//DecimalFormat df = new DecimalFormat("#");
    				//cellValue = df.format(cell.getNumericCellValue());
    				String  preCellValue = String.valueOf(cell.getNumericCellValue());
    				if(preCellValue.indexOf(".") > 0){
    					preCellValue = preCellValue.replaceAll("0+?$", "");//去掉多余的0
    					preCellValue = preCellValue.replaceAll("[.]$", "");//如最后一位是.则去掉
    				}
    				cellValue =preCellValue;
    			}
    			break;
    		case Cell.CELL_TYPE_BOOLEAN:// 布尔
    			cellValue = cell.getBooleanCellValue() + "";
    			break;
    		case Cell.CELL_TYPE_FORMULA:// 公式型
    			cellValue = cell.getNumericCellValue() + "";
    			break;
    		case Cell.CELL_TYPE_BLANK:// 空值
    			cellValue = "";
    			break;
    		case Cell.CELL_TYPE_ERROR: // 故障
    			cellValue = "error";
    			break;
    		default:
    			System.out.println();
    		}
    	}
    	return cellValue;
    }

    public static Iterator<Sheet> getAllSheets(String path) throws IOException {
        File file = new File(path);
        String fileName = file.getName();// 读取上传文件(excel)的名字，含后缀后
        FileInputStream fis = null;
        // 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
        Iterator<Sheet> sheets = null;
        try {
            fis = new FileInputStream(file);
            if (fileName.endsWith("xls")) {
                sheets =  new HSSFWorkbook(fis).iterator();
            } else if (fileName.endsWith("xlsx")) {
                sheets = new XSSFWorkbook(fis).iterator();
            }
            if (sheets == null) {
                throw new Exception("excel中不含有sheet工作表");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            if(fis!=null)
            {
                fis.close();
            }
        }
        return sheets;
    }
    /**
     * 获取sheet页中的行
     * @param sheet
     * @param rowIndex
     * @return
     */
    public static Row getRow(Sheet sheet, int rowIndex){
        return sheet.getRow(rowIndex);
    }

    /**
     * 获取单元格数据
     * @param row
     * @param cellIndex
     * @return
     */
    public static Cell getCell(Row row, int cellIndex){
        return row.getCell(cellIndex);
    }
    public static boolean isRowEmpty(Row row){
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK){
                return false;
            }
        }
        return true;
    }
}
