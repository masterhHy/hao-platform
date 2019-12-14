package com.hao.system.finance.data.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
@Slf4j
public class SeleniumConfig {

    public final static String CHROME_DRIVER_PATH ;

    static {
        String path ="";
        try{
            ClassPathResource classPathResource = new ClassPathResource("third-part/config.ini");
            File config = classPathResource.getFile();
            classPathResource = new ClassPathResource("third-part/chromedriver.exe");
            File driver = classPathResource.getFile();
            System.setProperty("selenuim_config",config.getAbsolutePath());
            path=driver.getAbsolutePath();

        }catch (Exception e){
            log.error(">>>>>>>>>>>>>>>>>>>>>>初始化 selenium 配置路径失败，后续使用到selenium 组件会引起错误，请重视!!!!");
        }finally {
            CHROME_DRIVER_PATH=path;
            log.info(">>>>>>>>>>>>>>>>>selenuim_config绝对路径{}",System.getProperty("selenuim_config"));
            log.info(">>>>>>>>>>>>>>>>>chrome_driver 绝对路径{}",path);

        }
    }

}
