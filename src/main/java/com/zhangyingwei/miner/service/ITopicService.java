package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.controller.result.PageInfo;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.Topic;

import java.util.List;

/**
 * Created by zhangyw on 2018/4/3.
 */
public interface ITopicService {
    List<Topic> listTopicsWithParam(Topic topic) throws MinerException;

    List<Topic> listTopicsWithPageAndParam(PageInfo pageInfo, Topic topic) throws MinerException;

    void addOne(Topic topic) throws MinerException;

    void deleteOne(String topic) throws MinerException;
}
