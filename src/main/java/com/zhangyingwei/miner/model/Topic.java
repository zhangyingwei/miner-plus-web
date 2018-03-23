package com.zhangyingwei.miner.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by zhangyw on 2018/3/23.
 */
@Getter
@Setter
@ToString
public class Topic {
    private Integer id;
    private String topic;
    private Integer flag;
    public static final Integer FLAG_NOMAL = 0;
    public static final Integer FLAG_DEL = 9;
}
