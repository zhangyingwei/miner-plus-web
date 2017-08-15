package com.zhangyingwei.miner.email;

import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.utils.EmailUtils;
import com.zhangyingwei.smail.Smail;
import com.zhangyingwei.smail.config.SmailConfig;
import com.zhangyingwei.smail.exception.SmailException;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Created by zhangyw on 2017/8/15.
 */
@Component
public class EmailSender {
    @Value("${miner.email.username}")
    private String username;
    @Value("${miner.email.password}")
    private String password;
    private Logger logger = LoggerFactory.getLogger(EmailSender.class);

    public boolean send(String email,String title,String content){
        try {
            logger.info("from : " + username);
            logger.info("to : "+email);
            logger.info("title : " + title);
            logger.info("content : " + content);
            new Smail(new SmailConfig().setNikename("Miner").setStarttls(true))
                    .auth(this.username,this.password)
                    .to(email)

                    .send(title,content);
            return true;
        } catch (UnsupportedEncodingException | MessagingException | SmailException e) {
            logger.info(e.getMessage());
            return false;
        }
    }
}
