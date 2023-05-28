package com.example.starwishbackend.controller;

import com.example.starwishbackend.common.R;
import com.example.starwishbackend.domain.Introduction;
import com.example.starwishbackend.domain.Scenery;
import com.example.starwishbackend.domain.Specialty;
import com.example.starwishbackend.service.IntroductionService;
import com.example.starwishbackend.service.SpecialtyService;
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
public class SpecialtyController {
    @Autowired(required = false)
    private SpecialtyService specialtyService;

    //    接收前端发来的login请求
    @GetMapping("/msg/getSpecialty")
    public List<Map<String, Specialty>> getSpecialty(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        List<Map<String, Specialty>> list;

//        判断收到的jwt有没有bear
        String jwt = req.getHeader("Authorization");
        String[] jwt2 = jwt.split(" ");
        Map<String, Object> claims2;

        if (jwt2.length == 1) {
            log.info("JWT" + jwt);
            claims2 = JwtUtils.parseJWT(jwt);
//        通过phoneNum来获取数据
            list = specialtyService.getSpecialty((String) claims2.get("phoneNum"));
        } else {
            log.info("JWT" + jwt2[1]);

            claims2 = JwtUtils.parseJWT(jwt2[1]);
            list = specialtyService.getSpecialty((String) claims2.get("phoneNum"));
        }

        return list;
    }
    @PostMapping("/msg/setSpecialty")
    public R<Object> setScenery(@RequestBody Specialty specialty) {
        log.info(specialty.getPhoneNum());
        specialtyService.setSpecialty(specialty);
        log.info("存入新记录：{}", specialty.getItem_name());

        return R.success(null);
    }

    @PutMapping("/msg/alterSpecialty")
    public R<Object> alterScenery(@RequestBody Specialty specialty) {
        specialtyService.alterSpecialty(specialty);
        log.info("修改记录为：{}", specialty.getItem_name());

        return R.success(null);
    }

    @DeleteMapping("/msg/deleteSpecialty/{item_index}")
    public R<Object> deleteSpecialty(@PathVariable int item_index) {
        specialtyService.deleteSpecialty(item_index);
        log.info("该标签已删除：{}", item_index);

        return R.success(null);
    }
}
