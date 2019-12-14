package com.hao.system.finance.data.job;

import com.hao.common.constant.JobNameConstant;
import com.hao.system.finance.data.common.CommonJobAbs;
import com.hao.system.finance.data.service.StockCodeDownLoadService;
import com.hao.system.finance.data.service.StockCodeIndustryDownLoadService;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@JobHandler("StockCodeHandlerJob")
public class StockCodeJobHandler extends CommonJobAbs {
    @Autowired
    private StockCodeDownLoadService stockCodeDownLoadService;
    @Autowired
    private StockCodeIndustryDownLoadService stockCodeIndustryDownLoadService;

    @Override
    public Integer process(String param) {
        stockCodeDownLoadService.downLoad();
        stockCodeIndustryDownLoadService.downLoad();
        return 1;
    }
}
