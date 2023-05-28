package com.example.starwishbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.starwishbackend.common.R;
import com.example.starwishbackend.domain.User;
import com.example.starwishbackend.service.UserService;
import com.example.starwishbackend.utils.JwtUtils;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
//@RequestMapping("/api")
public class UserController {
    @Autowired(required = false)
    private UserService userService;

    //    接收前端发来的login请求
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user) {

        User aUser = new User();
        aUser.setPhoneNum(user.getPhoneNum());
        aUser.setPassword(user.getPassword());
        if (userService.selectUser(aUser) == null) {
            return R.error("账号或密码错误");
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userService.selectUser(aUser).getId());
        claims.put("phoneNum", userService.selectUser(aUser).getPhoneNum());
        claims.put("username", userService.selectUser(aUser).getUsername());

        String jwt = JwtUtils.generateJwt(claims);
        aUser.setJwt(jwt);

        return R.success(aUser);

    }
}
