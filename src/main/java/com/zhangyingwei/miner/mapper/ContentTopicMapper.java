package com.zhangyingwei.miner.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author: zhangyw
 * @date: 2018/4/5
 * @time: 下午10:45
 * @desc:
 */
public interface ContentTopicMapper {

    @Insert("insert into mp_content_topic ( contentid,topicid) values (#{contentid},#{topicid})")
    void addContentTopic(@Param("contentid") Integer contentid, @Param("topicid") Integer topicid);
}
