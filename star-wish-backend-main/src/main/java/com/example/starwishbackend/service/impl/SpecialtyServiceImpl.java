package com.example.starwishbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.starwishbackend.domain.Introduction;
import com.example.starwishbackend.domain.Specialty;
import com.example.starwishbackend.mapper.IntroductionMapper;
import com.example.starwishbackend.mapper.SpecialtyMapper;
import com.example.starwishbackend.service.IntroductionService;
import com.example.starwishbackend.service.SpecialtyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class SpecialtyServiceImpl extends ServiceImpl<SpecialtyMapper, Specialty> implements SpecialtyService {
    @Autowired(required = false)
    private SpecialtyMapper specialtyMapper;


    @Override
    public List<Map<String, Specialty>> getSpecialty(String phoneNum) {
        List<Map<String, Specialty>> resultArray = specialtyMapper.querySpecialty(phoneNum);

        if (resultArray == null) {
//            "请求的数据不存在"
            return null;
        }
        log.info(String.valueOf(resultArray));

        return resultArray;
    }

    @Override
    public Specialty findByPhone(String phoneNum) {
        return null;
    }

    @Override
    public void setSpecialty(Specialty specialty) {

    }

    @Override
    public void alterSpecialty(Specialty specialty) {

    }

    @Override
    public void deleteSpecialty(int item_index) {

    }
}

