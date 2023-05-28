package com.example.starwishbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.starwishbackend.domain.Scenery;
import com.example.starwishbackend.domain.Specialty;

import java.util.List;
import java.util.Map;

public interface SceneryService extends IService<Scenery> {

    List<Map<String, Scenery>> getScenery(String phoneNum);

    Scenery findByPhone(String phoneNum);

    void setScenery(Scenery scenery);

    void alterScenery(Scenery scenery);

    void deleteScenery(int item_index);

}
