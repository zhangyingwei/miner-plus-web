package com.zhangyingwei.miner.controller;

import com.zhangyingwei.miner.common.MinerCache;
import com.zhangyingwei.miner.controller.result.Result;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.Subscribe;
import com.zhangyingwei.miner.service.ISubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Controller
@RequestMapping
public class SubscribeController {

    @Autowired
    private ISubscribeService subscribeService;

    @PostMapping("subscribe")
    @ResponseBody
    public Map subscribe(Subscribe subscribe) throws MinerException {
        this.subscribeService.adSubscribe(subscribe);
        return Result.success();
    }

    @GetMapping("subscribe/check/{email}/{id}")
    public String check(@PathVariable("email") String email,@PathVariable("id") String id,Map model){
        MinerCache.put(email, UUID.randomUUID().toString());
        String oid = MinerCache.get(email);
        if(oid == null){
            model.put("code", 400);
            model.put("message", "过期");
        }else{
            model.put("code", 200);
            model.put("message", "成功");
        }
        return "checkemail";
    }
}
