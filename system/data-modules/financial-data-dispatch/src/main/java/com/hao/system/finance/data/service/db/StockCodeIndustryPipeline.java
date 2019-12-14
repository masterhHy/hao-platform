package com.hao.system.finance.data.service.db;


import com.hao.entity.finance.StockCode;
import com.hao.system.finance.data.common.BactchInserMapper;
import com.hao.system.finance.data.mapper.StockCodeMapper;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class StockCodeIndustryPipeline implements Pipeline {
    @Resource
    private StockCodeMapper stockCodeMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Object code = resultItems.get("code");
        if(code!=null){
            String s = code.toString();
            String industry = resultItems.get("industry").toString();
            String net = resultItems.get("net").toString();
            String area = resultItems.get("area").toString();

            StockCode update = new StockCode();
            update.setIndustry(industry);
            update.setUpdateTime(new Date());
            Example example = new Example(StockCode.class);
            example.createCriteria().andEqualTo("code",s);
            stockCodeMapper.updateByExampleSelective(update,example);
            XxlJobLogger.log("更新股票代码:{0},行业:{1}",s,industry);
            log.info("更新股票代码:{},行业:{}",s,industry);
        }

    }

}
