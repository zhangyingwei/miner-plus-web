package com.zhangyingwei.miner.mapper;

import com.zhangyingwei.miner.model.Subscribe;
import org.apache.ibatis.annotations.*;

/**
 * Created by zhangyw on 2017/8/14.
 */
@Mapper
public interface SubscribeMapper {
    @Insert("insert into mp_subscribe (email,topics,subdate) values (#{sub.email},#{sub.topics},#{sub.subdate})")
    void addSubscribe(@Param("sub") Subscribe subscribe) throws Exception;

    @Select("select * from mp_subscribe where email=#{email}")
    Subscribe selectByEmail(String email) throws Exception;

    @Update("update mp_subscribe set flag=#{flag} where email=#{email}")
    void updateFlagByEmail(@Param("email") String email,@Param("flag") Integer flag) throws Exception;

    @Update("update mp_subscribe set flag=#{sub.flag},topics=#{sub.topics} where email=#{sub.email}")
    void updateByEmail(@Param("sub") Subscribe subscribe) throws Exception;
}
