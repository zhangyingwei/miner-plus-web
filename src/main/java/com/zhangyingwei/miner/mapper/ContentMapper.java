package com.zhangyingwei.miner.mapper;

import com.zhangyingwei.miner.model.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Mapper
public interface ContentMapper {

    @Select("select * from mp_content where getdate like #{getdate} order by getdate")
    List<Content> listContentsByGetDate(@Param("getdate") String getDate) throws Exception;

    @Select("select * from mp_content where getdate like #{getdate} order by getdate limit #{limit}")
    List<Content> listContentsByGetDateLimit(@Param("getdate") String toDay, @Param("limit") int limit);
}
