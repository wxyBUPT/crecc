package com.crecc.example.estimate;

import com.crecc.example.dbcp.SQLServerDBCPUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by xiyuanbupt on 2019/2/26.
 */
public class Excel2Estimate {
    class Row{
        String bookNum;
        String deNum;
        String deName;
        String workContent;

        public String getBookNum() {
            return bookNum;
        }

        public void setBookNum(String bookNum) {
            this.bookNum = bookNum;
        }

        public String getDeNum() {
            return deNum;
        }

        public void setDeNum(String deNum) {
            this.deNum = deNum;
        }

        public String getDeName() {
            return deName;
        }

        public void setDeName(String deName) {
            this.deName = deName;
        }

        public String getWorkContent() {
            return workContent;
        }

        public void setWorkContent(String workContent) {
            this.workContent = workContent;
        }

        public Row(String bookNum, String deNum, String deName, String workContent) {
            this.bookNum = bookNum;
            this.deNum = deNum;
            this.deName = deName;
            this.workContent = workContent;
        }

        @Override
        public String toString() {
            return "Row{" +
                    "bookNum='" + bookNum + '\'' +
                    ", deNum='" + deNum + '\'' +
                    ", deName='" + deName + '\'' +
                    ", workContent='" + workContent + '\'' +
                    '}';
        }
    }
    String updateTalbelSQL = "UPDATE 定额库xml SET 定额名称=?,工作内容=?,书号=? WHERE 定额编号=?";

    private void updateSheet(HSSFSheet sheet) throws Exception{
        Connection connection = SQLServerDBCPUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(updateTalbelSQL);
        for(int i = 1; i<=sheet.getLastRowNum(); i++){
            HSSFRow hssfRow = sheet.getRow(i);
            if(hssfRow == null){
                continue;
            }
            Row row = new Row(
                    hssfRow.getCell(0).getStringCellValue(),
                    hssfRow.getCell(1).getStringCellValue(),
                    hssfRow.getCell(2).getStringCellValue(),
                    hssfRow.getCell(4).getStringCellValue()
            );
            preparedStatement.setString(1, row.getDeName());
            preparedStatement.setString(2, row.getWorkContent());
            preparedStatement.setString(3, row.getBookNum());
            preparedStatement.setString(4, row.getDeNum());
            preparedStatement.addBatch();
        }
        int[] res = preparedStatement.executeBatch();
        for(int i :res){
            System.out.print(i);
        }
        connection.commit();
        connection.close();
        System.out.println("插入完成");
    }

    private void bootStart(String filePath) throws Exception{
        File excelFile = new File(filePath);
        InputStream is = new FileInputStream(excelFile);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        String[] bookNums = {
                "LG","QG","SG","GG","DG","EG","FG","HG","JG","PG","TG","XG","ZG","BCDE2"
        };
        for(String name:bookNums){
            String fullName = name + "_2018";
            System.out.println(fullName);
            HSSFSheet sheet = hssfWorkbook.getSheet(fullName);
            updateSheet(sheet);
        }
    }

    public static void main(String[] args)throws  Exception{
        Excel2Estimate estimate = new Excel2Estimate();
        estimate.bootStart("/Users/xiyuanbupt/Desktop/sprng/概算定额编号名称2019-2-21唐_2019-2-26王熙元.xls");
    }
}
