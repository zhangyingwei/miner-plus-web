package com.zhangyingwei.miner.controller;

import com.zhangyingwei.miner.controller.result.Result;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.ResRule;
import com.zhangyingwei.miner.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2018/4/1
 * @time: 下午9:34
 * @desc:
 */
@RestController
@RequestMapping("/api/rules")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @PostMapping("/test")
    public Map testRule(String url,ResRule resRule) throws MinerException {
        List<String> res = this.ruleService.testRule(url, resRule);
        return Result.succes(res);
    }
}
