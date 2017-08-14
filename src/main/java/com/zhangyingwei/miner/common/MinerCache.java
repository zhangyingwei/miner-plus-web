package com.zhangyingwei.miner.common;

import com.zhangyingwei.miner.utils.DateUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2017/8/14.
 */
public class MinerCache {
    private static Map<String, String> keyLive = new HashMap<String,String>();
    private static Map<String, String> cacheMap = new HashMap<String,String>();

    public static String get(String key){
        if(keyLive.containsKey(key)){
            String[] value = keyLive.get(key).split(",");
            Long start = Long.parseLong(value[0]);
            Long current = System.currentTimeMillis();
            Integer live = Integer.parseInt(value[1]);
            if((current - start) > live){
                return null;
            }else{
                return cacheMap.get(key);
            }
        }else{
            return cacheMap.get(key);
        }
    }

    public static void put(String key,String value){
        cacheMap.put(key, value);
    }

    public static void put(String key,String value,Integer liveMillis){
        cacheMap.put(key, value);
        keyLive.put(key, System.currentTimeMillis()+","+liveMillis);
    }

}
