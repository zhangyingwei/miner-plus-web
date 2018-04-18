package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.controller.result.PageInfo;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.Content;
import com.zhangyingwei.miner.model.PushCount;
import com.zhangyingwei.miner.model.Topic;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/14.
 */
public interface IContentService {
    List<Content> listContentsWithPageAndParram(PageInfo pageInfo,Content content) throws MinerException;
    List<Content> listContentsToDay() throws MinerException;
    List<Content> listContentsToDayTop10() throws MinerException;
    List<Content> listNomalContentsToDay() throws MinerException;
    List<Content> listNomalContentsToDayTop10(String data) throws MinerException;
    List<Topic> listTopics() throws MinerException;
    void readyToPush(String id, String comment,Integer select) throws MinerException;

    void isRubbis(String id) throws MinerException;

    void push(String id, String date) throws MinerException;

    Integer getYesterdayNew() throws MinerException;

    List<PushCount> listPushCountAfterNow() throws MinerException;
}

