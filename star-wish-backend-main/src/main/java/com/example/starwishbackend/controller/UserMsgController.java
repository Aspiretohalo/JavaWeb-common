package com.example.starwishbackend.controller;

import com.example.starwishbackend.common.R;
import com.example.starwishbackend.domain.Introduction;
import com.example.starwishbackend.domain.UserMsg;
import com.example.starwishbackend.service.IntroductionService;
import com.example.starwishbackend.service.UserMsgService;
import com.example.starwishbackend.utils.JwtUtils;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class UserMsgController {
    @Autowired(required = false)
    private UserMsgService userMsgService;

    //    接收前端发来的login请求
    @GetMapping("/msg/getMsg")
    public List<Map<String, UserMsg>> getUserMsg(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        List<Map<String, UserMsg>> list;

//        判断收到的jwt有没有bear
        String jwt = req.getHeader("Authorization");
        String[] jwt2 = jwt.split(" ");
        Map<String, Object> claims2;

        if (jwt2.length == 1) {
            log.info("JWT" + jwt);
            claims2 = JwtUtils.parseJWT(jwt);
//        通过phoneNum来获取数据
            list = userMsgService.getUserMsg((String) claims2.get("phoneNum"));
        } else {
            log.info("JWT" + jwt2[1]);

            claims2 = JwtUtils.parseJWT(jwt2[1]);
            list = userMsgService.getUserMsg((String) claims2.get("phoneNum"));

        }
        return list;
    }

    @PutMapping("/msg/alterMsg")
    public R<Object> alterIntroduction(@RequestBody UserMsg userMsg) {
        userMsgService.alterUserMsg(userMsg);
        log.info("修改记录为：{}", userMsg.getItem_name());

        return R.success(null);
    }
}
