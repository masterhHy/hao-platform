package com.hao.system.finance.data.job;

import com.hao.system.finance.data.common.CommonJobAbs;
import com.hao.system.finance.data.service.FundCodeDownLoadService;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@JobHandler("FundCodeHandlerJob")
public class FundCodeJobHandler extends CommonJobAbs {
    @Autowired
    private FundCodeDownLoadService fundCodeDownLoadService;

    @Override
    public Integer process(String param) {
        fundCodeDownLoadService.downLoad();
        return 1;
    }
}
