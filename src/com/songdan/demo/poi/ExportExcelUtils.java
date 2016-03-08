package com.songdan.demo.poi;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * POI　export demo
 * @author SONGDAN
 *
 */
public final class ExportExcelUtils {
    public static <T> void exportExcel(String title,String[] header,Collection<T> dataset,OutputStream out,String pattern,Map<String,String> mapping){
        //声明一个excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        //声明一个sheet
        HSSFSheet sheet = workbook.createSheet(title);
        //设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        //生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        setHeadStyle(style);
        //生成一个字体
        HSSFFont font = workbook.createFont();
        setHeadFont(font);
        //应用字体
        style.setFont(font);
        //生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        setNormalStyle(style2);
        HSSFFont font2 = workbook.createFont();
        setNormalFont(font2);
        style2.setFont(font2);
        //-------------------------------------------------------------
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        //生成表格标题行
        HSSFRow headrow = sheet.createRow(0);
        for (int i = 0; i < header.length; i++) {
            HSSFCell cell = headrow.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(header[i]);
            cell.setCellValue(text);
        }
        
        //遍历数据集合生成数据行
        Iterator<T> iterator = dataset.iterator();
        int index = 0;
        HSSFRow row = null;
        while (iterator.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) iterator.next();
            for (int i = 0; i < header.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);
                String name = header[i];
                String field = mapping.get(name);
                StringBuilder getMethodName = 
                        new StringBuilder("get").append(field.substring(0, 1).toUpperCase()).append(field.substring(1));
                Class dataClass = t.getClass();
                try {
                    Method method;
                    method = dataClass.getMethod(getMethodName.toString());
                    Object value = method.invoke(t);
                    //判断值的类型后进行强传 
                    String valueText = null;
                    if(value==null){
                        valueText=""; 
                    }else{
                        if(value instanceof Date){
                            Date date = (Date) value;
                            valueText = format.format(date);
                        }else{
                            valueText = String.valueOf(value);
                        }
                        if(valueText!=null){
                            Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
                            Matcher matcher = p.matcher(valueText);
                            if(matcher.matches()){
                                //是数字当作double处理
                                cell.setCellValue(new BigDecimal(valueText).setScale(1, RoundingMode.HALF_UP).toString());
                            }else{
                                HSSFRichTextString richString = new HSSFRichTextString(valueText);
                                HSSFFont font3 = workbook.createFont();
                                font3.setColor(HSSFColor.BLUE.index);
                                richString.applyFont(font3);
                                cell.setCellValue(richString);
                            }
                        }
                    }
                }
                catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                catch (SecurityException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        try{
            workbook.write(out);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void setNormalFont(HSSFFont font2) {
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
    }

    private static void setNormalStyle(HSSFCellStyle style2) {
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    }

    private static void setHeadFont(HSSFFont font) {
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    }

    private static void setHeadStyle(HSSFCellStyle style) {
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    }
}
