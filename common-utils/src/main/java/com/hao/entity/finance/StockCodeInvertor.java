package com.hao.entity.finance;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "fn_stock_code_investor")
public class StockCodeInvertor {
    /**
     * 表id
     */
    @Id
    private Integer id;

    /**
     * 股票id
     */
    @Column(name = "stock_code_id")
    private Integer stockCodeId;

    /**
     * 投资者名称
     */
    @Column(name = "investor_name")
    private String investorName;

    /**
     * 机构类型1:基金	2:保险公司3:一般法人4:信托公司5:社保基金6:QFII 7:券商	8:券商集合理财	9:企业年金20其他
     */
    @Column(name = "invertor_type")
    private Short invertorType;

    /**
     * 公布时间
     */
    private String day;

    /**
     * 持股占比
     */
    private BigDecimal percent;

    /**
     * 股票数量
     */
    @Column(name = "stock_num")
    private BigDecimal stockNum;

    /**
     * 股票总值
     */
    @Column(name = "stock_worth")
    private BigDecimal stockWorth;

    /**
     * 增持情况
     */
    private String comment;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取表id
     *
     * @return id - 表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置表id
     *
     * @param id 表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取股票id
     *
     * @return stock_code_id - 股票id
     */
    public Integer getStockCodeId() {
        return stockCodeId;
    }

    /**
     * 设置股票id
     *
     * @param stockCodeId 股票id
     */
    public void setStockCodeId(Integer stockCodeId) {
        this.stockCodeId = stockCodeId;
    }

    /**
     * 获取投资者名称
     *
     * @return investor_name - 投资者名称
     */
    public String getInvestorName() {
        return investorName;
    }

    /**
     * 设置投资者名称
     *
     * @param investorName 投资者名称
     */
    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    /**
     * 获取机构类型1:基金	2:保险公司3:一般法人4:信托公司5:社保基金6:QFII 7:券商	8:券商集合理财	9:企业年金20其他
     *
     * @return invertor_type - 机构类型1:基金	2:保险公司3:一般法人4:信托公司5:社保基金6:QFII 7:券商	8:券商集合理财	9:企业年金20其他
     */
    public Short getInvertorType() {
        return invertorType;
    }

    /**
     * 设置机构类型1:基金	2:保险公司3:一般法人4:信托公司5:社保基金6:QFII 7:券商	8:券商集合理财	9:企业年金20其他
     *
     * @param invertorType 机构类型1:基金	2:保险公司3:一般法人4:信托公司5:社保基金6:QFII 7:券商	8:券商集合理财	9:企业年金20其他
     */
    public void setInvertorType(Short invertorType) {
        this.invertorType = invertorType;
    }

    /**
     * 获取公布时间
     *
     * @return day - 公布时间
     */
    public String getDay() {
        return day;
    }

    /**
     * 设置公布时间
     *
     * @param day 公布时间
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * 获取持股占比
     *
     * @return percent - 持股占比
     */
    public BigDecimal getPercent() {
        return percent;
    }

    /**
     * 设置持股占比
     *
     * @param percent 持股占比
     */
    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    /**
     * 获取股票数量
     *
     * @return stock_num - 股票数量
     */
    public BigDecimal getStockNum() {
        return stockNum;
    }

    /**
     * 设置股票数量
     *
     * @param stockNum 股票数量
     */
    public void setStockNum(BigDecimal stockNum) {
        this.stockNum = stockNum;
    }

    /**
     * 获取股票总值
     *
     * @return stock_worth - 股票总值
     */
    public BigDecimal getStockWorth() {
        return stockWorth;
    }

    /**
     * 设置股票总值
     *
     * @param stockWorth 股票总值
     */
    public void setStockWorth(BigDecimal stockWorth) {
        this.stockWorth = stockWorth;
    }

    /**
     * 获取增持情况
     *
     * @return comment - 增持情况
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置增持情况
     *
     * @param comment 增持情况
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}