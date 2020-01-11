package com.hao.system.finance.data.common;

import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class BactchInserMapper {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public<T> void bacthInsert(List<T> data)  {
        try{
            if(data!=null&&data.size()>0){
                Class<?> clazz = data.get(0).getClass();
                Field[] declaredFields = clazz.getDeclaredFields();
                String cols ="";
                String param="";
                List<String> objFieldMed = new ArrayList<>();
                for (Field field:declaredFields) {
                    Id id = field.getAnnotation(Id.class);
                    Transient tran = field.getAnnotation(Transient.class);
                    if(id!=null||tran!=null){
                        continue;
                    }
                    Column annotation = field.getAnnotation(Column.class);
                    String col="";
                    if(annotation!=null){
                        col = annotation.name();
                    }else {
                        col = field.getName();
                    }
                    cols+=(col+",");
                    param+="?,";

                    String f = field.getName();
                    String med = "get"+f.substring(0,1).toUpperCase()+f.substring(1,f.length());
                    objFieldMed.add(med);
                }
                cols = cols.substring(0,cols.length()-1);
                param = param.substring(0,param.length()-1);
                String tableName = clazz.getAnnotation(Table.class).name();
                String sql ="insert into "+tableName+" ( "+cols+" ) values ( "+param+" )";
                List<Object[]> valList = new ArrayList<>();
                for (T t:data){
                    List<Object> itemVal = new ArrayList<>();
                    for (String med :objFieldMed){
                        itemVal.add(clazz.getMethod(med).invoke(t));
                    }
                    valList.add(itemVal.toArray());
                }
                jdbcTemplate.batchUpdate(sql, valList);
                log.info(">>>>>>>>>>>>>>>>>成功入库{}条数据",valList.size());
                XxlJobLogger.log(">>>>>>>>>>>>>>>>>成功入库{}条数据",valList.size());
            }
        }catch (Exception e){
            log.info("",e);
            XxlJobLogger.log(e);
            throw new RuntimeException(e.getMessage());
        }

    }


}
