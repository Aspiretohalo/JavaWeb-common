package com.example.starwishbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.starwishbackend.domain.User;

public interface UserService extends IService<User> {
//    User loginService(String phoneNum, String password);

    User selectUser(User u);
}
