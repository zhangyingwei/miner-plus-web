package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.mapper.ResourcesMapper;
import com.zhangyingwei.miner.mapper.RuleMapper;
import com.zhangyingwei.miner.model.ResRule;
import com.zhangyingwei.miner.model.Resources;
import com.zhangyingwei.miner.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangyw on 2018/4/2.
 */
@Service
public class RuleService {
    private Logger logger = LoggerFactory.getLogger(RuleService.class);
    @Autowired
    private RuleMapper ruleMapper;
    @Autowired
    private ResourcesMapper resourcesMapper;

    public List<String> testRule(String url, ResRule resRule) throws MinerException {
        try {
            return WebUtils.select(url, resRule);
        } catch (MinerException e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }

    @Transactional
    public void addRules(String uuid, ResRule title, String type, ResRule url, ResRule desc, ResRule pubdate) throws Exception {
        this.ruleMapper.addOne(title);
        this.ruleMapper.addOne(url);
        this.ruleMapper.addOne(desc);
        this.ruleMapper.addOne(pubdate);
        Resources resources = new Resources();
        resources.setFlag(Resources.FLAG_NOMAL);
        resources.setRgroup(type);
        this.resourcesMapper.updateResourcesById(uuid, resources);
    }
}
