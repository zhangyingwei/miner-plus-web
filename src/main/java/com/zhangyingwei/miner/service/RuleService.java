package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.ResRule;
import com.zhangyingwei.miner.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangyw on 2018/4/2.
 */
@Service
public class RuleService {
    private Logger logger = LoggerFactory.getLogger(RuleService.class);

    public List<String> testRule(String url, ResRule resRule) throws MinerException {
        try {
            return WebUtils.select(url, resRule);
        } catch (MinerException e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }
}
