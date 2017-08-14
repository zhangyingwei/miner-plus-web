package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.Subscribe;

/**
 * Created by zhangyw on 2017/8/14.
 */
public interface ISubscribeService {
    void adSubscribe(Subscribe subscribe) throws MinerException;

    void markAsValid(String email) throws MinerException;
}
