package com.hao.system.finance.data.service.db;


import com.hao.entity.finance.StockCode;
import com.hao.system.finance.data.common.BactchInserMapper;
import com.hao.system.finance.data.mapper.StockCodeMapper;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class StockCodePipeline implements Pipeline {
    @Resource
    private BactchInserMapper bactchInserMapper;
    @Resource
    private StockCodeMapper stockCodeMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<StockCode> codeList = resultItems.get("data");
        List<StockCode> data =new ArrayList<>();
        StockCode record = new StockCode();
        for (StockCode item :codeList){
            record.setCode(item.getCode());
            List<StockCode> select = stockCodeMapper.select(record);
            if(select.size()>0){
                StockCode stockCode = select.get(0);
                if(!stockCode.getName().equals(item.getName())){
                    stockCode.setName(item.getName());
                    stockCode.setUpdateTime(new Date());
                    stockCodeMapper.updateByPrimaryKeySelective(stockCode);
                }
            }else{
                data.add(item);
            }
        }
        log.info("本次更新股票代码数量有:{}",(codeList.size()-data.size()));
        XxlJobLogger.log("本次更新股票代码数量有:{0},新增数量:{1}",(codeList.size()-data.size()),data.size());
        bactchInserMapper.bacthInsert(data);
    }

}
