package com.hao.system.finance.data.job;

import com.hao.system.finance.data.common.CommonJobAbs;
import com.hao.system.finance.data.service.LineStockCodeService;
import com.hao.system.finance.data.service.StockCodeDayDataDownLoadService;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@JobHandler("LineStockCodeHandlerJob")
public class LineStockCodeJobHandler extends CommonJobAbs {
    @Autowired
    private LineStockCodeService lineStockCodeService;

    @Override
    public Integer process(String param) {
        lineStockCodeService.processLineStockCode();
        return 1;
    }
}
