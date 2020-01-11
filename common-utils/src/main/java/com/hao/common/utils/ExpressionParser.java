package com.hao.common.utils;

import org.springframework.util.StringUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by 53224 on 2020/1/11.
 */
public class ExpressionParser {

    public static  Boolean booleanParser(String express){
        Boolean res =false;
        if(!StringUtils.isEmpty(express)){
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            try {
                Boolean result = (Boolean) engine.eval(express);
                res = result;
            } catch (ScriptException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Boolean res = ExpressionParser.booleanParser("x>=2".replace("x","2.1"));
        System.out.println(res);

    }
}
