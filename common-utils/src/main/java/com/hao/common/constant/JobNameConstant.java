package com.hao.common.constant;

public enum JobNameConstant {
    StockCode("StockCodeHandlerJob","股票代码下载")


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
