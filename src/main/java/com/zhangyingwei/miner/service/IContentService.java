package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.Content;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/14.
 */
public interface IContentService {
    List<Content> listContentsToDay() throws MinerException;
}