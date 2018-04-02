package com.zhangyingwei.miner.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhangyw on 2018/4/2.
 */
public class ResRuleTest {
    @Test
    public void bulidRuleMap() throws Exception {
        ResRule resRule = new ResRule();
        resRule.setPrefix("http://baidu.com/");
        resRule.setRule(".article@ul@li[1]@a");
        resRule.setAttr("text");
        System.out.println(resRule.bulidRules());
    }

}