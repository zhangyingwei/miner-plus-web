package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.MinerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by zhangyw on 2017/8/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MinerApplication.class)
public class ResourcesServiceTest {

    @Autowired
    private IResourcesService resourcesService;

    @Test
    public void liseResources() throws Exception {
        this.resourcesService.liseResources();
    }

}