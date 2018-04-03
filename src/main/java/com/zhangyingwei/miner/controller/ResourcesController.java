package com.zhangyingwei.miner.controller;

import com.zhangyingwei.miner.common.Auth;
import com.zhangyingwei.miner.controller.result.PageInfo;
import com.zhangyingwei.miner.controller.result.Result;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.Resources;
import com.zhangyingwei.miner.service.IResourcesService;
import com.zhangyingwei.miner.service.ResourcesService;
import com.zhangyingwei.miner.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Controller
@RequestMapping
public class ResourcesController {
    @Autowired
    private ResourcesService resourcesService;

    @PostMapping("/resources")
    @ResponseBody
    public Map site(HttpServletRequest request, String site) throws MinerException {
        Resources resources = new Resources();
        resources.setResources(site);
        resources.setSip(WebUtils.getIpAddress(request));
        this.resourcesService.addResources(resources);
        return Result.success();
    }

    @Auth
    @GetMapping("/api/resources/new")
    @ResponseBody
    public Map listInitResourcesWithPage(PageInfo pageInfo) throws MinerException {
        List<Resources> resources = this.resourcesService.listResourcesWithPageAndFlag(pageInfo,Resources.FLAG_INIT);
        Map result = new HashMap();
        result.put("data", resources);
        result.put("page", pageInfo);
        return Result.succes(result);
    }

    @Auth
    @GetMapping("/api/resources")
    @ResponseBody
    public Map listAllResourcesWithPage(PageInfo pageInfo) throws MinerException {
        List<Resources> resources = this.resourcesService.listResourcesWithPage(pageInfo);
        Map result = new HashMap();
        result.put("data", resources);
        result.put("page", pageInfo);
        return Result.succes(result);
    }

    @Auth
    @GetMapping("/api/resources/bad")
    @ResponseBody
    public Map listBadResourcesWithPage(PageInfo pageInfo) throws MinerException {
        List<Resources> resources = this.resourcesService.listResourcesWithPageAndFlag(pageInfo,Resources.FLAG_UNUSED);
        Map result = new HashMap();
        result.put("data", resources);
        result.put("page", pageInfo);
        return Result.succes(result);
    }

    @Auth
    @DeleteMapping("/api/resources/{id}")
    @ResponseBody
    public Map removeById(@PathVariable("id") String id) throws MinerException {
        this.resourcesService.removeResourcesByState(id);
        return Result.success();
    }

    @Auth
    @PostMapping("/api/resources/bad/{id}")
    @ResponseBody
    public Map isBad(@PathVariable("id") String id) throws MinerException {
        this.resourcesService.badResources(id);
        return Result.success();
    }

    @Auth
    @PostMapping("/api/resources/good/{id}")
    @ResponseBody
    public Map isGood(@PathVariable("id") String id) throws MinerException {
        this.resourcesService.goodResources(id);
        return Result.success();
    }
}
