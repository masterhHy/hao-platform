package com.hao.system.finance.data.service;

import com.alibaba.fastjson.JSONObject;
import com.hao.entity.finance.StockCode;
import com.hao.system.finance.data.service.db.StockCodePipeline;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class StockCodeDownLoadService implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    @Autowired
    private StockCodePipeline stockCodePipeline;

    public  void downLoad(){

        String url ="http://63.push2.eastmoney.com/api/qt/clist/get?" +
                "cb=jQuery112404733242152488415_1575192564672&pn=1" +
                "&pz=100000&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281" +
                "&fltt=2&invt=2&fid=f3&fs=m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2,m:1+t:23" +
                "&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f128,f136,f115,f152" +
                "&_=1575192564779";

        Spider.create(this).addUrl(url)
                .addPipeline(stockCodePipeline)
                //.setDownloader(new SeleniumDownloader(SeleniumConfig.CHROME_DRIVER_PATH))
                .run();

    }


    @Override
    public void process(Page page) {
        Json j = page.getJson().removePadding("jQuery112404733242152488415_1575192564672");
        List<String> all = j.jsonPath("$.data.diff").all();
        List<StockCode> codeList = new ArrayList<>();
        for (String item :all){
            StockCode  code = new StockCode();
            JSONObject json = (JSONObject) JSONObject.parse(item);
            code.setCode((String) json.get("f12"));
            code.setName((String) json.get("f14"));
            if(StringUtils.isNotBlank(code.getCode())){
                if(code.getCode().startsWith("6")){
                    code.setType(1);
                }else if(code.getCode().startsWith("0")||code.getCode().startsWith("3")){
                    code.setType(2);
                }else {
                    code.setType(3);
                }
            }
            code.setCreateTime(new Date());
            codeList.add(code);
        }

        page.putField("data",codeList);
    }

    @Override
    public Site getSite() {
        return site;
    }


}
