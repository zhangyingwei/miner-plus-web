package com.zhangyingwei.miner.controller.result;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2017/8/14.
 */
public class Result {
    private static Map<String, Object> resultMap = new HashMap<String,Object>();

    private static final Integer CODE_SUCCESS = 200;
    private static final String MSG_SUCCESS = "success";
    private static final Integer CODE_ERROR = 9000;
    private static final Integer CODE_NOLOGIN = 501;
    private static final String MSG_ERROR = "error";

    public static Map success(){
        code(CODE_SUCCESS);
        msg(MSG_SUCCESS);
        return resultMap;
    }

    public static Map succes(Object result) {
        code(CODE_SUCCESS);
        msg(MSG_SUCCESS);
        result(result);
        return resultMap;
    }

    public static Map error(){
        code(CODE_ERROR);
        msg(MSG_ERROR);
        return resultMap;
    }

    public static Map error(String message){
        code(CODE_ERROR);
        msg(message);
        return resultMap;
    }

    public static Map message(String message){
        code(CODE_SUCCESS);
        msg(message);
        return resultMap;
    }

    private static void result(Object result) {
        resultMap.put("result", result);
    }

    private static void code(Integer code){
        resultMap.put("code", code);
    }

    private static void msg(String message){
        resultMap.put("message", message);
    }

    public static String nologin() {
        code(CODE_NOLOGIN);
        msg("no login");
        return JSONObject.fromObject(resultMap).toString();
    }
}
