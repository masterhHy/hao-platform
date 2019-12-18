package com.hao.entity.finance;

import java.util.Date;
import javax.persistence.*;

@Table(name = "fn_fund_code")
public class FundCode {
    /**
     * 表id
     */
    @Id
    private Integer id;

    /**
     * 基金代码
     */
    private String code;

    /**
     * 基金名称
     */
    private String name;

    /**
     * 基金类型1 股票型 2 混合型
     */
    private Short type;

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
     * @return code - 基金代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置基金代码
     *
     * @param code 基金代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取基金名称
     *
     * @return name - 基金名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置基金名称
     *
     * @param name 基金名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取基金类型1 股票型 2 混合型
     *
     * @return type - 基金类型1 股票型 2 混合型
     */
    public Short getType() {
        return type;
    }

    /**
     * 设置基金类型1 股票型 2 混合型
     *
     * @param type 基金类型1 股票型 2 混合型
     */
    public void setType(Short type) {
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