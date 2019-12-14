package com.hao.entity.finance;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "fn_rise_stock_code")
public class RiseStockCode {
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
     * 汇聚参数方案id
     */
    @Column(name = "rise_param_id")
    private Integer riseParamId;

    /**
     * 最大天数
     */
    private Short days;

    /**
     * 斜率
     */
    private BigDecimal beta;

    /**
     * 方差
     */
    private BigDecimal r2;

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
     * 获取汇聚参数方案id
     *
     * @return rise_param_id - 汇聚参数方案id
     */
    public Integer getRiseParamId() {
        return riseParamId;
    }

    /**
     * 设置汇聚参数方案id
     *
     * @param riseParamId 汇聚参数方案id
     */
    public void setRiseParamId(Integer riseParamId) {
        this.riseParamId = riseParamId;
    }

    /**
     * 获取最大天数
     *
     * @return days - 最大天数
     */
    public Short getDays() {
        return days;
    }

    /**
     * 设置最大天数
     *
     * @param days 最大天数
     */
    public void setDays(Short days) {
        this.days = days;
    }

    /**
     * 获取斜率
     *
     * @return beta - 斜率
     */
    public BigDecimal getBeta() {
        return beta;
    }

    /**
     * 设置斜率
     *
     * @param beta 斜率
     */
    public void setBeta(BigDecimal beta) {
        this.beta = beta;
    }

    /**
     * 获取方差
     *
     * @return r2 - 方差
     */
    public BigDecimal getR2() {
        return r2;
    }

    /**
     * 设置方差
     *
     * @param r2 方差
     */
    public void setR2(BigDecimal r2) {
        this.r2 = r2;
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