package com.hao.system.finance.data.job;

import com.hao.system.finance.data.common.CommonJobAbs;
import com.hao.system.finance.data.service.StockCodeDayDataDownLoadService;
import com.hao.system.finance.data.service.StockCodeDownLoadService;
import com.hao.system.finance.data.service.StockCodeIndustryDownLoadService;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@JobHandler("StockCodeDayDataHandlerJob")
public class StockCodeDayDataJobHandler extends CommonJobAbs {
    @Autowired
    private StockCodeDayDataDownLoadService stockCodeDayDataDownLoadService;

    @Override
    public Integer process(String param) {
        stockCodeDayDataDownLoadService.downLoad();
        return 1;
    }
}
