package com.hao.system.finance.data.service;

import com.alibaba.fastjson.JSONObject;
import com.hao.entity.finance.StockCode;
import com.hao.system.finance.data.mapper.StockCodeMapper;
import com.hao.system.finance.data.service.db.FundCodeRankingsPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class FundCodeRankingsService implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    @Autowired
    private FundCodeRankingsPipeline fundCodeRankingsPipeline;
    @Autowired
    private StockCodeMapper stockCodeMapper;

    public  void downLoad(){

        String url ="http://fundf10.eastmoney.com/FundArchivesDatas.aspx?type=jjcc&code=003745&topline=10";

        Spider.create(this).addUrl(url)
                .addPipeline(fundCodeRankingsPipeline)
                .run();

    }


    @Override
    public void process(Page page) {
        //获取股票代码并转换成code -id map
        List<StockCode> stockCodes = stockCodeMapper.selectAll();
        Map<String,Integer> idMap = new HashMap<>();
        for (StockCode code:stockCodes){
            idMap.put(code.getCode(),code.getId());
        }

        String data = page.getRawText().replace("var apidata=", "").trim();
        data = data.substring(0,data.length()-1).trim();
        JSONObject json = (JSONObject) JSONObject.parse(data);
        String content = json.get("content").toString();
        Html html = new Html(content);
        List<String> tableStr = html.$("table").all();
        for (String str:tableStr){
            Html table = new Html(str);
            List<String> tdStr = table.$("tbody").$("tr td").all();
            int i=1;
            for (String td :tdStr){
                //提取元素中文本
                while(td.indexOf(">")!=-1&&td.indexOf("<")!=-1){
                    td = td.substring(td.indexOf(">")+1,td.lastIndexOf("<"));
                }
                if(i==2){
                    //股票代码
                    Integer codeId = idMap.get(td);

                }else if(i==7){
                    //占净值比例
                    BigDecimal percent =new BigDecimal(0);
                    try{
                        percent = new BigDecimal(td.replace("%", ""));
                    }catch (Exception e){

                    }

                }else if(i==8){
                    //持股数
                    BigDecimal hodings =new BigDecimal(0);
                    try{
                        hodings = new BigDecimal(td);
                    }catch (Exception e){

                    }
                }else if(i==8){
                    //持仓市值
                    BigDecimal money =new BigDecimal(0);
                    try{
                        money = new BigDecimal(td);
                    }catch (Exception e){

                    }


                }

                i++;
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }


}
