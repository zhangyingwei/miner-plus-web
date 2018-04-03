package com.zhangyingwei.miner.controller;

import com.zhangyingwei.miner.controller.result.PageInfo;
import com.zhangyingwei.miner.controller.result.Result;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.Topic;
import com.zhangyingwei.miner.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2018/4/3.
 */
@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public Map listTopicsWithPage(PageInfo pageInfo,Topic topic) throws MinerException {
        List<Topic> topics = this.topicService.listTopicsWithPageAndParam(pageInfo, topic);
        Map result = new HashMap();
        result.put("page", pageInfo);
        result.put("data", topics);
        return Result.succes(result);
    }

    @GetMapping("/active")
    public Map listTopics() throws MinerException {
        Topic topic = new Topic();
        List<Topic> topics = this.topicService.listTopicsWithParam(topic);
        List<Map> res = topics.stream().map(t -> {
            Map map = new HashMap();
            map.put("key", t.getId());
            map.put("title", t.getTopic());
            return map;
        }).collect(Collectors.toList());
        return Result.succes(res);
    }

    @PostMapping
    public Map addOne(Topic topic) throws MinerException {
        this.topicService.addOne(topic);
        return Result.success();
    }

    @DeleteMapping("/{topic}")
    public Map deleteOne(@PathVariable("topic") String topic) throws MinerException {
        this.topicService.deleteOne(topic);
        return Result.success();
    }
}
