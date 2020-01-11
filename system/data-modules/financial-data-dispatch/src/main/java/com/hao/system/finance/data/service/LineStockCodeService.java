package com.hao.system.finance.data.service;

import com.hao.common.utils.DateHelper;
import com.hao.common.utils.LinearRegression;
import com.hao.common.utils.vo.Linear;
import com.hao.entity.finance.RiseParams;
import com.hao.entity.finance.RiseStockCode;
import com.hao.entity.finance.StockCode;
import com.hao.entity.finance.StockCodeDayData;
import com.hao.system.finance.data.mapper.RiseParamsMapper;
import com.hao.system.finance.data.mapper.RiseStockCodeMapper;
import com.hao.system.finance.data.mapper.StockCodeDayDataMapper;
import com.hao.system.finance.data.mapper.StockCodeMapper;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class LineStockCodeService {
    @Autowired
    private StockCodeDayDataMapper stockCodeDayDataMapper;
    @Autowired
    private StockCodeMapper stockCodeMapper;
    @Autowired
    private RiseParamsMapper riseParamsMapper;
    @Autowired
    private RiseStockCodeMapper riseStockCodeMapper;


    public void processLineStockCode(){
        List<StockCode> stockCodes = stockCodeMapper.selectAll();
        RiseParams record = new RiseParams();
        record.setStatus((short)1);
        List<RiseParams> riseParams = riseParamsMapper.select(record);
        if(riseParams.size()==0){
            log.info("请配置fn_rise_params 参数！！在进行汇聚");
            XxlJobLogger.log("请配置fn_rise_params 参数！！在进行汇聚");
        }
        for (RiseParams params:riseParams){
            for (StockCode item:stockCodes){
                this.proccessOne(item,params);
            }

        }

    }


    private void proccessOne(StockCode stockCode ,RiseParams params){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = DateHelper.add(new Date(), Calendar.YEAR, -1);
        String stDate = sdf.format(date);
        Example record = new Example(StockCodeDayData.class);
        record.createCriteria().andEqualTo("stockCodeId",stockCode.getId())
                .andGreaterThanOrEqualTo("openDate",stDate);
        record.orderBy("openDate").asc();
        List<StockCodeDayData> stockCodeDayData = stockCodeDayDataMapper.selectByExample(record);
        Integer resDay=0;
        Linear resLine =null;
        for(int i=0;i<stockCodeDayData.size();i++){
            List<StockCodeDayData> sum = stockCodeDayData.subList(i, stockCodeDayData.size());
            if(sum.size()<params.getDays()){
                break;
            }
            Linear linear = this.getLinear(sum);
            //在拟合度大于x值情况下 取最大天数 （天数越大拟合度越差）
            if(linear.rsquare>params.getR2().doubleValue()&&linear.beta>params.getBeta().doubleValue()){
                resDay = sum.size();
                resLine=linear;
                break;
            }
        }
        if(resLine!=null){
            RiseStockCode param = new RiseStockCode();
            //删除旧数据
            param.setRiseParamId(params.getId());
            param.setStockCodeId(stockCode.getId());
            riseStockCodeMapper.delete(param);


            RiseStockCode r = new RiseStockCode();
            r.setCreateTime(new Date());
            r.setBeta(new BigDecimal(resLine.beta));
            r.setDays(resDay.shortValue());
            r.setR2(new BigDecimal(resLine.rsquare));
            r.setRiseParamId(params.getId());
            r.setStockCodeId(stockCode.getId());
            riseStockCodeMapper.insertSelective(r);
            log.info("匹配到一只股票:{}",stockCode.getName());
            XxlJobLogger.log("匹配到一只股票:{}",stockCode.getName());
        }



    }


    private Linear getLinear( List<StockCodeDayData> stockCodeDayData){
        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();
        Integer index =1;
        for(StockCodeDayData item:stockCodeDayData){
            x.add(index.doubleValue());
            y.add(item.getClosePrice().doubleValue());
            index++;
        }
        Linear linear = LinearRegression.linearRegression(y.toArray(new Double[y.size()]), x.toArray(new Double[x.size()]), 0.0);
        return linear;
    }

}
