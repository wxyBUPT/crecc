package com.crecc.example.task.cement;

/**
 * Created by xiyuanbupt on 2019/2/27.
 */
public class CementType{
    private static String prefix = "HT-";
    private String name;
    private int startNum;
    private int endNumm;
    private String typeNum;

    public CementType(String name, int startNum, int endNumm, String typeNum) {
        this.name = name;
        this.startNum = startNum;
        this.endNumm = endNumm;
        this.typeNum = typeNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        CementType.prefix = prefix;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getEndNumm() {
        return endNumm;
    }

    public void setEndNumm(int endNumm) {
        this.endNumm = endNumm;
    }

    public String getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(String typeNum) {
        this.typeNum = typeNum;
    }
}
