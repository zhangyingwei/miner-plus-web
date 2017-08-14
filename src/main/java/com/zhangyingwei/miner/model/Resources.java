package com.zhangyingwei.miner.model;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Validated
public class Resources {
    /**
     * 未审核
     */
    public static final Integer FLAG_INIT = 0;
    /**
     * 正常
     */
    public static final Integer FLAG_NOMAL = 1;
    /**
     * 失效
     */
    public static final Integer FLAG_UNUSED = 2;
    /**
     * 删除
     */
    public static final Integer FLAG_DEL = 9;
    private Integer id;
    @NotNull(message = "地址不能为空")
    private String resources;
    private String type;
    private String createdate;
    private String updatedate;
    private Integer flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "id=" + id +
                ", resources='" + resources + '\'' +
                ", type='" + type + '\'' +
                ", createdate='" + createdate + '\'' +
                ", updatedate='" + updatedate + '\'' +
                ", flag=" + flag +
                '}';
    }
}
