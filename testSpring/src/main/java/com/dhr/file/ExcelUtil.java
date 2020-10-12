package com.dhr.file;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author duanhaoran
 * @since 2020/1/7 1:50 PM
 */
public class ExcelUtil {
    /**
     * @param in           ：承载着Excel的输入流
     * @param entityClass  ：List中对象的类型（Excel中的每一行都要转化为该类型的对象）
     * @param fieldMap     ：Excel中的中文列头和类的英文属性的对应关系Map
     * @param uniqueFields ：指定业务主键组合（即复合主键），这些列的组合不能重复
     * @return ：List
     * @throws RuntimeException
     * @MethodName : excelToList
     * @Description : 将Excel转化为List
     */
    public static <T> List<T> excelToList(
            InputStream in,
            String sheetName,
            Class<T> entityClass,
            LinkedHashMap<String, String> fieldMap,
            String[] uniqueFields
    ) throws RuntimeException {

        //定义要返回的list
        List<T> resultList = new ArrayList<T>();

        try {

            //根据Excel数据源创建WorkBook
            Workbook wb = Workbook.getWorkbook(in);
            //获取工作表
            Sheet sheet = wb.getSheet(sheetName);

            //获取工作表的有效行数
            int realRows = 0;
            for (int i = 0; i < sheet.getRows(); i++) {

                int nullCols = 0;
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell currentCell = sheet.getCell(j, i);
                    if (currentCell == null || "".equals(currentCell.getContents().toString())) {
                        nullCols++;
                    }
                }

                if (nullCols == sheet.getColumns()) {
                    break;
                } else {
                    realRows++;
                }
            }


            //如果Excel中没有数据则提示错误
            if (realRows <= 1) {
                throw new RuntimeException("Excel文件中没有任何数据");
            }


            Cell[] firstRow = sheet.getRow(0);

            String[] excelFieldNames = new String[firstRow.length];

            //获取Excel中的列名
            for (int i = 0; i < firstRow.length; i++) {
                excelFieldNames[i] = firstRow[i].getContents().toString().trim();
            }

            //判断需要的字段在Excel中是否都存在
            boolean isExist = true;
            List<String> excelFieldList = Arrays.asList(excelFieldNames);
            for (String cnName : fieldMap.keySet()) {
                if (!excelFieldList.contains(cnName)) {
                    isExist = false;
                    break;
                }
            }

            //如果有列名不存在，则抛出异常，提示错误
            if (!isExist) {
                throw new RuntimeException("Excel中缺少必要的字段，或字段名称有误");
            }


            //将列名和列号放入Map中,这样通过列名就可以拿到列号
            LinkedHashMap<String, Integer> colMap = new LinkedHashMap<String, Integer>();
            for (int i = 0; i < excelFieldNames.length; i++) {
                colMap.put(excelFieldNames[i], firstRow[i].getColumn());
            }


            //判断是否有重复行
            //1.获取uniqueFields指定的列
            int uniqueFieldlength = uniqueFields.length;
            if (uniqueFieldlength > 0) {
                Cell[][] uniqueCells = new Cell[uniqueFieldlength][];
                for (int i = 0; i < uniqueFieldlength; i++) {
                    int col = colMap.get(uniqueFields[i]);
                    uniqueCells[i] = sheet.getColumn(col);
                }

                //2.从指定列中寻找重复行
                for (int i = 1; i < realRows; i++) {
                    int nullCols = 0;
                    for (int j = 0; j < uniqueFieldlength; j++) {
                        String currentContent = uniqueCells[j][i].getContents();
                        Cell sameCell = sheet.findCell(currentContent,
                                uniqueCells[j][i].getColumn(),
                                uniqueCells[j][i].getRow() + 1,
                                uniqueCells[j][i].getColumn(),
                                uniqueCells[j][realRows - 1].getRow(),
                                true);
                        if (sameCell != null) {
                            nullCols++;
                        }
                    }

                    if (nullCols == uniqueFieldlength) {
                        throw new RuntimeException("Excel中有重复行，请检查");
                    }
                }
            }


            //将sheet转换为list
            for (int i = 1; i < realRows; i++) {
                //新建要转换的对象
                T entity = entityClass.newInstance();

                //给对象中的字段赋值
                for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
                    //获取中文字段名
                    String cnNormalName = entry.getKey();
                    //获取英文字段名
                    String enNormalName = entry.getValue();
                    //根据中文字段名获取列号
                    int col = colMap.get(cnNormalName);

                    //获取当前单元格中的内容
                    String content = sheet.getCell(col, i).getContents().toString().trim();

                    //给对象赋值
                    setFieldValueByName(enNormalName, content, entity);
                }

                resultList.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //如果是RuntimeException，则直接抛出
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;

                //否则将其它异常包装成RuntimeException再抛出
            } else {
                e.printStackTrace();
                throw new RuntimeException("导入Excel失败");
            }
        }
        return resultList;
    }

    /**
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @param o          对象
     * @MethodName : setFieldValueByName
     * @Description : 根据字段名给对象的字段赋值
     */
    private static void setFieldValueByName(String fieldName, Object fieldValue, Object o) throws Exception {

        Field field = getFieldByName(fieldName, o.getClass());
        if (field != null) {
            field.setAccessible(true);
            //获取字段类型
            Class<?> fieldType = field.getType();

            //根据字段类型给字段赋值
            if (String.class == fieldType) {
                field.set(o, String.valueOf(fieldValue));
            } else if ((Integer.TYPE == fieldType)
                    || (Integer.class == fieldType)) {
                field.set(o, Integer.parseInt(fieldValue.toString()));
            } else if ((Long.TYPE == fieldType)
                    || (Long.class == fieldType)) {
                field.set(o, Long.valueOf(fieldValue.toString()));
            } else if ((Float.TYPE == fieldType)
                    || (Float.class == fieldType)) {
                field.set(o, Float.valueOf(fieldValue.toString()));
            } else if ((Short.TYPE == fieldType)
                    || (Short.class == fieldType)) {
                field.set(o, Short.valueOf(fieldValue.toString()));
            } else if ((Double.TYPE == fieldType)
                    || (Double.class == fieldType)) {
                field.set(o, Double.valueOf(fieldValue.toString()));
            } else if (Character.TYPE == fieldType) {
                if ((fieldValue != null) && (fieldValue.toString().length() > 0)) {
                    field.set(o, Character
                            .valueOf(fieldValue.toString().charAt(0)));
                }
            } else if (Date.class == fieldType) {
                field.set(o, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fieldValue.toString()));
            } else {
                field.set(o, fieldValue);
            }
        } else {
            throw new RuntimeException(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
        }
    }

    /**
     * @param fieldName 字段名
     * @param clazz     包含该字段的类
     * @return 字段
     * @MethodName : getFieldByName
     * @Description : 根据字段名获取字段
     */
    private static Field getFieldByName(String fieldName, Class<?> clazz) {
        //拿到本类的所有字段
        Field[] selfFields = clazz.getDeclaredFields();

        //如果本类中存在该字段，则返回
        for (Field field : selfFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        //否则，查看父类中是否存在此字段，如果有则返回
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null && superClazz != Object.class) {
            return getFieldByName(fieldName, superClazz);
        }

        //如果本类和父类都没有，则返回空
        return null;
    }
}
