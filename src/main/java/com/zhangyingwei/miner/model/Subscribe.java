package com.zhangyingwei.miner.model;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Created by zhangyw on 2017/8/14.
 * 订阅
 */
@Validated
public class Subscribe {
    /**
     * 待验证
     */
    public static final Integer FLAG_INIT = 0;
    /**
     * 已通知
     */

    public static final Integer FLAG_NOTICE = 1;
    /**
     * 已验证
     */
    public static final Integer FLAG_CKECKED = 2;
    /**
     * 已退订
     */
    public static final Integer FLAG_UNSUB = 9;
    private Integer id;
    @NotNull(message = "邮箱不能为空")
    private String email;
    private String topics;
    private Integer flag;
    private String subdate;
    private String dissubdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getSubdate() {
        return subdate;
    }

    public void setSubdate(String subdate) {
        this.subdate = subdate;
    }

    public String getDissubdate() {
        return dissubdate;
    }

    public void setDissubdate(String dissubdate) {
        this.dissubdate = dissubdate;
    }

    @Override
    public String toString() {
        return "Subscribe{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", topics='" + topics + '\'' +
                ", flag=" + flag +
                ", subdate='" + subdate + '\'' +
                ", dissubdate='" + dissubdate + '\'' +
                '}';
    }
}
