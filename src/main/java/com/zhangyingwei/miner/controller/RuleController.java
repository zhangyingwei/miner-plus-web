package com.zhangyingwei.miner.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zhangyingwei.miner.controller.result.Result;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.ResRule;
import com.zhangyingwei.miner.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseBody
    public Map addRules(String id,@RequestParam Map params) {
        System.out.println(params);
        return Result.message("add rule success");
    }
}
