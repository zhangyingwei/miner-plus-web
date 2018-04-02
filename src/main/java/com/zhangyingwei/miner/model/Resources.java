package com.zhangyingwei.miner.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Validated
@Getter
@Setter
@ToString
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
    private String rgroup;
    private String rtype;
    private String sip;
    private String sarea;
    private String createdate;
    private String updatedate;
    private Integer flag;
}
