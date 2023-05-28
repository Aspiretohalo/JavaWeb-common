package com.example.starwishbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.starwishbackend.domain.Scenery;
import com.example.starwishbackend.domain.Specialty;
import com.example.starwishbackend.mapper.SceneryMapper;
import com.example.starwishbackend.mapper.SpecialtyMapper;
import com.example.starwishbackend.service.SceneryService;
import com.example.starwishbackend.service.SpecialtyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class SceneryServiceImpl extends ServiceImpl<SceneryMapper, Scenery> implements SceneryService {
    @Autowired(required = false)
    private SceneryMapper sceneryMapper;

    @Override
    public List<Map<String, Scenery>> getScenery(String phoneNum) {
        List<Map<String, Scenery>> resultArray = sceneryMapper.queryScenery(phoneNum);

        if (resultArray == null) {
//            "请求的数据不存在"
            return null;
        }
        log.info(String.valueOf(resultArray));

        return resultArray;
    }

    @Override
    public Scenery findByPhone(String phoneNum) {
        return null;
    }

    @Override
    public void setScenery(Scenery scenery) {

    }

    @Override
    public void alterScenery(Scenery scenery) {

    }

    @Override
    public void deleteScenery(int item_index) {

    }
}
