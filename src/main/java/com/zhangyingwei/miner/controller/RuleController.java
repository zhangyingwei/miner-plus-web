package com.zhangyingwei.miner.controller;

import com.zhangyingwei.miner.controller.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2018/4/1
 * @time: 下午9:34
 * @desc:
 */
@RestController
@RequestMapping("/rules")
public class RuleController {

    @PostMapping("/test")
    public Map testRule(String rule){
        return Result.success();
    }
}
