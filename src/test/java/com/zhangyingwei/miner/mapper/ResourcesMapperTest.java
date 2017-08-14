package com.zhangyingwei.miner.mapper;

import com.zhangyingwei.miner.MinerApplication;
import com.zhangyingwei.miner.model.Resources;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhangyw on 2017/8/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MinerApplication.class)
public class ResourcesMapperTest {
    @Autowired
    private ResourcesMapper resourcesMapper;
    @Test
    public void listResources() throws Exception {
        List<Resources> res = resourcesMapper.listResources();
        System.out.println(res);
        Assert.assertNotNull(res);
    }

    @Test
    public void addResources() throws Exception {
        Resources resources = new Resources();
        resources.setResources("http://zhangyingwei.com");
        resources.setCreatedate("2017-08-14 00:00:00");
        this.resourcesMapper.addResources(resources);
    }

}