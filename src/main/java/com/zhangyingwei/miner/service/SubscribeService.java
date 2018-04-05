package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.email.EmailQueue;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.mapper.SubscribeMapper;
import com.zhangyingwei.miner.model.Subscribe;
import com.zhangyingwei.miner.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Service
public class SubscribeService implements ISubscribeService{
    @Autowired
    private SubscribeMapper subscribeMapper;
    @Autowired
    private EmailQueue emailQueue;
    private Logger logger = LoggerFactory.getLogger(SubscribeService.class);

    @Override
    public void adSubscribe(Subscribe subscribe) throws MinerException {
        try {
            Subscribe old = this.subscribeMapper.selectByEmail(subscribe.getEmail());
            if (old == null) {
                subscribe.setSubdate(DateUtils.getCurrentDateTime());
                subscribe.setFlag(Subscribe.FLAG_INIT);
                this.subscribeMapper.addSubscribe(subscribe);
                emailQueue.push(subscribe.getEmail());
                logger.info(subscribe.getEmail() + "已经入队");
            }else {
                if (old.getFlag() >= Subscribe.FLAG_CKECKED) {
                    subscribe.setFlag(Subscribe.FLAG_CKECKED);
                    this.subscribeMapper.updateByEmail(subscribe);
                    throw new MinerException("您是老用户，已经修改订阅主题");
                } else {
                    subscribe.setFlag(Subscribe.FLAG_NOTICE);
                    this.subscribeMapper.updateByEmail(subscribe);
                    emailQueue.push(old.getEmail());
                    logger.info(subscribe.getEmail() + "已经入队");
                    throw new MinerException("订阅成功，请登录邮箱进行验证！！！");
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

    @Override
    public void markAsChecked(String email) throws MinerException {
        try {
            this.subscribeMapper.updateFlagByEmail(email, Subscribe.FLAG_CKECKED);
        } catch (Exception e) {
            throw new MinerException(e);
        }
    }
}
