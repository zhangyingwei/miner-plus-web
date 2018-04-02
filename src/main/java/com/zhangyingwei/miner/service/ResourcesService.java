package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.controller.result.PageInfo;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.mapper.ResourcesMapper;
import com.zhangyingwei.miner.model.Resources;
import com.zhangyingwei.miner.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Service
public class ResourcesService implements IResourcesService {
    @Autowired
    private ResourcesMapper resourcesMapper;

    private Logger logger = LoggerFactory.getLogger(ResourcesService.class);


    @Override
    public List<Resources> liseResources() throws MinerException {
        try {
            return this.resourcesMapper.listResources();
        } catch (Exception e) {
            throw new MinerException(e);
        }
    }

    @Override
    public void addResources(Resources resources) throws MinerException {
        resources.setCreatedate(DateUtils.getCurrentDateTime());
        resources.setFlag(Resources.FLAG_INIT);
        try {
            Resources res = this.resourcesMapper.selectBySite(resources.getResources());
            if (res == null) {
                this.resourcesMapper.addResources(resources);
            } else {
                logger.info("资源已经存在");
            }
        } catch (Exception e) {
            throw new MinerException(e);
        }
    }

    @Override
    public List<Resources> listResourcesWithPageAndFlag(PageInfo pageInfo, Integer flag) throws MinerException {
        try {
            Resources resources = new Resources();
            resources.setFlag(flag);
            pageInfo.setTotal(this.resourcesMapper.total(pageInfo,resources));
            return this.resourcesMapper.listResourcesWithPageAndParam(pageInfo,resources);
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void removeResourcesByState(String id) throws MinerException {
        try {
            this.resourcesMapper.updateStateById(id,Resources.FLAG_DEL);
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }
}
