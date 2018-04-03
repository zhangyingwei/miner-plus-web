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
import java.util.Optional;

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
    public Map addRules(String uuid,String id,@RequestParam Map params) throws MinerException {
        ResRule title = this.bulidModel(params,id,"title");
        ResRule url = this.bulidModel(params,id,"url");
        ResRule desc = this.bulidModel(params,id,"desc");
        ResRule pubdate = this.bulidModel(params,id,"pubdate");
        try {
            this.ruleService.addRules(uuid,title, url, desc, pubdate);
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
        return Result.message("add rule success");
    }

    private ResRule bulidModel(Map params, String id,String key) {
        ResRule rule = new ResRule();
        rule.setRule(Optional.ofNullable(params.get(key.concat("[rule]"))).orElse("")+"");
        rule.setPrefix(Optional.ofNullable(params.get(key.concat("[prefix]"))).orElse("")+"");
        rule.setSuffix(Optional.ofNullable(params.get(key.concat("[suffix]"))).orElse("")+"");
        rule.setAttr(Optional.ofNullable(params.get(key.concat("[attr]"))).orElse("")+"");
        rule.setUrl(id);
        rule.setRtype(key);
        return rule;
    }
}