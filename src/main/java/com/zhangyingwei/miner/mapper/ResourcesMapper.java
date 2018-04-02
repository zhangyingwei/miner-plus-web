package com.zhangyingwei.miner.mapper;

import com.zhangyingwei.miner.controller.result.PageInfo;
import com.zhangyingwei.miner.model.Resources;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Mapper
public interface ResourcesMapper {

    @Select("select * from mp_resources")
    List<Resources> listResources() throws Exception;

    @SelectProvider(type = ResourcesProvider.class,method = "listResourcesWithPageAndParam")
    List<Resources> listResourcesWithPageAndParam(@Param("page") PageInfo pageInfo,@Param("res") Resources resources) throws Exception;

    @SelectProvider(type = ResourcesProvider.class,method = "total")
    Integer total(@Param("page") PageInfo pageInfo,@Param("res") Resources resources);

    @Insert("insert into mp_resources (resources,rtype,createdate,updatedate,flag,sip) values (#{res.resources},#{res.rtype},#{res.createdate},#{res.updatedate},#{res.flag},#{res.sip})")
    void addResources(@Param("res") Resources resources) throws Exception;

    @Select("select * from mp_resources where resources=#{resources}")
    Resources selectBySite(String site) throws Exception;

    class ResourcesProvider {
        public String listResourcesWithPageAndParam(@Param("page") PageInfo pageInfo,@Param("res") Resources resources) {
            StringBuffer sql = new StringBuffer("select * from mp_resources where 1=1 and");
            if (null != resources.getFlag()) {
                sql.append(" flag=#{res.flag} and");
            }
            sql = sql.delete(sql.length() - 4, sql.length());
            sql.append(" order by id desc");
            sql.append(" limit #{page.start},#{page.pageSize}");
            return sql.toString();
        }

        public String total(@Param("page") PageInfo pageInfo,@Param("res") Resources resources) {
            StringBuffer sql = new StringBuffer("select count(*) from mp_resources where 1=1 and");
            if (null != resources.getFlag()) {
                sql.append(" flag=#{res.flag} and");
            }
            sql = sql.delete(sql.length() - 4, sql.length());
            return sql.toString();
        }
    }
}
