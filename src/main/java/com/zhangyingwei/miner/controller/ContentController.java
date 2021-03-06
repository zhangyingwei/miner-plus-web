package com.zhangyingwei.miner.controller;

import com.zhangyingwei.miner.common.Auth;
import com.zhangyingwei.miner.controller.result.PageInfo;
import com.zhangyingwei.miner.controller.result.Result;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.Content;
import com.zhangyingwei.miner.model.PushCount;
import com.zhangyingwei.miner.service.ContentService;
import com.zhangyingwei.miner.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyw on 2018/4/3.
 */
@RestController
@RequestMapping("/api/contents")
@Auth
public class ContentController {

    @Autowired
    private ContentService contentService;
    @GetMapping("/news")
    public Map listNewWithPage(PageInfo pageInfo) throws MinerException {
        Content content = new Content();
        content.setState(Content.STATE_INIT);
        List<Content> contents = this.contentService.listContentsWithPageAndParram(pageInfo, content);
        Map result = new HashMap();
        result.put("page", pageInfo);
        result.put("data", contents);
        return Result.succes(result);
    }

    @PostMapping("/push/{id}")
    public Map readyToPush(@PathVariable("id") String id,Integer select,String comment) throws MinerException {
        this.contentService.readyToPush(id,comment,select);
        return Result.message("成功");
    }

    @PostMapping("/push")
    public Map push(String id,String date) throws MinerException {
        this.contentService.push(id,date);
        return Result.message("成功");
    }

    @GetMapping("/push")
    public Map listPush(PageInfo pageInfo) throws MinerException {
        Content content = new Content();
        content.setState(Content.STATE_PASS);
        content.setPushdate(DateUtils.getCurrentDate());
        List<Content> contents = this.contentService.listContentsWithPageAndParram(pageInfo, content);
        Map result = new HashMap();
        result.put("page", pageInfo);
        result.put("data", contents);
        return Result.succes(result);
    }

    @DeleteMapping("/review/{id}")
    public Map isRubbis(@PathVariable("id") String id) throws MinerException {
        this.contentService.isRubbis(id);
        return Result.message("成功");
    }

    @GetMapping("/news/yesterday")
    public Map yesterdayNew() throws MinerException{
        Integer count = this.contentService.getYesterdayNew();
        return Result.succes(count);
    }

    @GetMapping("/push/count")
    public Map listPushCount() throws MinerException {
        List<PushCount> counes = this.contentService.listPushCountAfterNow();
        return Result.succes(counes);
    }
}