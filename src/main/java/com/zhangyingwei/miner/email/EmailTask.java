package com.zhangyingwei.miner.email;

import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.mapper.SubscribeMapper;
import com.zhangyingwei.miner.service.SubscribeService;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Component("emailTask")
@Scope("prototype")
public class EmailTask implements Runnable {
    private Logger logger = LoggerFactory.getLogger(EmailTask.class);

    @Autowired
    private SubscribeService subscribeService;

    private EmailQueue queue;
    private String id = UUID.randomUUID().toString();

    public EmailTask() {}

    public EmailQueue getQueue() {
        return queue;
    }

    public void setQueue(EmailQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try {
                String email = this.queue.take();
                Thread.sleep(5000);
                this.subscribeService.markAsValid(email);
                logger.info("发送邮件成功");
            } catch (InterruptedException | MinerException e) {
                logger.info(e.getLocalizedMessage());
            }
        }
    }

    public String getId() {
        return id;
    }
}