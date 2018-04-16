package com.zhangyingwei.miner.mapper;

import com.zhangyingwei.miner.controller.result.PageInfo;
import com.zhangyingwei.miner.model.Topic;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zhangyw on 2018/4/3.
 */
@Mapper
public interface TopicMapper {

    @SelectProvider(type = TopicMapperProvider.class,method = "listTopicsByParamSql")
    List<Topic> listTopicsByParam(@Param("topic") Topic topic) throws Exception;

    @SelectProvider(type = TopicMapperProvider.class,method = "listTopicsByPageAndParamSql")
    List<Topic> listTopicsByPageAndParam(@Param("page") PageInfo pageInfo, @Param("topic")Topic topic) throws Exception;

    @SelectProvider(type = TopicMapperProvider.class,method = "total")
    Integer total(@Param("topic")Topic topic) throws Exception;

    @Insert("insert into mp_topics ( topic ) values ( #{t.topic} )")
    void addOne(@Param("t") Topic topic) throws Exception;

    @Select("select * from mp_topics where topic=#{topic}")
    Topic selectOne(@Param("topic") String topic) throws Exception;

    @Select("select contentid from mp_content_topic where topicid=#{topicid}")
    List<String> selectUsed(@Param("topicid") String topicid) throws Exception;


    @UpdateProvider(type = TopicMapperProvider.class,method = "updateByNameSql")
    void updateByName(@Param("t") String topic, @Param("param") Topic param) throws Exception;

    @Delete("delete from mp_topics where topic=#{topic}")
    void deleteTopic(String topic);

    class TopicMapperProvider {
        public String updateByNameSql(@Param("t") String topic, @Param("param") Topic param) {
            StringBuffer sql = new StringBuffer("update mp_topics set");
            if (null != param.getFlag()) {
                sql.append(" flag=#{param.flag},");
            }
            sql.delete(sql.length() - 1, sql.length());
            sql.append(" where topic=#{t}");
            return sql.toString();
        }

        public String listTopicsByParamSql(@Param("topic") Topic topic) {
            StringBuffer sql = new StringBuffer("select * from mp_topics where 1=1 and");
            if (null != topic.getFlag()) {
                sql.append(" flag=#{topic.flag} and");
            }
            sql.delete(sql.length() - 3, sql.length());
            sql.append(" order by id desc");
            return sql.toString();
        }

        public String listTopicsByPageAndParamSql(@Param("page") PageInfo pageInfo, @Param("topic")Topic topic) {
            StringBuffer sql = new StringBuffer("select * from mp_topics where 1=1 and");
            if (topic != null) {
                if (null != topic.getFlag()) {
                    sql.append(" flag=#{topic.flag} and");
                }
            }
            sql.delete(sql.length() - 3, sql.length());
            sql.append(" order by id desc limit #{page.start},#{page.pageSize}");
            return sql.toString();
        }

        public String total(@Param("topic")Topic topic) {
            StringBuffer sql = new StringBuffer("select count(*) from mp_topics where 1=1 and");
            if (null != topic.getFlag()) {
                sql.append(" flag=#{topic.flag} and");
            }
            sql.delete(sql.length() - 3, sql.length());
            return sql.toString();
        }
    }
}
