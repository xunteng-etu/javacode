package com.edu.base.entity;

import java.util.Date;

/**
 * @program: edu_parent
 * @description:
 * @author: BaronLi
 * @create: 2019-07-05 09:58
 */
public class BaseEntity {
    private Integer serial;
    private String remark;
    private String sysStatus;
    private Date createTime;
    private Date updateTime;
    private String valueFlag;

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSysStatus() {
        return sysStatus;
    }

    public void setSysStatus(String sysStatus) {
        this.sysStatus = sysStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getValueFlag() {
        return valueFlag;
    }

    public void setValueFlag(String valueFlag) {
        this.valueFlag = valueFlag;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "serial=" + serial +
                ", remark='" + remark + '\'' +
                ", sysStatus='" + sysStatus + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", valueFlag='" + valueFlag + '\'' +
                '}';
    }
}
