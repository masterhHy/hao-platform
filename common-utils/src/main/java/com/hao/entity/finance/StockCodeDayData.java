package com.hao.entity.finance;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "fn_stock_code_day_data")
public class StockCodeDayData {
    /**
     * 表id
     */
    @Id
    private Integer id;

    /**
     * 开盘日期
     */
    @Column(name = "open_date")
    private String openDate;

    /**
     * 开盘价格
     */
    @Column(name = "open_price")
    private BigDecimal openPrice;

    /**
     * 收盘价格
     */
    @Column(name = "close_price")
    private BigDecimal closePrice;

    /**
     * 最高价格
     */
    @Column(name = "top_price")
    private BigDecimal topPrice;

    /**
     * 最低价格
     */
    @Column(name = "low_price")
    private BigDecimal lowPrice;

    /**
     * 涨跌幅
     */
    @Column(name = "final_percent")
    private BigDecimal finalPercent;

    /**
     * 成交量(手)
     */
    private BigDecimal num;

    /**
     * 成交金额(亿)
     */
    private BigDecimal amount;

    /**
     * 换手率
     */
    @Column(name = "change_hand")
    private BigDecimal changeHand;

    /**
     * 股票id
     */
    @Column(name = "stock_code_id")
    private Integer stockCodeId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
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
     * 获取开盘日期
     *
     * @return open_date - 开盘日期
     */
    public String getOpenDate() {
        return openDate;
    }

    /**
     * 设置开盘日期
     *
     * @param openDate 开盘日期
     */
    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    /**
     * 获取开盘价格
     *
     * @return open_price - 开盘价格
     */
    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    /**
     * 设置开盘价格
     *
     * @param openPrice 开盘价格
     */
    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    /**
     * 获取收盘价格
     *
     * @return close_price - 收盘价格
     */
    public BigDecimal getClosePrice() {
        return closePrice;
    }

    /**
     * 设置收盘价格
     *
     * @param closePrice 收盘价格
     */
    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    /**
     * 获取最高价格
     *
     * @return top_price - 最高价格
     */
    public BigDecimal getTopPrice() {
        return topPrice;
    }

    /**
     * 设置最高价格
     *
     * @param topPrice 最高价格
     */
    public void setTopPrice(BigDecimal topPrice) {
        this.topPrice = topPrice;
    }

    /**
     * 获取最低价格
     *
     * @return low_price - 最低价格
     */
    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    /**
     * 设置最低价格
     *
     * @param lowPrice 最低价格
     */
    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    /**
     * 获取涨跌幅
     *
     * @return final_percent - 涨跌幅
     */
    public BigDecimal getFinalPercent() {
        return finalPercent;
    }

    /**
     * 设置涨跌幅
     *
     * @param finalPercent 涨跌幅
     */
    public void setFinalPercent(BigDecimal finalPercent) {
        this.finalPercent = finalPercent;
    }

    /**
     * 获取成交量(手)
     *
     * @return num - 成交量(手)
     */
    public BigDecimal getNum() {
        return num;
    }

    /**
     * 设置成交量(手)
     *
     * @param num 成交量(手)
     */
    public void setNum(BigDecimal num) {
        this.num = num;
    }

    /**
     * 获取成交金额(亿)
     *
     * @return amount - 成交金额(亿)
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置成交金额(亿)
     *
     * @param amount 成交金额(亿)
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取换手率
     *
     * @return change_hand - 换手率
     */
    public BigDecimal getChangeHand() {
        return changeHand;
    }

    /**
     * 设置换手率
     *
     * @param changeHand 换手率
     */
    public void setChangeHand(BigDecimal changeHand) {
        this.changeHand = changeHand;
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
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "StockCodeDayData{" +
                "id=" + id +
                ", openDate='" + openDate + '\'' +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", topPrice=" + topPrice +
                ", lowPrice=" + lowPrice +
                ", finalPercent=" + finalPercent +
                ", num=" + num +
                ", amount=" + amount +
                ", changeHand=" + changeHand +
                ", stockCodeId=" + stockCodeId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}