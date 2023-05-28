package com.example.starwishbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.starwishbackend.domain.Specialty;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SpecialtyMapper extends BaseMapper<Specialty> {
    @Select(value = "SELECT * FROM home_specialty WHERE phoneNum=#{phoneNum}")
    Specialty findByPhone(String phoneNum);
    @Select(value = "SELECT * FROM home_specialty WHERE phoneNum=#{phoneNum}")
    List<Map<String, Specialty>> querySpecialty(String phoneNum);
    @Select(value = "insert into home_specialty (item_name,item_description,item_detail) values(#{item_name},#{item_description},#{item_detail})")
    void setSpecialty(Specialty specialty);

    @Select(value = "update home_specialty set item_name=#{item_name},item_description=#{item_description},item_detail=#{item_detail} where item_index=#{item_index}")
    void alterSpecialty(Specialty specialty);

    @Select(value = "delete from home_specialty WHERE item_index=#{item_index}")
    void deleteSpecialty(Integer item_index);


}
