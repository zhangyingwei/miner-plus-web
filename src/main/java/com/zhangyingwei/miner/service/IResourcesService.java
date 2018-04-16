package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.controller.result.PageInfo;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.Resources;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/14.
 */
public interface IResourcesService {
    List<Resources> liseResources() throws MinerException;

    void addResources(Resources resources) throws MinerException;

    List<Resources> listResourcesWithPageAndFlag(PageInfo pageInfo, Integer flag) throws MinerException;

    List<Resources> listResourcesWithPage(PageInfo pageInfo) throws MinerException;

    void removeResourcesByState(String id) throws MinerException;

    void badResources(String id) throws MinerException;

    void goodResources(String id) throws MinerException;

    void updateResources(String id,String resources, String rgroup);

    void deleteResourcesById(String id) throws MinerException;
}
