package com.example.starwishbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.starwishbackend.domain.Introduction;
import com.example.starwishbackend.domain.User;
import com.example.starwishbackend.mapper.IntroductionMapper;
import com.example.starwishbackend.mapper.UserMapper;
import com.example.starwishbackend.service.IntroductionService;
import com.example.starwishbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class IntroductionServiceImpl extends ServiceImpl<IntroductionMapper, Introduction> implements IntroductionService {
    @Autowired(required = false)
    private IntroductionMapper introductionMapper;

    @Override
    public List<Map<String, Introduction>> getIntroduction(String phoneNum) {
        List<Map<String, Introduction>> resultArray = introductionMapper.queryIntroduction(phoneNum);

        if (resultArray == null) {
//            "请求的数据不存在"
            return null;
        }
        log.info(String.valueOf(resultArray));

        return resultArray;
    }

    @Override
    public Introduction findByPhone(String phoneNum) {
        return introductionMapper.findByPhone(phoneNum);
    }

    @Override
    public void setIntroduction(Introduction introduction) {
        introductionMapper.setIntroduction(introduction);
    }

    @Override
    public void alterIntroduction(Introduction introduction) {
        introductionMapper.alterIntroduction(introduction);
    }

    @Override
    public void deleteIntroduction(int item_index) {
        introductionMapper.deleteIntroduction(item_index);
    }
}
