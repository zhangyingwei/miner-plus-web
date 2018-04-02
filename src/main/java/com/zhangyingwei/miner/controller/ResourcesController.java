package com.zhangyingwei.miner.controller;

import com.zhangyingwei.miner.controller.result.PageInfo;
import com.zhangyingwei.miner.controller.result.Result;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.Resources;
import com.zhangyingwei.miner.service.IResourcesService;
import com.zhangyingwei.miner.service.ResourcesService;
import com.zhangyingwei.miner.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/api/resources/new")
    @ResponseBody
    public Map listInitResourcesWithPage(PageInfo pageInfo) throws MinerException {
        List<Resources> resources = this.resourcesService.listResourcesWithPageAndFlag(pageInfo,Resources.FLAG_INIT);
        Map result = new HashMap();
        result.put("data", resources);
        result.put("page", pageInfo);
        return Result.succes(result);
    }
}
