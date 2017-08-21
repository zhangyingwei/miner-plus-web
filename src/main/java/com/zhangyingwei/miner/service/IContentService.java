package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.Content;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/14.
 */
public interface IContentService {
    List<Content> listContentsToDay() throws MinerException;
    List<Content> listContentsToDayTop10() throws MinerException;
    List<Content> listNomalContentsToDay() throws MinerException;
    List<Content> listNomalContentsToDayTop10() throws MinerException;
    List<String> listTopics() throws MinerException;
}

