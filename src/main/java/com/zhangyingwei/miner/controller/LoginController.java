package com.zhangyingwei.miner.controller;

import com.zhangyingwei.miner.controller.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhangyw on 2018/4/3.
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @PostMapping("/login")
    public Map login(HttpServletRequest request,String username, String password) {
        HttpSession session = request.getSession();
        if ("admin".equals(username) && "723129Miner".equals(password)) {
            String token = UUID.randomUUID().toString();
            session.setAttribute("miner-token", token);
            return Result.succes(token);
        }
        return Result.error("用户名密码错误");
    }
}
