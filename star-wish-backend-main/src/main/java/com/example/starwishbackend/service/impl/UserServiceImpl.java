package com.example.starwishbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.starwishbackend.domain.User;
import com.example.starwishbackend.mapper.UserMapper;
import com.example.starwishbackend.repository.UserDao;
import com.example.starwishbackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUser(User u){
        if(u.getPhoneNum()==null||u.getPassword()==null){
            return null;
        }
        return userMapper.selectOneUser(u);
    }

}
