package com.zhangyingwei.miner.email;

import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.mapper.SubscribeMapper;
import com.zhangyingwei.miner.service.SubscribeService;
import com.zhangyingwei.miner.utils.EmailUtils;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    private EmailSender emailSender;

    @Value("${miner.email.sleep}")
    private String sleep;

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
                Thread.sleep(this.getSleep());
                boolean result = this.emailSender.send(email, EmailUtils.checkTitle(), EmailUtils.checkContent(email));
                if(result){
                    logger.info(email+" 发送邮件成功");
                    this.subscribeService.markAsValid(email);
                }else{
                    logger.info(email + " 发送邮件失败");
                }
            } catch (InterruptedException | MinerException e) {
                logger.info(e.getLocalizedMessage());
            }
        }
    }

    /**
     * 获取发送邮件线程 sleep 时间
     * @return
     */
    private long getSleep() {
        this.sleep = this.sleep == null ? "0" : this.sleep;
        return Long.parseLong(this.sleep);
    }

    public String getId() {
        return id;
    }
}