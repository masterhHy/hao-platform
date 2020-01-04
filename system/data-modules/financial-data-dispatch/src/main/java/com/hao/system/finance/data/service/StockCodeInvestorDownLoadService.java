package com.hao.system.finance.data.service;

import com.hao.entity.finance.StockCode;
import com.hao.entity.finance.StockCodeInvestor;
import com.hao.system.finance.data.mapper.StockCodeInvestorMapper;
import com.hao.system.finance.data.mapper.StockCodeMapper;
import com.hao.system.finance.data.service.db.StockCodeInvestorPipeline;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    @Resource
    private StockCodeInvestorMapper stockCodeInvestorMapper;


    public  void downLoad(){
        List<String> urls = new ArrayList<>();
        List<StockCode> codes = stockCodeMapper.selectAll();
        for (StockCode code :codes){
            String url ="http://basic.10jqka.com.cn/"+code.getCode()+"/position.html?stockCodeId="+code.getId();
            urls.add(url);
        }
        String[] arr = new String[urls.size()];
        Spider.create(this).addUrl(urls.toArray(arr))
                .addPipeline(stockCodeInvestorPipeline)
                .run();

    }


    @Override
    public void process(Page page) {
        String stockCodeId = page.getRequest().getUrl().substring(page.getRequest().getUrl().lastIndexOf("?")).replace("?stockCodeId=","");
        StockCodeInvestor del = new StockCodeInvestor();
        del.setStockCodeId(Integer.parseInt(stockCodeId));

        List<StockCodeInvestor> intoDb = new ArrayList<>();
        //获取时间
        List<String> pubdayList = page.getHtml().$("#holdetail .fdate","text").all();
        List<String> tableIdList = page.getHtml().$("#holdetail .fdate","targ").all();
        for (int i=0;i<pubdayList.size();i++){
            String pubday = pubdayList.get(i).trim();
            del.setDay(pubday);
            stockCodeInvestorMapper.delete(del);
            String tableId = tableIdList.get(i);
            List<String> trs = page.getHtml().$("#holdetail #" + tableId+" tbody tr").all();
            for (String tr:trs){
                List<String> tdList = new Html("<html><body><table>"+tr+"</table></body></html>").$("td","text").all();
                String investorName= new Html("<html><body><table>"+tr+"</table></body></html>").$("th span","text").get().trim();

                StockCodeInvestor investor = new StockCodeInvestor();
                investor.setDay(pubday);
                investor.setStockCodeId(Integer.parseInt(stockCodeId));
                investor.setCreateTime(new Date());
                investor.setInvestorName(investorName);

                String type = tdList.get(0).trim();
                if("基金".equals(type)){
                    investor.setInvertorType((short)1);
                }else if("保险公司".equals(type)){
                    investor.setInvertorType((short)2);
                }else if("一般法人".equals(type)){
                    investor.setInvertorType((short)3);
                }else if ("信托公司".equals(type)){
                    investor.setInvertorType((short)4);
                }else if ("社保基金".equals(type)){
                    investor.setInvertorType((short)5);
                }else if("QFII".equals(type)){
                    investor.setInvertorType((short)6);
                }else if("券商".equals(type)){
                    investor.setInvertorType((short)7);
                }else if("券商集合理财".equals(type)){
                    investor.setInvertorType((short)8);
                }else if("企业年金".equals(type)){
                    investor.setInvertorType((short)9);
                }else{
                    investor.setInvertorType((short)20);
                }

                String num = tdList.get(1).trim();
                Double codeQun =0.0;
                if(num.indexOf("万")!=-1){
                    codeQun =Double.parseDouble(num.replace("万", ""));
                }else if(num.indexOf("亿")!=-1){
                    codeQun =Double.parseDouble(num.replace("亿", ""))*10000;
                }else if(num.indexOf("-")!=-1){
                    codeQun =0.0;
                }else{
                    try {
                        codeQun = Double.parseDouble(num)/10000;
                    } catch (Exception e) {

                    }
                }
                investor.setStockNum(new BigDecimal(codeQun));

                String worth = tdList.get(2).trim();
                Double stockWorth =0.0;
                if(worth.indexOf("万")!=-1){
                    stockWorth =Double.parseDouble(worth.replace("万", ""));
                }else if(worth.indexOf("亿")!=-1){
                    stockWorth =Double.parseDouble(worth.replace("亿", ""))*10000;
                }else if(worth.indexOf("-")!=-1){
                    stockWorth =0.0;
                }else{
                    try {
                        stockWorth = Double.parseDouble(worth)/10000;
                    } catch (Exception e) {
                    }
                }
                investor.setStockWorth(new BigDecimal(stockWorth));


                String percent = tdList.get(3).trim().replace("%", "");
                if(percent.indexOf("不足")!=-1){
                    percent="0.001";
                }
                investor.setPercent(new BigDecimal(percent));

                String commit = tdList.get(4).trim();
                if(StringUtils.isBlank(commit)){
                    commit = "-";
                }
                investor.setComment(commit);

                intoDb.add(investor);
            }

        }


        page.putField("data",intoDb);

    }

    @Override
    public Site getSite() {
        return site;
    }


}
