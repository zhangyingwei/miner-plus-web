package com.zhangyingwei.miner.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: zhangyw
 * @date: 2018/4/2
 * @time: 下午9:26
 * @desc:
 */
@Getter
@Setter
@ToString
public class Rules {
    private String id;
    private ResRule title;
    private ResRule url;
    private ResRule desc;
    private ResRule pubdate;
}
