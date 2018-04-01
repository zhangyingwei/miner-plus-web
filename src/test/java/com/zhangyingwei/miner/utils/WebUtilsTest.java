package com.zhangyingwei.miner.utils;

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
        String url = "https://www.barretlee.com/entry/";
        List<String> rules = new ArrayList<String>() {
            {
                add(".entry-recent-posts");
                add("ul");
                add("li");
                add("a");
            }
        };
        List<String> res = WebUtils.select(url, rules);
        res.stream().forEach(line -> {
            System.out.println(line);
        });
    }

}