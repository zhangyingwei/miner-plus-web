package com.zhangyingwei.miner.utils;

import com.zhangyingwei.miner.model.ResRule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: zhangyw
 * @date: 2018/4/1
 * @time: 下午9:16
 * @desc:
 */
public class WebUtilsTest {
    @Test
    public void select() throws Exception {
        String url = "https://www.barretlee.com/blog/archives/";
        ResRule rules = new ResRule();
        rules.setRule("div.cate-detail.response-overflow@ul> li > a");
//        rules.setAttr("href");
//        rules.setPrefix("https://www.barretlee.com");
        List<String> res = WebUtils.select(url, rules);
        res.stream().forEach(line -> {
            System.out.println(line);
        });
    }

}