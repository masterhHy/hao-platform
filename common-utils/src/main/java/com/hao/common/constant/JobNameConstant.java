package com.hao.common.constant;

public enum JobNameConstant {
    StockCodeHandlerJob("StockCodeHandlerJob","股票代码下载"),
    LineStockCodeHandlerJob("LineStockCodeHandlerJob","线性汇聚"),
    StockCodeDayDataHandlerJob("StockCodeDayDataHandlerJob","股票日数据下载"),
    FundCodeHandlerJob("FundCodeHandlerJob","基金代码下载"),
    FundCodeHoldingsHandlerJob("FundCodeHoldingsHandlerJob","基金持仓下载"),
    StockCodeInvestorHandlerJob("StockCodeInvestorHandlerJob","股票投资者下载"),


    ;

    private String jobName;
    private String msg;


    JobNameConstant(String jobName, String msg) {
        this.jobName = jobName;
        this.msg = msg;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
