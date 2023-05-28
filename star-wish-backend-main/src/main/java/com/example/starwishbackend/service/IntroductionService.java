package com.example.starwishbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.starwishbackend.domain.Introduction;

import java.util.List;
import java.util.Map;

public interface IntroductionService extends IService<Introduction> {

    List<Map<String, Introduction>> getIntroduction(String phoneNum);

    Introduction findByPhone(String phoneNum);

    void setIntroduction(Introduction introduction);

    void alterIntroduction(Introduction introduction);

    void deleteIntroduction(int item_index);

}
