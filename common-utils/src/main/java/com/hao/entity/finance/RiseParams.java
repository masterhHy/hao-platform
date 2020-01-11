package com.hao.entity.finance;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "fn_rise_params")
public class RiseParams {
    /**
     * 表id
     */
    @Id
    private Integer id;

    /**
     * 方差
     */
    private BigDecimal r2;

    /**
     * 天数最小值
     */
    private Short days;

    /**
     * 斜率最小值
     */
    private String beta;

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
     * 是否生效 0 不生效 1 生效
     */
    private Short status;

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

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
     * 获取天数最小值
     *
     * @return days - 天数最小值
     */
    public Short getDays() {
        return days;
    }

    /**
     * 设置天数最小值
     *
     * @param days 天数最小值
     */
    public void setDays(Short days) {
        this.days = days;
    }

    /**
     * 获取斜率最小值
     *
     * @return beta - 斜率最小值
     */
    public String getBeta() {
        return beta;
    }

    /**
     * 设置斜率最小值
     *
     * @param beta 斜率最小值
     */
    public void setBeta(String beta) {
        this.beta = beta;
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
}