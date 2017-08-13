package com.zhangyingwei.miner.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zhangyw
 * @date: 2017/8/12
 * @time: 下午10:41
 * @desc:
 */
public class FileUtils {
    public static String read(String path) throws IOException {
        StringBuffer result = new StringBuffer();
        Files.lines(new File(path).toPath()).forEach(line -> result.append(line));
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        String line = "<div class=\"miner_body\">";
        Pattern pattern = Pattern.compile("<div(.*)>");
        Matcher matcher = pattern.matcher(line);
        boolean find = matcher.find();
        System.out.println(find);
        if(find){
            System.out.println(matcher.group());
        }
        System.out.println(line.replaceAll("<div(.*)>","<div>"));
    }
}
