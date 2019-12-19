package com.hao.system.finance.data.service;

import com.alibaba.fastjson.JSONObject;
import com.hao.system.finance.data.service.db.FundCodePipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;


@Service
public class FundCodeDownLoadService implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    @Autowired
    private FundCodePipeline fundCodePipeline;

    public  void downLoad(){

        String url ="https://fundapi.eastmoney.com/fundtradenew.aspx?ft=gp&sc=6y&st=desc&pi=1&pn=99999&fl=0&isab=1";

        Spider.create(this).addUrl(url)
                .addPipeline(fundCodePipeline)
                .run();

    }


    @Override
    public void process(Page page) {
        String data = page.getRawText().replace("var rankData =", "").trim();
        data = data.substring(0,data.length()-1).trim();
        JSONObject json = (JSONObject) JSONObject.parse(data);
        System.out.println(json);
        List<String> list = (List<String>) json.get("datas");
        for (String line:list) {
            String[] split = line.split("\\|");
            //基金代码|            基金名称|    类型|日期          |净值    |1日 |1周 |1月   |3月   |6月   |1年
            //1       |2                   |3       |4            |5        |6   |7    |8    |9     |10    |11
            System.out.println(split[0]);
        }
        //入库基金代码

        //入库基金收益 有则更新 没则插入
        System.out.println(list);
        page.putField("code","");
        page.putField("codeWorth","");
    }

    @Override
    public Site getSite() {
        return site;
    }


}
