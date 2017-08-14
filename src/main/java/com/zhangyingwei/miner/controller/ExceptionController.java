package com.zhangyingwei.miner.controller;

import com.zhangyingwei.miner.controller.result.Result;
import com.zhangyingwei.miner.exception.MinerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Controller
@ControllerAdvice
public class ExceptionController {
    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ResponseBody
    @ExceptionHandler(value = {BindException.class, MinerException.class, NullPointerException.class})
    public Object bindExceptionHandler(Exception ex) {
        String message = "";
        if (ex instanceof BindException) {
            BindException bex = (BindException) ex;
            message = bex.getFieldError().getDefaultMessage();
        } else {
            message = ex.getMessage();
        }
        message = message.split(":")[1];
        logger.info("hello exception: " + message);
        return Result.error(message);
    }
}
