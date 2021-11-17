package com.small.common.excel;


import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ExcelParse
 * @Description: Excel 解析
 * @Author xaq
 * @Date 2019/10/14
 * @Version V1.0
 **/
public class ExcelParse {
    /**
     * 将 Excel 解析为 JavaBean 对象
     *
     * @param file
     * @param clazz
     * @param beContainTitle 是否包含表头
     * @param rowIndex       从第几行开始解析，第一行为 0，依次类推
     * @return
     * @since 2019-04-23 16:07
     */
    public static <T> List<T> parse(MultipartFile file, Class<T> clazz, boolean beContainTitle, int rowIndex) {
        List<List<String>> result = parse(file, beContainTitle, rowIndex);
        return result.stream()
                .map(x -> setField(clazz, x))
                .collect(Collectors.toList());
    }

    /**
     * 为对象的每一个属性赋值
     *
     */
    private static <T> T setField(Class<T> clazz, List<String> rowDatas) {
        try {
            T obj = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
                int index = excelProperty.index();
                String format = excelProperty.format();
                Object value = getFieldValue(field, rowDatas.get(index), format);
                field.setAccessible(true);
                field.set(obj, value);
            }
            return obj;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 将 Excel 解析为 List 集合
     *
     */
    public static List<List<String>> parse(MultipartFile file, boolean beContainTitle, int rowIndex) {
        Sheet sheet = ExcelImport.parseFile(file);
        int rowCount = sheet.getPhysicalNumberOfRows(); // 总行数
        int cellCount = sheet.getRow(0).getPhysicalNumberOfCells(); // 总列数
        int i = beContainTitle ? rowIndex + 1 : rowIndex; // 是否包含表头

        List<List<String>> result = new ArrayList<List<String>>();
        for (; i < rowCount; i++) {
            List<String> cellResult = new ArrayList<>();
            for (int j = 0; j < cellCount; j++) {
                cellResult.add(ExcelImport.getCellValue(sheet, i, j));
            }
            result.add(cellResult);
        }
        return result;
    }

    /**
     * 根据 Java 对象的属性的类型，将 Excel 中的值解析为相对应的类型
     *
     */
    @SuppressWarnings("rawtypes")
    private static Object getFieldValue(Field field, String value, String pattern) {
        Class typeClass = field.getType();
        Object val = null;
        if (typeClass == Integer.class) {
            val= Arrays.asList(ExcelIntegerEnum.values()).stream()
                    .filter(x -> x.getName().equals(value.toUpperCase()))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new)
                    .getValue();
        } else if (typeClass == Long.class) {
            val = Long.valueOf(value);
        } else if (typeClass == Float.class) {
            val = Float.valueOf(value);
        } else if (typeClass == Double.class) {
            val = Double.valueOf(value);
        } else if (typeClass == Date.class) {
            val = ExcelImport.getDate(value, pattern);
        } else if (typeClass == Short.class) {
            val = Short.valueOf(value);
        } else if (typeClass == Character.class) {
            val = Character.valueOf(value.charAt(0));
        } else if (typeClass == Boolean.class) {
            val = Arrays.asList(ExcelBooleanEnum.values()).stream()
                    .filter(x -> x.getName().equals(value.toUpperCase()))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new)
                    .getValue();
        } else {
            val=value;
        }
        return val;

    }
}
