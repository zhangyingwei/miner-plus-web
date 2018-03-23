package com.zhangyingwei.miner.mapper;

import com.zhangyingwei.miner.model.Content;
import com.zhangyingwei.miner.model.Topic;
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

    @Select("select * from mp_content where state=#{state} and getdate like #{getdate} order by getdate")
    List<Content> listContentsByGetDateAndState(@Param("getdate") String getDate,@Param("state") Integer state) throws Exception;

    @Select("select contenttopic.*,group_concat(t.topic) as topic from (select c.* ,ct.topicid from mp_content c left join mp_content_topic ct on ct.contentid=c.id) as contenttopic left join mp_topics t on contenttopic.topicid=t.id where contenttopic.state=#{state} and contenttopic.pushdate like #{pushdate} group by contenttopic.id order by getdate limit #{limit}")
    List<Content> listContentsByPushDateAndStateLimit(@Param("pushdate") String toDay, @Param("limit") int limit,@Param("state") Integer state);

    @Select("select * from mp_topics where flag=#{flag}")
    List<Topic> listTopicsByState(@Param("flag") Integer flag) throws Exception;
}
