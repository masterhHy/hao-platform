package com.hao.system.finance.data.service;

import com.alibaba.fastjson.JSONObject;
import com.hao.entity.finance.StockCode;
import com.hao.system.finance.data.mapper.StockCodeMapper;
import com.hao.system.finance.data.service.db.StockCodeIndustryPipeline;
import com.hao.system.finance.data.service.db.StockCodePipeline;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.selector.Selector;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class StockCodeIndustryDownLoadService implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    @Autowired
    private StockCodeIndustryPipeline stockCodeIndustryPipeline;

    @Resource
    private StockCodeMapper stockCodeMapper;

    public  void downLoad(){
        List<StockCode> stockCodes = stockCodeMapper.selectAll();
        List<String> urls = new ArrayList<>();
        for (StockCode item:stockCodes){
            String url ="http://basic.10jqka.com.cn/"+item.getCode()+"/company.html#stockpage";
            urls.add(url);
        }

        String[] arr = new String[urls.size()];
        Spider.create(this).addUrl(urls.toArray(arr))
                .addPipeline(stockCodeIndustryPipeline)
                //.setDownloader(new SeleniumDownloader(SeleniumConfig.CHROME_DRIVER_PATH))
                .run();

    }


    @Override
    public void process(Page page) {
        List<String> allTable = page.getHtml().$("#detail .bd .m_table").all();
        if(allTable.size()>0){
            String firstTable = allTable.get(0);
            List<String> tdList = new Html(firstTable).$("tr td").all();
            String area = new Html(tdList.get(2)).$("span","text").get();
            String industry = new Html(tdList.get(4)).$("span","text").get();
            String net = new Html(tdList.get(6)).$("a","text").get();
            String code = page.getRequest().getUrl().replace("http://basic.10jqka.com.cn/","").replace("/company.html#stockpage","");
            page.putField("area",area.trim());
            page.putField("industry",industry.trim());
            page.putField("net",net.trim());
            page.putField("code",code.trim());

        }

    }

    @Override
    public Site getSite() {
        return site;
    }


}
