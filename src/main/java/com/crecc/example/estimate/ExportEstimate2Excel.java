package com.crecc.example.estimate;

import com.crecc.example.dbcp.SQLServerDBCPUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Bean;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.function.Consumer;

/**
 * Created by xiyuanbupt on 2019/2/11.
 * 所有专业的概算定额导出到excel
 */

public class ExportEstimate2Excel {

    static class EstimateItem{
        private String bookNum;
        private String quotaNum;
        private String quotaName;
        private String unit;
        private double basePrice;
        private double workPrice;
        private double materialPrice;
        private double machinePrice;
        private double weight;
        private String workContent;

        static public String[] getHeader(){
            String[] header = {
                    "定额书号", "定额编号", "定额名称", "单位", "工作内容"
            };
            return header;
        }
        public String[] getContent() {
            String[] content = {
                    bookNum, quotaNum, quotaName, unit, workContent
            };
            return content;
        }

        public String getBookNum() {
            return bookNum;
        }

        public void setBookNum(String bookNum) {
            this.bookNum = bookNum;
        }

        public String getQuotaNum() {
            return quotaNum;
        }

        public void setQuotaNum(String quotaNum) {
            this.quotaNum = quotaNum;
        }

        public String getQuotaName() {
            return quotaName;
        }

        public void setQuotaName(String quotaName) {
            this.quotaName = quotaName;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getBasePrice() {
            return basePrice;
        }

        public void setBasePrice(double basePrice) {
            this.basePrice = basePrice;
        }

        public double getWorkPrice() {
            return workPrice;
        }

        public void setWorkPrice(double workPrice) {
            this.workPrice = workPrice;
        }

        public double getMaterialPrice() {
            return materialPrice;
        }

        public void setMaterialPrice(double materialPrice) {
            this.materialPrice = materialPrice;
        }

        public double getMachinePrice() {
            return machinePrice;
        }

        public void setMachinePrice(double machinePrice) {
            this.machinePrice = machinePrice;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public String getWorkContent() {
            return workContent;
        }

        public void setWorkContent(String workContent) {
            this.workContent = workContent;
        }

        @Override
        public String toString() {
            return "EstimateItem{" +
                    "bookNum='" + bookNum + '\'' +
                    ", quotaNum='" + quotaNum + '\'' +
                    ", quotaName='" + quotaName + '\'' +
                    ", unit='" + unit + '\'' +
                    ", basePrice=" + basePrice +
                    ", workPrice=" + workPrice +
                    ", materialPrice=" + materialPrice +
                    ", machinePrice=" + machinePrice +
                    ", weight=" + weight +
                    ", workContent='" + workContent + '\'' +
                    '}';
        }
    }

    public void updateExcel2DB(String fileName){

    }

    public void export2Excel(String savePath){
        // web对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建表头
        String[] bookNums = {
                "LG","QG","SG","GG","DG","EG","FG","HG","JG","PG","TG","XG","ZG","BCDE2"
        };

        for(int i = 0; i<bookNums.length; i++){
            writeToSheet(bookNums[i] + "_2018", wb);
        }
        // save file
        try {
            FileOutputStream output = new FileOutputStream("/Users/xiyuanbupt/Desktop/sprng/content.xls");
            wb.write(output);
            output.flush();
            System.out.println("成功创建excel文件");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Connection connection = SQLServerDBCPUtil.getConnection();

    public void getAllNumber(){
        Set<Integer> uniqueNum = new HashSet<Integer>();
        try{
            PreparedStatement statement = connection.prepareStatement("select * from 定额库temp");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String allConsumeStr = resultSet.getString("消耗");
                String[] consumeStrs = allConsumeStr.split("@");
                for(String consumeStr:consumeStrs){
                    if(consumeStr.length() < 9){
                        System.out.println("出现错误");
                        System.out.println(resultSet.getString("定额编号"));
                        System.out.println(allConsumeStr);
                        continue;
                    }
                    String sub = consumeStr.substring(0,9);
                    Integer num = Integer.parseInt(sub);
                    uniqueNum.add(num);
                    // 查询数据库中是否有
                    /**
                    if(! isComputeCodeExist(num)){
                        System.out.println("没有记录");
                        System.out.println(num);
                        insertComputeCode(num);
                    }
                     **/
                }
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
        for(Integer i:uniqueNum){
            System.out.println("(" + i + "),");
        }
    }

    private void addComputeCode(int computeCode){
        String selectStr = "select count(*) from 材料库2018概算onlynum where 电算代号=?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectStr);
            preparedStatement.setInt(1, computeCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int count = resultSet.getInt(1);
            if(count>0){
                return;
            }
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
        String insertStr = "insert into 材料库2018概算onlynum (电算代号) values (?)";
        try{
            System.out.println(computeCode);
            System.out.println("update " + System.currentTimeMillis());
            System.out.println();

            PreparedStatement preparedStatement = connection.prepareStatement(insertStr);
            preparedStatement.setInt(1, computeCode);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }


    private void insertComputeCode(int computeCode){
        String selectStr = "SELECT 文号,电算代号,材料名称,单位,基期单价,编制期价,单重,换算系数,汇总标志,主材标志,材料运输类别,LOCK,甲供方式 from 材料库 where 电算代号=?";

        String insertStr = "INSERT INTO 材料库2018概算" +
                "(文号,电算代号,材料名称,单位,基期单价,编制期价,单重,换算系数,汇总标志,主材标志,材料运输类别,LOCK,甲供方式)" +
                "SELECT 文号,电算代号,材料名称,单位,基期单价,编制期价,单重,换算系数,汇总标志,主材标志,材料运输类别,LOCK,甲供方式 " +
                "FROM 材料单价库 WHERE 电算代号=?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    insertStr
            );
            preparedStatement.setInt(1, computeCode);
            int rowCount = preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean isComputeCodeExist(int coumputeCode){
        try{
            PreparedStatement statement = connection.prepareStatement("select count(*) from 材料库2018概算 WHERE 电算代号=?");
            statement.setInt(1, coumputeCode);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0 ? true:false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private void writeToSheet(String bookNum, HSSFWorkbook wb) {
        HSSFSheet sheet = wb.createSheet(bookNum);
        List<EstimateItem> estimateItems = new ArrayList<EstimateItem>();
        Connection connection = SQLServerDBCPUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from 定额库xml where 书号=?");
            statement.setString(1, bookNum);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                EstimateItem estimateItem = new EstimateItem();
                estimateItem.setBookNum(resultSet.getString("书号"));
                estimateItem.setQuotaNum(resultSet.getString("定额编号"));
                estimateItem.setQuotaName(resultSet.getString("定额名称"));
                estimateItem.setUnit(resultSet.getString("单位"));
                estimateItem.setWorkContent(resultSet.getString("工作内容"));
                estimateItems.add(estimateItem);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
        Collections.sort(estimateItems, new Comparator<EstimateItem>() {
            public int compare(EstimateItem o1, EstimateItem o2) {
                return Integer.valueOf(o1.getQuotaNum().split("-")[1]) - Integer.valueOf(o2.getQuotaNum().split("-")[1]);
            }
        });
        createRow0(sheet);
        for(int i = 0; i<estimateItems.size(); i++){
            HSSFRow row = sheet.createRow(i + 1);
            String[] content = estimateItems.get(i).getContent();
            for(int j = 0; j<content.length; j++){
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(content[j]);
            }
        }
    }

    // create sheet head
    public void createRow0(HSSFSheet sheet){
        HSSFRow row = sheet.createRow(0);
        String[] header = EstimateItem.getHeader();
        for(int i = 0; i<header.length; i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }
    }

    public static void main(String[] args){
        ExportEstimate2Excel exportEstimate2Excel = new ExportEstimate2Excel();
        exportEstimate2Excel.export2Excel(".");
        //exportEstimate2Excel.getAllNumber();
        try {
            exportEstimate2Excel.connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
