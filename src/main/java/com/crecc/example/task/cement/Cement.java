package com.crecc.example.task.cement;

/**
 * Created by xiyuanbupt on 2019/2/27.
 */
public class Cement {
    private int num;
    private String quotaName;
    private String workContet;
    private String preQuotaName;
    private String fullWorkContent;
    private String baseQuota;

    public Cement(int num, String quotaName, String workContet) {
        this.num = num;
        this.quotaName = quotaName;
        this.workContet = workContet;
    }

    @Override
    public String toString() {
        return "Cement{" +
                "num=" + num +
                ", quotaName='" + quotaName + '\'' +
                ", workContet='" + workContet + '\'' +
                ", preQuotaName='" + preQuotaName + '\'' +
                ", fullWorkContent='" + fullWorkContent + '\'' +
                ", baseQuota='" + baseQuota + '\'' +
                '}';
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getQuotaName() {
        return quotaName;
    }

    public void setQuotaName(String quotaName) {
        this.quotaName = quotaName;
    }

    public String getWorkContet() {
        return workContet;
    }

    public void setWorkContet(String workContet) {
        this.workContet = workContet;
    }

    public String getPreQuotaName() {
        return preQuotaName;
    }

    public void setPreQuotaName(String preQuotaName) {
        this.preQuotaName = preQuotaName;
    }

    public String getFullWorkContent() {
        return fullWorkContent;
    }

    public void setFullWorkContent(String fullWorkContent) {
        this.fullWorkContent = fullWorkContent;
    }

    public String getBaseQuota() {
        return baseQuota;
    }

    public void setBaseQuota(String baseQuota) {
        this.baseQuota = baseQuota;
    }
}
