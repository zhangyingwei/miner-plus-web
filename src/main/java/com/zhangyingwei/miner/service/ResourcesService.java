package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.common.ipip.IP;
import com.zhangyingwei.miner.controller.result.PageInfo;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.mapper.ResourcesMapper;
import com.zhangyingwei.miner.model.Resources;
import com.zhangyingwei.miner.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        if (this.validIp(resources.getSip())) {
            resources.setSarea(Arrays.toString(IP.find(resources.getSip()))
                    .replaceAll("\\[","")
                    .replaceAll("]","")
            );
            logger.info("add resources {} from {} - {}",resources.getResources(),resources.getSip(),resources.getSarea());
        }
        try {
            Resources res = this.resourcesMapper.selectBySite(resources.getResources());
            if (res == null) {
                this.resourcesMapper.addResources(resources);
            } else if (Resources.FLAG_DEL.equals(res.getFlag())){
                this.resourcesMapper.deleteById(res.getId()+"");
                this.resourcesMapper.addResources(resources);
            }else {
                logger.info("资源已经存在");
            }
        } catch (Exception e) {
            throw new MinerException(e);
        }
    }

    private boolean validIp(String sip) {
        if (StringUtils.isBlank(sip)) {
            logger.info("IP 为空");
            return false;
        }
        if (sip.split("\\.").length != 4) {
            logger.info("IP 格式错误. {}", sip);
            return false;
        }
        return true;
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
    public List<Resources> listResourcesWithPage(PageInfo pageInfo) throws MinerException {
        try {
            pageInfo.setTotal(this.resourcesMapper.total(pageInfo,new Resources()));
            return this.resourcesMapper.listResourcesWithPageAndParam(pageInfo, new Resources());
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

    @Override
    public void badResources(String id) throws MinerException {
        try {
            this.resourcesMapper.updateStateById(id, Resources.FLAG_DEL);
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void goodResources(String id) throws MinerException {
        try {
            this.resourcesMapper.updateStateById(id, Resources.FLAG_NOMAL);
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void updateResources(String id,String resources, String rgroup) {
        try {
            Resources params = new Resources();
            params.setResources(resources);
            params.setRgroup(rgroup);
            this.resourcesMapper.updateResourcesById(id, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteResourcesById(String id)  throws MinerException{
        try {
            this.resourcesMapper.deleteById(id);
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }
}
