package com.zhangyingwei.miner.utils;

import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import com.zhangyingwei.miner.common.MinerEnum;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.Content;
import com.zhangyingwei.miner.model.ResRule;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: zhangyw
 * @date: 2018/4/1
 * @time: 下午8:56
 * @desc:
 */
public class WebUtils {
    private static Logger logger = LoggerFactory.getLogger(WebUtils.class);
    private static OkHttpClient client;

    static {
        try {
            SslUtils.ignoreSsl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        client = new OkHttpClient().newBuilder().build();
    }
    public static List<String> select(String url, ResRule rule) throws MinerException {
        try {
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                Document html = Jsoup.parse(response.body().string());
                Elements res = html.select("*");
                List<String> rules = rule.bulidRules();
                for (String r : rules) {
                    logger.info(r);
                    res = res.select(r);
                }
                List<String> resRules = null;
                if (MinerEnum.ATTR_TEXT.getValue().equals(rule.getAttr()) || StringUtils.isBlank(rule.getAttr())) {
                    resRules = res.stream().map(element -> element.text()).collect(Collectors.toList());
                }else{
                    resRules = res.stream().map(element -> element.attr(rule.getAttr())).collect(Collectors.toList());
                }
                return resRules.stream().map(text -> {
                    if (StringUtils.isNotEmpty(rule.getPrefix())) {
                        text = rule.getPrefix() + text;
                    }
                    if (StringUtils.isNotEmpty(rule.getSuffix())) {
                        text = text + rule.getSuffix();
                    }
                    return text;
                }).filter(StringUtils::isNotBlank).collect(Collectors.toList());
            }else {
                throw new Exception("请求失败: " + response.body().string());
            }
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }


    /**
     * 获取用户真实ip
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String bulidRss(List<Content> contents) throws FeedException {
        SyndFeed feed = new SyndFeedImpl();
        feed.setEncoding("utf-8");
        feed.setAuthor("张英伟");
        feed.setDescription("一个内容订阅服务，给自己用的，如果你想用我也不会拒绝。主要内容包含 java、scala、python、机器学习、大数据、安全、前端等一系列我感兴趣的东西。如果暂时没收录到你的关注点，来来咱俩交流交流感情。");
        feed.setTitle("Miner 也许是国内外第二烂的内容订阅服务");
        feed.setLink("http://miner.zhangyingwei.com/");
        feed.setFeedType("rss_2.0");
        List<SyndEntry> entires = contents.stream().map(content -> {
            SyndEntry entry = new SyndEntryImpl();
            entry.setTitle(content.getTitle());
            entry.setLink(content.getUrl());
            entry.setAuthor("张英伟");
            SyndContent scontent = new SyndContentImpl();
            scontent.setValue(Optional.ofNullable(content.getComment()).orElse(""));
            entry.setDescription(scontent);
            entry.setCategories(content.getTopics().stream().map(t -> {
                SyndCategory category = new SyndCategoryImpl();
                category.setName(t);
                return category;
            }).collect(Collectors.toList()));
            return entry;
        }).collect(Collectors.toList());
        feed.setEntries(entires);

        SyndFeedOutput output = new SyndFeedOutput();
        return output.outputString(feed);
    }
}
