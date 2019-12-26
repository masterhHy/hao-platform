package com.hao.system.finance.data.job;

import com.hao.system.finance.data.common.CommonJobAbs;
import com.hao.system.finance.data.service.StockCodeDownLoadService;
import com.hao.system.finance.data.service.StockCodeIndustryDownLoadService;
import com.hao.system.finance.data.service.StockCodeInvestorDownLoadService;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@JobHandler("StockCodeInvestorHandlerJob")
public class StockCodeInvestorJobHandler extends CommonJobAbs {
    @Autowired
    private StockCodeInvestorDownLoadService stockCodeInvestorDownLoadService;

    @Override
    public Integer process(String param) {
        stockCodeInvestorDownLoadService.downLoad();
        return 1;
    }
}
