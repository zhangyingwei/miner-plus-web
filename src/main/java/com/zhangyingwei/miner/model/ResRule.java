package com.zhangyingwei.miner.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: zhangyw
 * @date: 2018/4/1
 * @time: 下午10:05
 * @desc:
 */

@Getter
@Setter
@ToString
public class ResRule {
    private String rule;
    private String prefix;
    private String suffix;
    private String attr;
}
