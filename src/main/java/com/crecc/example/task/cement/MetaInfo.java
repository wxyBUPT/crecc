package com.crecc.example.task.cement;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiyuanbupt on 2019/2/27.
 */
public class MetaInfo {
    static List<CementType> allCementType = new LinkedList<CementType>();
    static String[] names = new String[]{
                "碳化环境泵送高性能混凝土",
                "氯盐环境泵送高性能混凝土",
                "化学侵蚀环境泵送高性能混凝土",
                "冻融破坏环境泵送高性能混凝土",
                "腐蚀环境泵送高性能混凝土",
                "盐类结晶破坏环境泵送高性能混凝土",
                "碳化环境非泵送高性能混凝土",
                "氯盐环境非泵送高性能混凝土",
                "化学侵蚀环境非泵送高性能混凝土",
                "冻融破坏环境非泵送高性能混凝土",
                "腐蚀环境非泵送高性能混凝土",
                "盐类结晶破坏环境非泵送高性能混凝土",
                "碳化环境水下高性能混凝土",
                "氯盐环境水下高性能混凝土",
                "化学腐蚀环境水下高性能混凝土",
                "非泵送及非喷射普通混凝土",
                "水下普通混凝土",
                "泵送普通混凝土",
                "喷射普通混凝土",
                "水泥砂浆",
            "半干硬性混凝土",
            "半干硬性混凝土",
            "半干硬性混凝土",
            "低流动性混凝土",
            "低流动性混凝土",
            "低流动性混凝土",
            "塑性混凝土",
            "塑性混凝土",
            "塑性混凝土",
            "稀混凝土",
            "稀混凝土",
            "稀混凝土",
            "泵送混凝土",
            "抗渗混凝土",
            "水泥砂浆",
            "混合砂浆",
            "抹灰砂浆",
            "特种砂浆",
            "混合砂浆"
    };
    static int[] startNums = new int[]{
            5001,5201,5401,5601,
            5801,5950,6125,6325,
            6510,6700,6875,7001,
            7175,7200,7225,1,
            109,133,201,905,
            2501,3013,3077,
            2629,3029,3093,
            2757,3045,3109,
            2885,3061,3125,
            3141,3195,3296,
            3310,3323,3377,
            3318
    };
    static int[] endNums = new int[]{
            5179, 5369, 5553, 5778,
            5909, 6103, 6304, 6494,
            6678, 6853, 6984, 7153,
            7189, 7217, 7242, 108,
            132, 200, 217, 932,
            2628, 3028, 3092,
            2756, 3044, 3108,
            2884, 3060, 3124,
            3012, 3076, 3140,
            3194, 3295, 3309,
            3317,3376,3432,
            3322
    };
    static {
        Assert.assertEquals(startNums.length,endNums.length,names.length);
    }
    static char curr = (char)('A' - 1);
    static Map<String,String> cementBase = new HashMap<String, String>();
    static Map<String,Integer> cementCurrNum = new HashMap<String, Integer>();
    static {
        for(String name:names){
            if(cementBase.containsKey(name)){
                continue;
            }
            cementBase.put(
                    name, String.valueOf((char)(++curr))
            );
            cementCurrNum.put(name, -1);
        }
    }
    static public String getNextBaseQuota(String name){
        String base = cementBase.get(name);
        Integer num = cementCurrNum.get(name) + 1;
        cementCurrNum.put(name, num);
        return base + num;
    };

    /**
    static String[] cementBases = new String[]{
                "A", "B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T"
    };
     **/

    static public void calculateCement(Cement cement){
        int len = startNums.length;
        int num = cement.getNum();
        //
        int i=0;
        for(; i<startNums.length;i++){
            if(startNums[i] <= num && num <= endNums[i]){
                break;
            }
        }
        if(i>=len){
            System.out.println();
            System.out.println(cement);
        }
        Assert.assertTrue(i<len);
        cement.setBaseQuota(getNextBaseQuota(names[i]));

        String quotaName = cement.getQuotaName();
        // 分为几种情况确定内容
        String[] removeWords = new String[]{
                "水下普通混凝土",
                "泵送普通混凝土",
                "喷射普通混凝土",
                "普通混凝土",
                "水泥砂浆",
                "半干硬性混凝土",
                "稀混凝土",
                "低流动性混凝土",
                "泵送混凝土",
                "抗渗混凝土",
                "塑性混凝土",
                "混合砂浆",
        };
        if(num>=3318 && num<=3432){
            Pattern p = Pattern.compile("[0-9]");
            Matcher matcher = p.matcher(quotaName);
            String pre = null;
            String post = null;
            if(matcher.find()){
                int indx = quotaName.indexOf(matcher.group());
                pre = quotaName.substring(0, indx);
                post = quotaName.substring(indx);
                cement.setFullWorkContent(names[i] + "(" + post + ")");
            }else {
                pre = quotaName;
                post="";
                cement.setFullWorkContent(names[i]);
            }
            cement.setPreQuotaName(pre);
            return;
        }
        // 第一层看是否在removeWord中
        String removeWord = null;
        for(String word:removeWords){
            if(quotaName.indexOf(word)>0){
                removeWord = word;
                break;
            }
        }
        if(removeWord != null){
            int startIndex = quotaName.indexOf(removeWord);
            cement.setPreQuotaName(
                    quotaName.substring(0, startIndex)
            );
            cement.setFullWorkContent(
                    names[i]+ "("+ (quotaName.length() > (startIndex + removeWord.length() + 1) ?quotaName.substring(startIndex + removeWord.length() + 1 ):"")+ ")"
            );
            return;
        }
        if(quotaName.startsWith("C")){
            cement.setPreQuotaName(
                    quotaName.substring(0, 3)
            );
            cement.setFullWorkContent(
                    names[i] + "(" + (quotaName.substring(3)) + ")"
            );
            return;
        }
        return;

    }

    static {
        char startChar = 'A';
        int len = names.length;
        for(int i = 0; i<len; i++){
            CementType cementType = new CementType(
                    names[i], startNums[i], endNums[i], String.valueOf((char)(startChar + i))
            );
            allCementType.add(cementType);
        }
    }
    public static void handleExcel(String fileName, String sheetName, String outFile) throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(fileName)));
        HSSFSheet sheet = workbook.getSheet(sheetName);

        CreationHelper creationHelper = workbook.getCreationHelper();
        Sheet writeSheet = workbook.createSheet("水泥分类");
        Row headerRow = writeSheet.createRow(0);

        headerRow.createCell(0).setCellValue("定额编号");
        headerRow.createCell(1).setCellValue("原定额名称");
        headerRow.createCell(2).setCellValue("工作内容");
        headerRow.createCell(3).setCellValue("新定额名称");
        headerRow.createCell(4).setCellValue("新工作内容");
        headerRow.createCell(5).setCellValue("分类标号");

        for(int i = 1; i<sheet.getLastRowNum();i++){
            HSSFRow row = sheet.getRow(i);
            Integer num = Integer.parseInt(row.getCell(0).getStringCellValue().split("-")[1]);
            Cement cement = new Cement(
                    num, row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue()
            );
            calculateCement(cement);
            System.out.println(cement);
            // write back
            Row writeRow = writeSheet.createRow(i);
            writeRow.createCell(0).setCellValue("HT-" + cement.getNum());
            writeRow.createCell(1).setCellValue(cement.getQuotaName());
            writeRow.createCell(2).setCellValue(cement.getWorkContet());
            writeRow.createCell(3).setCellValue(cement.getPreQuotaName());
            writeRow.createCell(4).setCellValue(cement.getFullWorkContent());
            writeRow.createCell(5).setCellValue(cement.getBaseQuota());
        }
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
    }
    public static void main(String[] args) throws Exception{
        handleExcel("/Users/xiyuanbupt/Desktop/sprng/水泥编号.xls", "Sheet1","/Users/xiyuanbupt/Desktop/sprng/水泥编号分类.xls");
    }
}
