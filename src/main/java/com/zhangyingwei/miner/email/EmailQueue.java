package com.zhangyingwei.miner.email;

import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Component
public class EmailQueue {
    private ArrayBlockingQueue<String> queue;

    /**
     * 初始化队列
     */
    public EmailQueue() {
        this.queue = new ArrayBlockingQueue(1024);
    }

    /**
     * @param log
     * @return
     */
    public String push(String log){
        this.queue.add(log);
        return log;
    }

    /**
     * 出队一条 email
     * @return
     */
    public String take() throws InterruptedException {
        return this.queue.take();
    }

    /**
     * 队列大小
     * @return
     */
    public Integer size(){
        return this.queue.size();
    }

    /**
     * 判断队列是否空
     * @return
     */
    public Boolean isEmpty(){
        return this.queue.isEmpty();
    }

    /**
     * 清空队列
     */
    public void clear(){
        this.queue.clear();
    }
}
