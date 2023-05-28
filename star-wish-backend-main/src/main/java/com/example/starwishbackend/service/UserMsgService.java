package com.example.starwishbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.starwishbackend.domain.Specialty;
import com.example.starwishbackend.domain.UserMsg;

import java.util.List;
import java.util.Map;

public interface UserMsgService extends IService<UserMsg> {

    List<Map<String, UserMsg>> getUserMsg(String phoneNum);

    UserMsg findByPhone(String phoneNum);

    void alterUserMsg(UserMsg userMsg);

}