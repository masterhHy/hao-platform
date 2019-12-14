package test;

import com.hao.system.finance.data.FinancialDataDispatchBootstrap;
import com.hao.system.finance.data.common.BactchInserMapper;
import com.hao.system.finance.data.service.LineStockCodeService;
import com.hao.system.finance.data.service.StockCodeDayDataDownLoadService;
import com.hao.system.finance.data.service.StockCodeDownLoadService;
import com.hao.system.finance.data.service.StockCodeIndustryDownLoadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinancialDataDispatchBootstrap.class)
public class TestSplidere {
    @Autowired
    private StockCodeDownLoadService stockCodeDownLoadService;
    @Autowired
    private StockCodeIndustryDownLoadService stockCodeIndustryDownLoadService;
    @Autowired
    private StockCodeDayDataDownLoadService stockCodeDayDataDownLoadService;
    @Autowired
    private LineStockCodeService lineStockCodeService;
    @Test
    public void test01(){
        stockCodeDownLoadService.downLoad();
    }
    @Test
    public void test02(){
        stockCodeIndustryDownLoadService.downLoad();
    }
    @Test
    public void test03(){
        stockCodeDayDataDownLoadService.downLoad();
    }
    @Test
    public void test04(){
        lineStockCodeService.processLineStockCode();
    }


}
