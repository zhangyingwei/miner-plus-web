package com.zhangyingwei.miner.mapper;

import com.zhangyingwei.miner.model.ResRule;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhangyw on 2018/4/3.
 */
@Mapper
public interface RuleMapper {

    @Insert("insert into mp_resources_rules(url,rule,prefix,suffix,attr,rtype) values (#{rule.url},#{rule.rule},#{rule.prefix},#{rule.suffix},#{rule.attr},#{rule.rtype})")
    void addOne(@Param("rule") ResRule resRule) throws Exception;
}
