package com.hao.system.finance.data.service;

import com.alibaba.fastjson.JSONObject;
import com.hao.entity.finance.FundCode;
import com.hao.entity.finance.FundRanking;
import com.hao.system.finance.data.common.BactchInserMapper;
import com.hao.system.finance.data.mapper.FundCodeMapper;
import com.hao.system.finance.data.mapper.FundRankingMapper;
import com.hao.system.finance.data.service.db.FundCodePipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.math.BigDecimal;
import java.util.*;


@Service
public class FundCodeDownLoadService implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    @Autowired
    private FundCodePipeline fundCodePipeline;
    @Autowired
    private FundCodeMapper fundCodeMapper;
    @Autowired
    private FundRankingMapper fundRankingMapper;
    @Autowired
    private BactchInserMapper bactchInserMapper;


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
        List<String> list = (List<String>) json.get("datas");
        List<FundCode> codes = new ArrayList<>();
        List<FundRanking> rankings = new ArrayList<>();
        for (String line:list) {
            String[] split = line.split("\\|");
            //基金代码|            基金名称|    类型|日期          |净值    |1日 |1周 |1月   |3月   |6月   |1年
            //1       |2                   |3       |4            |5        |6   |7    |8    |9     |10    |11
            FundCode fc=new FundCode();
            fc.setCode(split[0]);
            List<FundCode> select = fundCodeMapper.select(fc);
            fc.setName(split[1]);
            if("股票型".equals(split[2])){
                fc.setType((short)1);
            }else if("混合型".equals(split[2])){
                fc.setType((short)2);
            }
            fc.setCreateTime(new Date());
            if(select.size()==0){
                codes.add(fc);
            }
        }
        bactchInserMapper.bacthInsert(codes);

        //获取基金代码 转换 code FundCode map
        List<FundCode> dataCode = fundCodeMapper.selectAll();
        Map<String,FundCode> keyMap = new HashMap<>();
        for(FundCode item : dataCode){
            keyMap.put(item.getCode(),item);
        }

        for (String line:list) {
            String[] split = line.split("\\|");
            //基金代码|            基金名称|    类型|日期          |净值    |1日 |1周 |1月   |3月   |6月   |1年
            //1       |2                   |3       |4            |5        |6   |7    |8    |9     |10    |11
            FundRanking ranking = new FundRanking();

            ranking.setFundCodeId(keyMap.get(split[0]).getId());
            List<FundRanking> select = fundRankingMapper.select(ranking);

            BigDecimal worth = new BigDecimal(0);
            BigDecimal oneDay = new BigDecimal(0);
            BigDecimal oneWeek = new BigDecimal(0);
            BigDecimal oneMon = new BigDecimal(0);
            BigDecimal threeMon = new BigDecimal(0);
            BigDecimal sixMon = new BigDecimal(0);
            BigDecimal oneYear = new BigDecimal(0);
            try{
                worth = new BigDecimal(split[4]);
            }catch (Exception e){

            }
            try{
                oneDay = new BigDecimal(split[5]);
            }catch (Exception e){

            }
            try{
                oneWeek = new BigDecimal(split[6]);
            }catch (Exception e){

            }
            try{
                oneMon = new BigDecimal(split[7]);
            }catch (Exception e){

            }
            try{
                threeMon = new BigDecimal(split[8]);
            }catch (Exception e){

            }
            try{
                sixMon = new BigDecimal(split[9]);
            }catch (Exception e){

            }
            try{
                oneYear = new BigDecimal(split[10]);
            }catch (Exception e){

            }


            ranking.setWorth(worth);
            ranking.setOneDay(oneDay);
            ranking.setOneWeek(oneWeek);
            ranking.setOneMonth(oneMon);
            ranking.setThreeMonth(threeMon);
            ranking.setSixMonth(sixMon);
            ranking.setOneYear(oneYear);
            if(select.size()==0){
                ranking.setCreateTime(new Date());
                rankings.add(ranking);
            }else{
                ranking.setId(select.get(0).getId());
                ranking.setUpdateTime(new Date());
                fundRankingMapper.updateByPrimaryKeySelective(ranking);
            }


        }



        //入库基金收益 有则更新 没则插入
        page.putField("data",rankings);
    }

    @Override
    public Site getSite() {
        return site;
    }


}
