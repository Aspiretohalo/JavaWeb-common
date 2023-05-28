package com.example.starwishbackend.controller;

import com.example.starwishbackend.domain.Scenery;
import com.example.starwishbackend.domain.Specialty;
import com.example.starwishbackend.service.SceneryService;
import com.example.starwishbackend.service.SpecialtyService;
import com.example.starwishbackend.utils.JwtUtils;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@Slf4j
@RestController
public class SceneryController {
    @Autowired(required = false)
    private SceneryService sceneryService;

    //    接收前端发来的login请求
    @GetMapping("/msg/getScenery")
    public List<Map<String, Scenery>> getScenery(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        List<Map<String, Scenery>> list;

//        判断收到的jwt有没有bear
        String jwt = req.getHeader("Authorization");
        String[] jwt2 = jwt.split(" ");
        Map<String, Object> claims2;

        if (jwt2.length == 1) {
            log.info("JWT" + jwt);
            claims2 = JwtUtils.parseJWT(jwt);
//        通过phoneNum来获取数据
            list = sceneryService.getScenery((String) claims2.get("phoneNum"));
        } else {
            log.info("JWT" + jwt2[1]);

            claims2 = JwtUtils.parseJWT(jwt2[1]);
            list = sceneryService.getScenery((String) claims2.get("phoneNum"));
        }

        return list;
    }
}
