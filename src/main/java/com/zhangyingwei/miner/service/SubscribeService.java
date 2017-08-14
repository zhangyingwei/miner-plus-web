package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.email.EmailQueue;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.mapper.SubscribeMapper;
import com.zhangyingwei.miner.model.Subscribe;
import com.zhangyingwei.miner.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Service
public class SubscribeService implements ISubscribeService{
    @Autowired
    private SubscribeMapper subscribeMapper;
    @Autowired
    private EmailQueue emailQueue;
    @Override
    public void adSubscribe(Subscribe subscribe) throws MinerException {
        try {
            Subscribe old = this.subscribeMapper.selectByEmail(subscribe.getEmail());
            if (old == null) {
                subscribe.setSubdate(DateUtils.getCurrentDateTime());
                subscribe.setFlag(Subscribe.FLAG_INIT);
                this.subscribeMapper.addSubscribe(subscribe);
                emailQueue.push(subscribe.getEmail());
            }else {
                if (old.getFlag() == Subscribe.FLAG_INIT) {
                    throw new MinerException("您已经订阅过啦！");
                } else {
                    this.subscribeMapper.updateFlagByEmail(old.getEmail(),Subscribe.FLAG_INIT);
                    emailQueue.push(old.getEmail());
                }
            }
        } catch (Exception e) {
            throw new MinerException(e);
        }
    }

    @Override
    public void markAsValid(String email) throws MinerException {
        try {
            this.subscribeMapper.updateFlagByEmail(email, Subscribe.FLAG_NOTICE);
        } catch (Exception e) {
            throw new MinerException(e);
        }
    }
}
