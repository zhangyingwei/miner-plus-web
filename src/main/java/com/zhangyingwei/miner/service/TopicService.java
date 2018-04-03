package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.controller.result.PageInfo;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.mapper.TopicMapper;
import com.zhangyingwei.miner.model.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangyw on 2018/4/3.
 */
@Service
public class TopicService implements ITopicService {

    @Autowired
    private TopicMapper topicMapper;
    private Logger logger = LoggerFactory.getLogger(TopicService.class);

    @Override
    public List<Topic> listTopicsWithParam(Topic topic) throws MinerException {
        try {
            topic.setFlag(Topic.FLAG_NOMAL);
            return this.topicMapper.listTopicsByParam(topic);
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }

    @Override
    public List<Topic> listTopicsWithPageAndParam(PageInfo pageInfo, Topic topic) throws MinerException {
        try {
            topic.setFlag(Topic.FLAG_NOMAL);
            pageInfo.setTotal(this.topicMapper.total(topic));
            return this.topicMapper.listTopicsByPageAndParam(pageInfo, topic);
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void addOne(Topic topic) throws MinerException {
        try {
            Topic t = this.topicMapper.selectOne(topic.getTopic());
            if (null == t) {
                this.topicMapper.addOne(topic);
            }else {
                if (Topic.FLAG_DEL.equals(t.getFlag())) {
                    Topic param = new Topic();
                    param.setFlag(Topic.FLAG_NOMAL);
                    this.topicMapper.updateByName(t.getTopic(), param);
                    logger.info("topic: " + topic.getTopic() + " 已存在，修改状态为正常");
                }else {
                    logger.info("topic: " + topic.getTopic() + " 已存在");
                    throw new Exception("已存在");
                }
            }
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void deleteOne(String topic) throws MinerException {
        Topic t = new Topic();
        t.setFlag(Topic.FLAG_DEL);
        try {
            this.topicMapper.updateByName(topic,t);
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }
}
