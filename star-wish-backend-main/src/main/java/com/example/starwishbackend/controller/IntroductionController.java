package com.example.starwishbackend.controller;

import com.example.starwishbackend.common.R;
import com.example.starwishbackend.domain.Introduction;
import com.example.starwishbackend.domain.User;
import com.example.starwishbackend.service.IntroductionService;
import com.example.starwishbackend.service.UserService;
import com.example.starwishbackend.utils.JwtUtils;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class IntroductionController {
    @Autowired(required = false)
    private IntroductionService introductionService;

    //    接收前端发来的login请求
    @GetMapping("/msg/getIntroduction")
    public List<Map<String, Introduction>> getIntroduction(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        List<Map<String, Introduction>> list;

//        判断收到的jwt有没有bear
        String jwt = req.getHeader("Authorization");
        String[] jwt2 = jwt.split(" ");
        Map<String, Object> claims2;

        if (jwt2.length == 1) {
            log.info("JWT" + jwt);
            claims2 = JwtUtils.parseJWT(jwt);
//        通过phoneNum来获取数据
            list = introductionService.getIntroduction((String) claims2.get("phoneNum"));
        } else {
            log.info("JWT" + jwt2[1]);

            claims2 = JwtUtils.parseJWT(jwt2[1]);
            list = introductionService.getIntroduction((String) claims2.get("phoneNum"));
        }
        return list;
    }

    @PostMapping("/msg/setIntroduction")
    public R<Object> setIntroduction(@RequestBody Introduction introduction) {
        log.info(introduction.getPhoneNum());
        introductionService.setIntroduction(introduction);
        log.info("存入新记录：{}", introduction.getItem_name());

        return R.success(null);
    }

    @PutMapping("/msg/alterIntroduction")
    public R<Object> alterIntroduction(@RequestBody Introduction introduction) {
        introductionService.alterIntroduction(introduction);
        log.info("修改记录为：{}", introduction.getItem_name());

        return R.success(null);
    }

    @DeleteMapping("/msg/deleteIntroduction/{item_index}")
    public R<Object> deleteCategory(@PathVariable int item_index) {
        introductionService.deleteIntroduction(item_index);
        log.info("该标签已删除：{}", item_index);

        return R.success(null);
    }
}
