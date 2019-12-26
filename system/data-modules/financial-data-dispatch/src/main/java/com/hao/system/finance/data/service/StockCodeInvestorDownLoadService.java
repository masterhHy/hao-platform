package com.hao.system.finance.data.service;

import com.alibaba.fastjson.JSONObject;
import com.hao.entity.finance.FundCode;
import com.hao.entity.finance.StockCode;
import com.hao.system.finance.data.mapper.StockCodeMapper;
import com.hao.system.finance.data.service.db.StockCodeInvestorPipeline;
import com.hao.system.finance.data.service.db.StockCodePipeline;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class StockCodeInvestorDownLoadService implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    @Autowired
    private StockCodeInvestorPipeline stockCodeInvestorPipeline;
    @Resource
    private StockCodeMapper stockCodeMapper;


    public  void downLoad(){
        List<String> urls = new ArrayList<>();
        List<StockCode> codes = stockCodeMapper.selectAll();
        for (StockCode code :codes){
            String url ="http://stockpage.10jqka.com.cn/"+code.getCode()+"/position/#holdetail?stockCodeId="+code.getId();
            urls.add(url);
            break;
        }
        String[] arr = new String[urls.size()];
        Spider.create(this).addUrl(urls.toArray(arr))
                .addPipeline(stockCodeInvestorPipeline)
                .run();

    }


    @Override
    public void process(Page page) {
        String stockCodeId = page.getRequest().getUrl().substring(page.getRequest().getUrl().lastIndexOf("?")).replace("?stockCodeId=","");
        System.out.println(page.getHtml().all());
        List<String> all = page.getHtml().$("#holdetail").all();

        System.out.println(all);
    }

    @Override
    public Site getSite() {
        return site;
    }


}
