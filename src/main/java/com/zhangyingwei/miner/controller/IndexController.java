package com.zhangyingwei.miner.controller;

import com.zhangyingwei.miner.utils.FileUtils;
import com.zhangyingwei.miner.utils.HtmlFormater;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2017/8/12
 * @time: 下午9:17
 * @desc:
 */
@Controller
@RequestMapping
public class IndexController {

    @GetMapping
    public String index(Map<String,Object> model) throws IOException {
        String path = "src/main/resources/templates/content.html";
        String content = FileUtils.read(path);
        model.put("html", HtmlFormater.csdnHtmlFormate(content));
        return "index";
    }

    @GetMapping("main")
    public String main(Map<String,String> model){
        return "main";
    }
}
