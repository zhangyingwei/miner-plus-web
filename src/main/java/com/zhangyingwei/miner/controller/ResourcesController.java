package com.zhangyingwei.miner.controller;

import com.zhangyingwei.miner.controller.result.Result;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.Resources;
import com.zhangyingwei.miner.service.IResourcesService;
import com.zhangyingwei.miner.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Controller
@RequestMapping
public class ResourcesController {
    @Autowired
    private ResourcesService resourcesService;

    @PostMapping("resources")
    @ResponseBody
    public Map site(String site) throws MinerException {
        Resources resources = new Resources();
        resources.setResources(site);
        this.resourcesService.addResources(resources);
        return Result.success();
    }
}
