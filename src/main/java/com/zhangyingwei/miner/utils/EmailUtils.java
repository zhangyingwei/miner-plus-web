package com.zhangyingwei.miner.utils;

import com.zhangyingwei.miner.common.MinerCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by zhangyw on 2017/8/15.
 */
public class EmailUtils {

    private static Logger logger = LoggerFactory.getLogger(EmailUtils.class);
    private static final Integer live = 5 * 60 * 1000;
    private static final String URL_DEFAULT = "checkurl";

    public synchronized static String checkContent(String email) {
        try {
            String content = FileUtils.read(ResourceUtils.getURL("classpath:templates/temp/checkemailtemp.html").getPath());
            String uuid = UUID.randomUUID().toString();
            MinerCache.put(email,uuid,live);
            String url = "http://miner.zhangyingwei.com/subscribe/check/"+email+"/"+uuid;
            content = content.replace(URL_DEFAULT, url);
            return content;
        } catch (IOException e) {
            logger.info(e.getMessage());
            return "";
        }
    }

    public static String checkTitle() {
        return "Miner 订阅验证";
    }
}
