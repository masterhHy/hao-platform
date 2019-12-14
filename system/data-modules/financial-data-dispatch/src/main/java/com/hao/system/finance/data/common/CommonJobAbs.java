package com.hao.system.finance.data.common;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class CommonJobAbs extends IJobHandler {
    protected Logger logger = LoggerFactory.getLogger(CommonJobAbs.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        Boolean success =false;
        long st = System.currentTimeMillis();
        try {
            logger.info(">>>>>>>>>>>>>>>开始执行任务，开始时间:{}",sdf.format(new Date()));
            XxlJobLogger.log(">>>>>>>>>>>>>>>开始执行任务，开始时间:{0}",sdf.format(new Date()));
            Integer res = this.process(param);
            if(res==null||res!=0){
                success=true;
            }
        }catch (Exception e){
            logger.error("",e);
            XxlJobLogger.log(e);
        }
        long et = System.currentTimeMillis();
        logger.info(">>>>>>>>>>>>>>>任务执行完毕，结束时间:{}",sdf.format(new Date()));
        logger.info(">>>>>>>>>>>>>>>任务执行完毕，总耗时:{} s",(et-st)/1000);
        XxlJobLogger.log(">>>>>>>>>>>>>>>任务执行完毕，结束时间:{0}",sdf.format(new Date()));
        XxlJobLogger.log(">>>>>>>>>>>>>>>任务执行完毕，总耗时:{0} s",(et-st)/1000);

        if(success){
            return ReturnT.SUCCESS;
        }else{
            return ReturnT.FAIL;
        }
    }

    /**
     *
     * @param param
     * @return 0 失败 1成功
     */
    public abstract Integer process(String param);
}
