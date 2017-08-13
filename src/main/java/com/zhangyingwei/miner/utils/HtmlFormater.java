package com.zhangyingwei.miner.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author: zhangyw
 * @date: 2017/8/12
 * @time: 下午11:26
 * @desc:
 */
public class HtmlFormater {
    public static String csdnHtmlFormate(String content){
        Document document = Jsoup.parse(content);
        document.select("#article_details").get(0).children().forEach(element -> {
            if((!element.classNames().contains("article_title")) && (!element.classNames().contains("article_content"))){
                element.remove();
            }
        });
        return document.html();
    }
}
