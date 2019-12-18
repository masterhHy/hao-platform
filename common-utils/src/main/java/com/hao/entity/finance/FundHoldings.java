package com.hao.entity.finance;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "fn_fund_holdings")
public class FundHoldings {
    /**
     * 表id
     */
    @Id
    private Integer id;

    /**
     * 基金id
     */
    @Column(name = "fund_code_id")
    private Integer fundCodeId;

    /**
     * 股票id
     */
    @Column(name = "stock_code_id")
    private Integer stockCodeId;

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
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取基金id
     *
     * @return fund_code_id - 基金id
     */
    public Integer getFundCodeId() {
        return fundCodeId;
    }

    /**
     * 设置基金id
     *
     * @param fundCodeId 基金id
     */
    public void setFundCodeId(Integer fundCodeId) {
        this.fundCodeId = fundCodeId;
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

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}