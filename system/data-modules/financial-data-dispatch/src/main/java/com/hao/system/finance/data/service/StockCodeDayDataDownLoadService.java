package com.hao.system.finance.data.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hao.common.utils.DateHelper;
import com.hao.entity.finance.StockCode;
import com.hao.entity.finance.StockCodeDayData;
import com.hao.system.finance.data.mapper.StockCodeMapper;
import com.hao.system.finance.data.service.db.StockCodeDayDataPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StockCodeDayDataDownLoadService implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    @Autowired
    private StockCodeDayDataPipeline stockCodeDayDataPipeline;
    @Resource
    private StockCodeMapper stockCodeMapper;

    public  void downLoad(){
        //http://q.stock.sohu.com/cn/000600/lshq.shtml
        List<StockCode> stockCodes = stockCodeMapper.selectAll();
        List<String> urls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        Date st = DateHelper.add(now, Calendar.YEAR, -5);
        String endTime  = sdf.format(now);
        String stTime =sdf.format(st);
        for (StockCode item:stockCodes){
            String url ="http://q.stock.sohu.com/hisHq?code=cn_"+item.getCode() +
                    "&start="+stTime+"&end="+endTime+"&" +
                    "stat=1&order=D&period=d&codeid="+item.getId();
            urls.add(url);
        }

        String[] arr = new String[urls.size()];
        Spider.create(this).addUrl(urls.toArray(arr))
                .addPipeline(stockCodeDayDataPipeline)
                .run();

    }

    @Override
    public void process(Page page) {
        String data = page.getJson().get();
        JSONArray jsonarr = (JSONArray) JSONArray.parse(data);
        JSONObject json = (JSONObject) jsonarr.get(0);
        JSONArray arr = (JSONArray) json.get("hq");
        List<StockCodeDayData> res = new ArrayList<>();
        String url = page.getRequest().getUrl();
        String code =url.split("&codeid=")[1];
        for (Object objList : arr) {
            List<String> list = (List<String>)objList;
            StockCodeDayData scdd = new StockCodeDayData();
            scdd.setAmount(dealDouble(list.get(8)).divide(new BigDecimal(10000)));
            scdd.setChangeHand(dealDouble(list.get(9)));
            scdd.setClosePrice(dealDouble(list.get(2)));
            scdd.setFinalPercent(dealDouble(list.get(4)));
            scdd.setLowPrice(dealDouble(list.get(5)));
            scdd.setNum(dealDouble(list.get(7)));
            scdd.setOpenDate(list.get(0));
            scdd.setOpenPrice(dealDouble(list.get(1)));
            scdd.setStockCodeId(Integer.parseInt(code));
            scdd.setTopPrice(dealDouble(list.get(6)));
            scdd.setCreateTime(new Date());
            res.add(scdd);
        }
        page.putField("data",res);
    }

    @Override
    public Site getSite() {
        return site;
    }



    private BigDecimal dealDouble(String s){
        s=s.replace("%", "");
        Double d =null;
        try {
            d = Double.parseDouble(s);

        } catch (Exception e) {
            d =0d;
        }
        return new BigDecimal(d);
    }
}
