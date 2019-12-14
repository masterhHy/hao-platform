package com.hao.entity.finance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "fn_stock_code")
public class StockCode implements Serializable {
    @Id
    private Integer id;

    /**
     * 股票代码
     */
    private String code;

    /**
     * 股票名称
     */
    private String name;

    private String industry;

    /**
     * 股票类型
     */
    private Integer type;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;


    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取股票代码
     *
     * @return code - 股票代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置股票代码
     *
     * @param code 股票代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取股票名称
     *
     * @return name - 股票名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置股票名称
     *
     * @param name 股票名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取股票类型
     *
     * @return type - 股票类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置股票类型
     *
     * @param type 股票类型
     */
    public void setType(Integer type) {
        this.type = type;
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