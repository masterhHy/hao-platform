package com.hao.system.finance.data.service;

import com.alibaba.fastjson.JSONObject;
import com.hao.entity.finance.FundCode;
import com.hao.entity.finance.FundHoldings;
import com.hao.entity.finance.StockCode;
import com.hao.system.finance.data.mapper.FundCodeMapper;
import com.hao.system.finance.data.mapper.FundHoldingsMapper;
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
import java.util.*;


@Service
public class FundCodeHoldingsService implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    @Autowired
    private FundCodeRankingsPipeline fundCodeRankingsPipeline;
    @Autowired
    private StockCodeMapper stockCodeMapper;
    @Autowired
    private FundCodeMapper fundCodeMapper;
    @Autowired
    private FundHoldingsMapper fundHoldingsMapper;

    public  void downLoad(){
        List<String> urls = new ArrayList<>();
        List<FundCode> codes = fundCodeMapper.selectAll();
        for (FundCode code :codes){
            String url ="http://fundf10.eastmoney.com/FundArchivesDatas.aspx?" +
                    "type=jjcc&code="+code.getCode()+"&topline=10&fundCodeId="+code.getId();

            urls.add(url);
        }

        String[] arr = new String[urls.size()];
        Spider.create(this).addUrl(urls.toArray(arr))
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
        String fundCodeId = page.getRequest().getUrl().substring(page.getRequest().getUrl().lastIndexOf("&")).replace("&fundCodeId=","");

        List<FundHoldings> holdings = new ArrayList<>();
        String data = page.getRawText().replace("var apidata=", "").trim();
        data = data.substring(0,data.length()-1).trim();
        JSONObject json = (JSONObject) JSONObject.parse(data);
        String content = json.get("content").toString();
        Html html = new Html(content);
        List<String> boxes = html.$(".box").all();


        int tableIndex=1;
        for (String str:boxes){

            Html box = new Html(str);
            List<String> trStr = box.$("table tbody").$("tr").all();
            String day = box.$("h4 font", "text").get();

            FundHoldings record = new FundHoldings();
            record.setFundCodeId(Integer.parseInt(fundCodeId));
            record.setDay(day);
            fundHoldingsMapper.delete(record);
            for (String tr :trStr){
                List<String> tdList = new Html("<html><body><table>"+tr+"</table></body></html>").$("td").all();
                if(tableIndex==1){
                    //第一个table 要把 4 5 的td删除
                    tdList.remove(3);
                    tdList.remove(3);
                }

                int i=1;
                FundHoldings fh = new FundHoldings();
                fh.setFundCodeId(Integer.parseInt(fundCodeId));
                fh.setDay(day);
                boolean success =true;
                for (String td :tdList){
                    //提取元素中文本
                    while(td.indexOf(">")!=-1&&td.indexOf("<")!=-1){
                        td = td.substring(td.indexOf(">")+1,td.lastIndexOf("<"));
                    }
                    if(i==2){
                        //股票代码
                        Integer codeId = idMap.get(td);
                        if(codeId==null){
                            success=false;
                            break;
                        }
                        fh.setStockCodeId(codeId);
                    }else if(i==5){
                        //占净值比例
                        BigDecimal percent =new BigDecimal(0);
                        try{
                            percent = new BigDecimal(td.replace("%", ""));
                        }catch (Exception e){

                        }
                        fh.setPercent(percent);
                    }else if(i==6){
                        //持股数
                        BigDecimal hodings =new BigDecimal(0);
                        try{
                            hodings = new BigDecimal(td);
                        }catch (Exception e){

                        }
                        fh.setStockNum(hodings);
                    }else if(i==7){
                        //持仓市值
                        BigDecimal money =new BigDecimal(0);
                        try{
                            money = new BigDecimal(td);
                        }catch (Exception e){

                        }
                        fh.setStockWorth(money);

                    }

                    i++;
                }
                fh.setCreateTime(new Date());
                if(success){
                    holdings.add(fh);
                }
            }
            tableIndex++;
        }

        page.putField("data",holdings);


    }

    @Override
    public Site getSite() {
        return site;
    }


}
