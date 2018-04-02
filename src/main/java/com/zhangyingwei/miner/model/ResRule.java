package com.zhangyingwei.miner.model;

import com.zhangyingwei.miner.common.MinerEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<String> bulidRules(){
        return Arrays.stream(rule.split(MinerEnum.RULE_SPLITER.getValue())).collect(Collectors.toList());
    }
}
