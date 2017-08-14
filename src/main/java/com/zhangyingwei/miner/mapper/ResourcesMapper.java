package com.zhangyingwei.miner.mapper;

import com.zhangyingwei.miner.model.Resources;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Mapper
public interface ResourcesMapper {

    @Select("select * from mp_resources")
    List<Resources> listResources() throws Exception;

    @Insert("insert into mp_resources (resources,type,createdate,updatedate,flag) values (#{res.resources},#{res.type},#{res.createdate},#{res.updatedate},#{res.flag})")
    void addResources(@Param("res") Resources resources) throws Exception;

    @Select("select * from mp_resources where resources=#{resources}")
    Resources selectBySite(String site) throws Exception;
}
