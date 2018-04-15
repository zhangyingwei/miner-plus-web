package com.zhangyingwei.miner.mapper;

import com.zhangyingwei.miner.controller.result.PageInfo;
import com.zhangyingwei.miner.model.Content;
import com.zhangyingwei.miner.model.PushCount;
import com.zhangyingwei.miner.model.Topic;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;

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

    @SelectProvider(type = ContentMapperProvider.class,method = "listContentsWithPageAndParamSql")
    List<Content> listContentsWithPageAndParam(@Param("page") PageInfo pageInfo, @Param("content") Content content) throws Exception;

    @SelectProvider(type = ContentMapperProvider.class,method = "total")
    Integer total(@Param("content") Content content) throws Exception;

    @UpdateProvider(type = ContentMapperProvider.class ,method = "updateById")
    void updateById(@Param("id") String id, @Param("content") Content content) throws Exception;

    @SelectProvider(type = ContentMapperProvider.class,method = "count")
    Integer getCountByParam(@Param("content")Content content) throws Exception;

    @Select("select pushdate,count(1) as count from mp_content where state=4 and (pushdate >=#{nowdate} or pushdate is null) group by pushdate")
    List<PushCount> listPushCountAfter(@Param("nowdate")String nowdate) throws Exception;

    class ContentMapperProvider {
        public String listContentsWithPageAndParamSql(@Param("page") PageInfo pageInfo, @Param("content") Content content){
            StringBuffer sql = new StringBuffer("select * from mp_content where 1=1 and");
            if (null != content.getState()) {
                sql.append(" state=#{content.state} and");
            }
            if (StringUtils.isNotEmpty(content.getPushdate())) {
                sql.append(" (pushdate is null or pushdate >= #{content.pushdate}) and");
            }
            sql.delete(sql.length() - 3, sql.length());
            sql.append(" order by pushdate,id desc limit #{page.start},#{page.pageSize}");
            return sql.toString();
        }

        public String count(@Param("content") Content content){
            StringBuffer sql = new StringBuffer("select count(*) from mp_content where 1=1 and");
            if (null != content.getState()) {
                sql.append(" state=#{content.state} and");
            }
            if (StringUtils.isNotEmpty(content.getGetdate())) {
                sql.append(" getdate like #{content.getdate} and");
            }
            sql.delete(sql.length() - 3, sql.length());
            return sql.toString();
        }

        public String total(@Param("content") Content content){
            StringBuffer sql = new StringBuffer("select count(*) from mp_content where 1=1 and");
            if (null != content.getState()) {
                sql.append(" state=#{content.state} and");
            }
            if (StringUtils.isNotEmpty(content.getPushdate())) {
                sql.append(" (pushdate is null or pushdate >= #{content.pushdate}) and");
            }
            sql.delete(sql.length() - 3, sql.length());
            return sql.toString();
        }

        public String updateById(@Param("id") String id, @Param("content") Content content) {
            StringBuffer sql = new StringBuffer("update mp_content set");
            Boolean has = false;
            if (null != content.getState()) {
                sql.append(" state=#{content.state},");
                has = true;
            }
            if (StringUtils.isNotEmpty(content.getComment())) {
                sql.append(" comment=#{content.comment},");
                has = true;
            }
            if (StringUtils.isNotEmpty(content.getPushdate())) {
                sql.append(" pushdate=#{content.pushdate},");
                has = true;
            }
            sql.delete(sql.length() - 1, sql.length());
            if (!has) {
                sql.delete(sql.length() - 3, sql.length());
            }
            sql.append(" where id=#{id}");
            return sql.toString();
        }
    }
}
