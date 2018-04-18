package com.zhangyingwei.miner.controller;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import com.zhangyingwei.miner.exception.MinerException;
import com.zhangyingwei.miner.model.Content;
import com.zhangyingwei.miner.model.Topic;
import com.zhangyingwei.miner.service.ContentService;
import com.zhangyingwei.miner.utils.DateUtils;
import com.zhangyingwei.miner.utils.FileUtils;
import com.zhangyingwei.miner.utils.HtmlFormater;
import com.zhangyingwei.miner.utils.WebUtils;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author: zhangyw
 * @date: 2017/8/12
 * @time: 下午9:17
 * @desc:
 */
@Controller
@RequestMapping
@Log4j
public class IndexController {

    @Autowired
    private ContentService contentService;

//    @GetMapping
//    public String index(Map<String,Object> model) throws IOException {
//        String path = "src/main/resources/templates/content.html";
//        String content = FileUtils.read(path);
//        model.put("html", HtmlFormater.csdnHtmlFormate(content));
//        return "index";
//    }

    @GetMapping
    public String main(@RequestParam(value = "date",required = false) String date, Map<String,Object> model) throws MinerException {
        if (StringUtils.isBlank(date)) {
            date = DateUtils.getCurrentDate();
        }
        log.info("get contents of data : " + date);
        List<Content> contents = this.contentService.listNomalContentsToDayTop10(date);
        List<Topic> topics = this.contentService.listTopics();
        model.put("contents", contents);
        model.put("topics", Optional.ofNullable(topics).orElse(new ArrayList<Topic>()));
        return "main";
    }

    @GetMapping(value = "/rss", produces = { "application/xml;charset=UTF-8" })
    @ResponseBody
    public String rss(@RequestParam(value = "date", required = false) String date, HttpServletResponse response) throws MinerException {
        if (StringUtils.isBlank(date)) {
            date = DateUtils.getCurrentDate();
        }
        List<Content> contents = this.contentService.listNomalContentsToDayTop10(date);
        try {
            String feed = WebUtils.bulidRss(contents);
            return feed;
        } catch (FeedException e) {
            throw new MinerException(e.getLocalizedMessage());
        }

    }
}
