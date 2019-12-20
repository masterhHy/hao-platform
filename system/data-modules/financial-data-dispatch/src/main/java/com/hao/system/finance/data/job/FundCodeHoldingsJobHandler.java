package com.hao.system.finance.data.job;

import com.hao.system.finance.data.common.CommonJobAbs;
import com.hao.system.finance.data.service.FundCodeHoldingsService;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@JobHandler("FundCodeHoldingsHandlerJob")
public class FundCodeHoldingsJobHandler extends CommonJobAbs {
    @Autowired
    private FundCodeHoldingsService fundCodeHoldingsService;

    @Override
    public Integer process(String param) {
        fundCodeHoldingsService.downLoad();
        return 1;
    }
}
