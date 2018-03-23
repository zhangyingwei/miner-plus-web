package com.zhangyingwei.miner.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by zhangyw on 2017/8/14.
 */
@Getter
@Setter
@ToString
public class Content {
    public static final Integer STATE_INIT = 0;
    public static final Integer STATE_NOMAL = 1;
    public static final Integer STATE_INVALID = 2;
    public static final Integer STATE_NOPASS = 3;
    public static final Integer STATE_PASS = 4;
    public static final Integer STATE_DEL = 9;

    private Integer id;
    private String author;
    private String sitename;
    private String site;
    private String url;
    private String title;
    private String comment;
    private String pubdate;
    private String getdate;
    private String topic;
    private List<String> topics;
    private Integer state;

    public List<String> getTopics() {
        if (null == topic) {
            return new ArrayList<String>();
        }
        return Arrays.asList(topic.split(","));
    }
}
