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
}
