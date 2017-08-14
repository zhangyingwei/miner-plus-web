package com.zhangyingwei.miner.common;

import com.zhangyingwei.miner.email.EmailHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Component
public class MinerListener implements ApplicationListener {
    private Logger logger = LoggerFactory.getLogger(MinerListener.class);
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //我也不知道为什么，不加这一句方法就会被执行三次，如果加上就执行一次，那就加上喽
        ContextRefreshedEvent event = (ContextRefreshedEvent) applicationEvent;
        logger.info("初始化 Miner 邮件发送器");
        EmailHandler emailHandler = ApplicationContextProvider.getBean("emailHandler", EmailHandler.class);
        emailHandler.start();
        logger.info("Miner 邮件发送器初始化成功");
    }
}
