package com.zhangyingwei.miner.email;

import com.zhangyingwei.miner.common.ApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Component("emailHandler")
public class EmailHandler {
    private int threadNum = 2;
    private Logger logger = LoggerFactory.getLogger(EmailHandler.class);
    @Autowired
    private EmailQueue queue;
    public void start(int threadNum){
        this.threadNum = threadNum;
        this.run();
    }
    public void start(){
        this.run();
    }

    public void produceLog(String email){
        this.queue.push(email);
    }

    private void run(){
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < threadNum; i++) {
            EmailTask emailTask = ApplicationContextProvider.getBean("emailTask", EmailTask.class);
            emailTask.setQueue(queue);
            service.execute(emailTask);
            logger.info("启动邮件发送线程:"+emailTask.getId());
        }
    }
}
