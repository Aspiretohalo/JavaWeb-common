package com.example.starwishbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.starwishbackend.domain.Specialty;
import com.example.starwishbackend.domain.UserMsg;
import com.example.starwishbackend.mapper.SpecialtyMapper;
import com.example.starwishbackend.mapper.UserMsgMapper;
import com.example.starwishbackend.service.SpecialtyService;
import com.example.starwishbackend.service.UserMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserMsgServiceImpl extends ServiceImpl<UserMsgMapper, UserMsg> implements UserMsgService {
    @Autowired(required = false)
    private UserMsgMapper userMsgMapper;


    @Override
    public List<Map<String, UserMsg>> getUserMsg(String phoneNum) {
        List<Map<String, UserMsg>> resultArray = userMsgMapper.queryUserMsg(phoneNum);

        if (resultArray == null) {
//            "请求的数据不存在"
            return null;
        }
        log.info(String.valueOf(resultArray));

        return resultArray;
    }

    @Override
    public UserMsg findByPhone(String phoneNum) {
        return userMsgMapper.findByPhone(phoneNum);
    }

    @Override
    public void alterUserMsg(UserMsg userMsg) {
        userMsgMapper.alterUserMsg(userMsg);
    }
}
