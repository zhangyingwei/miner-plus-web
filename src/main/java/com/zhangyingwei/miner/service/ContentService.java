package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.controller.result.PageInfo;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.mapper.ContentMapper;
import com.zhangyingwei.miner.model.Content;
import com.zhangyingwei.miner.model.Topic;
import com.zhangyingwei.miner.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Service
public class ContentService implements IContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public List<Content> listContentsWithPageAndParram(PageInfo pageInfo, Content content) throws MinerException {
        try {
            pageInfo.setTotal(this.contentMapper.total(content));
            return this.contentMapper.listContentsWithPageAndParam(pageInfo,content);
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }

    @Override
    public List<Content> listContentsToDay() throws MinerException {
        String toDay = DateUtils.getCurrentDate();
        toDay += "%";
        try {
            return this.contentMapper.listContentsByGetDate(toDay);
        } catch (Exception e) {
            throw new MinerException(e);
        }
    }

    @Override
    public List<Content> listContentsToDayTop10() throws MinerException {
        String toDay = DateUtils.getCurrentDate();
        toDay += "%";
        try {
            return this.contentMapper.listContentsByGetDateLimit(toDay,10);
        } catch (Exception e) {
            throw new MinerException(e);
        }
    }

    @Override
    public List<Content> listNomalContentsToDay() throws MinerException {
        String toDay = DateUtils.getCurrentDate();
        toDay += "%";
        try {
            return this.contentMapper.listContentsByGetDateAndState(toDay,Content.STATE_NOMAL);
        } catch (Exception e) {
            throw new MinerException(e);
        }
    }

    @Override
    public List<Content> listNomalContentsToDayTop10() throws MinerException {
        String toDay = DateUtils.getCurrentDate();
        toDay += "%";
        try {
            List<Content> contents = this.contentMapper.listContentsByPushDateAndStateLimit(toDay, 10, Content.STATE_PASS);
            for (Content content : contents) {
                content.setGetdate(DateUtils.formateDatetimeAsDate(content.getGetdate()));
                content.setPubdate(DateUtils.formateDatetimeAsDate(content.getPubdate()));
            }
            return contents;
        } catch (Exception e) {
            throw new MinerException(e);
        }
    }

    @Override
    public List<Topic> listTopics() throws MinerException {
        try {
            return this.contentMapper.listTopicsByState(Topic.FLAG_NOMAL);
        } catch (Exception e) {
            throw new MinerException(e);
        }
    }

    @Override
    public void readyToPush(String id, String comment) throws MinerException {
        Content content = new Content();
        content.setState(Content.STATE_PASS);
        content.setComment(comment);
//        content.setPushdate(DateUtils.getNextDate());
        try {
            this.contentMapper.updateById(id,content);
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void isRubbis(String id)  throws MinerException{
        Content content = new Content();
        content.setState(Content.STATE_NOPASS);
        try {
            this.contentMapper.updateById(id,content);
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void push(String id, String date) throws MinerException {
        Content content = new Content();
        content.setPushdate(date);
        try {
            this.contentMapper.updateById(id,content);
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }
}
