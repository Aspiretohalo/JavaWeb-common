package com.example.starwishbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.starwishbackend.domain.Scenery;
import com.example.starwishbackend.domain.Specialty;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SceneryMapper extends BaseMapper<Scenery> {
    @Select(value = "SELECT * FROM home_scenery WHERE phoneNum=#{phoneNum}")
    Scenery findByPhone(String phoneNum);

    @Select(value = "SELECT * FROM home_scenery WHERE phoneNum=#{phoneNum}")
    List<Map<String, Scenery>> queryScenery(String phoneNum);

    @Select(value = "insert into home_scenery (item_name,item_description,item_detail,phoneNum) values(#{item_name},#{item_description},#{item_detail},#{phoneNum})")
    void setScenery(Scenery scenery);

    @Select(value = "update home_scenery set item_name=#{item_name},item_description=#{item_description},item_detail=#{item_detail} where item_index=#{item_index}")
    void alterScenery(Scenery scenery);

    @Select(value = "delete from home_scenery WHERE item_index=#{item_index}")
    void deleteScenery(Integer item_index);


}
