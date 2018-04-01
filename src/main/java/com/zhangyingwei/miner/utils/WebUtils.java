package com.zhangyingwei.miner.utils;

import com.zhangyingwei.miner.exception.MinerException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zhangyw
 * @date: 2018/4/1
 * @time: 下午8:56
 * @desc:
 */
public class WebUtils {
    private static OkHttpClient client;

    static {
        client = new OkHttpClient().newBuilder().build();
    }
    public static List<String> select(String url,List<String> rules) throws MinerException {
        try {
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                Document html = Jsoup.parse(response.body().string());
                Elements res = html.select("*");
                for (String rule : rules) {
                    res = res.select(rule);
                }
                return res.stream().map(element -> element.text()).collect(Collectors.toList());
            }else {
                throw new Exception("请求失败: " + response.body().string());
            }
        } catch (Exception e) {
            throw new MinerException(e.getLocalizedMessage());
        }
    }
}
