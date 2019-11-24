import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @version V1.0
 * @author: WangQingLong
 * @date: 2019/11/24 10:35
 * @description: 使用POI可以在内存中创建一个Excel文件并将数据写入到这个文件，最后通过输出流将内存中的Excel文件下载到磁盘
 *
 * XSSFWorkbook：工作簿
 * XSSFSheet：工作表
 * Row：行
 * Cell：单元格
 */
public class PoIWriteTest01 {
    @Test
    public void WriteTest(){
        try {
            //在内存中创建一个Excel文件
            XSSFWorkbook workbook = new XSSFWorkbook();
            //创建工作表，指定工作表名称
            XSSFSheet sheet = workbook.createSheet("Wang-Excel");

            //创建行，0表示第一行
            XSSFRow row = sheet.createRow(0);
            //创建单元格，0表示第一个单元格
            row.createCell(0).setCellValue("编号");
            row.createCell(1).setCellValue("名称");
            row.createCell(2).setCellValue("年龄");
            XSSFRow row1 = sheet.createRow(1);
            row1.createCell(0).setCellValue("1");
            row1.createCell(1).setCellValue("小明");
            row1.createCell(2).setCellValue("10");

            XSSFRow row2 = sheet.createRow(2);
            row2.createCell(0).setCellValue("2");
            row2.createCell(1).setCellValue("小王");
            row2.createCell(2).setCellValue("20");

             //通过输出流将workbook对象下载到磁盘
            FileOutputStream out = new FileOutputStream("F:\\Wang.xlsx");
            workbook.write(out);
            out.flush();
            out.close();
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
