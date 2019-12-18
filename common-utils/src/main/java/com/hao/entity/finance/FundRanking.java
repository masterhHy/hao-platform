package com.hao.entity.finance;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "fn_fund_ranking")
public class FundRanking {
    /**
     * 表id
     */
    @Id
    private Integer id;

    /**
     * 基金代码
     */
    @Column(name = "fund_code_id")
    private Integer fundCodeId;

    /**
     * 基金净值
     */
    private BigDecimal worth;

    /**
     * 一天累计涨幅
     */
    @Column(name = "one_day")
    private BigDecimal oneDay;

    /**
     * 一周累计涨幅
     */
    @Column(name = "one_week")
    private BigDecimal oneWeek;

    /**
     * 一个月累计涨幅
     */
    @Column(name = "one_month")
    private BigDecimal oneMonth;

    /**
     * 三个月累计涨幅
     */
    @Column(name = "three_month")
    private BigDecimal threeMonth;

    /**
     * 六个月累计涨幅
     */
    @Column(name = "six_month")
    private BigDecimal sixMonth;

    /**
     * 一年累计涨幅
     */
    @Column(name = "one_year")
    private BigDecimal oneYear;

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
     * 获取基金代码
     *
     * @return fund_code_id - 基金代码
     */
    public Integer getFundCodeId() {
        return fundCodeId;
    }

    /**
     * 设置基金代码
     *
     * @param fundCodeId 基金代码
     */
    public void setFundCodeId(Integer fundCodeId) {
        this.fundCodeId = fundCodeId;
    }

    /**
     * 获取基金净值
     *
     * @return worth - 基金净值
     */
    public BigDecimal getWorth() {
        return worth;
    }

    /**
     * 设置基金净值
     *
     * @param worth 基金净值
     */
    public void setWorth(BigDecimal worth) {
        this.worth = worth;
    }

    /**
     * 获取一天累计涨幅
     *
     * @return one_day - 一天累计涨幅
     */
    public BigDecimal getOneDay() {
        return oneDay;
    }

    /**
     * 设置一天累计涨幅
     *
     * @param oneDay 一天累计涨幅
     */
    public void setOneDay(BigDecimal oneDay) {
        this.oneDay = oneDay;
    }

    /**
     * 获取一周累计涨幅
     *
     * @return one_week - 一周累计涨幅
     */
    public BigDecimal getOneWeek() {
        return oneWeek;
    }

    /**
     * 设置一周累计涨幅
     *
     * @param oneWeek 一周累计涨幅
     */
    public void setOneWeek(BigDecimal oneWeek) {
        this.oneWeek = oneWeek;
    }

    /**
     * 获取一个月累计涨幅
     *
     * @return one_month - 一个月累计涨幅
     */
    public BigDecimal getOneMonth() {
        return oneMonth;
    }

    /**
     * 设置一个月累计涨幅
     *
     * @param oneMonth 一个月累计涨幅
     */
    public void setOneMonth(BigDecimal oneMonth) {
        this.oneMonth = oneMonth;
    }

    /**
     * 获取三个月累计涨幅
     *
     * @return three_month - 三个月累计涨幅
     */
    public BigDecimal getThreeMonth() {
        return threeMonth;
    }

    /**
     * 设置三个月累计涨幅
     *
     * @param threeMonth 三个月累计涨幅
     */
    public void setThreeMonth(BigDecimal threeMonth) {
        this.threeMonth = threeMonth;
    }

    /**
     * 获取六个月累计涨幅
     *
     * @return six_month - 六个月累计涨幅
     */
    public BigDecimal getSixMonth() {
        return sixMonth;
    }

    /**
     * 设置六个月累计涨幅
     *
     * @param sixMonth 六个月累计涨幅
     */
    public void setSixMonth(BigDecimal sixMonth) {
        this.sixMonth = sixMonth;
    }

    /**
     * 获取一年累计涨幅
     *
     * @return one_year - 一年累计涨幅
     */
    public BigDecimal getOneYear() {
        return oneYear;
    }

    /**
     * 设置一年累计涨幅
     *
     * @param oneYear 一年累计涨幅
     */
    public void setOneYear(BigDecimal oneYear) {
        this.oneYear = oneYear;
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