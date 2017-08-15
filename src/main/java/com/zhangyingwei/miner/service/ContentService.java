package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.mapper.ContentMapper;
import com.zhangyingwei.miner.model.Content;
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
}
