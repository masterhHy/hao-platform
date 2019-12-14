package com.hao.system.finance.data.service.db;


import com.hao.entity.finance.StockCodeDayData;
import com.hao.system.finance.data.common.BactchInserMapper;
import com.hao.system.finance.data.mapper.StockCodeDayDataMapper;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.*;

@Component
@Slf4j
public class StockCodeDayDataPipeline implements Pipeline {
    @Resource
    private BactchInserMapper bactchInserMapper;
    @Resource
    private StockCodeDayDataMapper stockCodeDayDataMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<StockCodeDayData> codeList = resultItems.get("data");
        if(codeList.size()>0){
            List<StockCodeDayData> data =new ArrayList<>();
            StockCodeDayData record = new StockCodeDayData();
            record.setStockCodeId(codeList.get(0).getStockCodeId());
            List<StockCodeDayData> select = stockCodeDayDataMapper.select(record);
            Set<String> opendate = new HashSet<>();
            for (StockCodeDayData item :select){
                opendate.add(item.getOpenDate());
            }
            for (StockCodeDayData item :codeList){
                if(!opendate.contains(item.getOpenDate())){
                    data.add(item);
                }
            }
            log.info("股票id{},新增日数据数量:{}",record.getStockCodeId(),data.size());
            XxlJobLogger.log("股票id{0},新增日数据数量:{1}",record.getStockCodeId(),data.size());
            bactchInserMapper.bacthInsert(data);
        }

    }

}
