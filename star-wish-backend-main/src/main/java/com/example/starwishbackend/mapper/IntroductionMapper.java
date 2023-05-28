package com.example.starwishbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.starwishbackend.domain.Introduction;
import com.example.starwishbackend.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface IntroductionMapper extends BaseMapper<Introduction> {
    @Select(value = "SELECT * FROM home_introduction WHERE phoneNum=#{phoneNum}")
    Introduction findByPhone(String phoneNum);
    @Select(value = "SELECT * FROM home_introduction WHERE phoneNum=#{phoneNum}")
    List<Map<String, Introduction>> queryIntroduction(String phoneNum);
    @Select(value = "insert into home_introduction (item_name,item_description,item_detail,phoneNum) values(#{item_name},#{item_description},#{item_detail},#{phoneNum})")
    void setIntroduction(Introduction introduction);

    @Select(value = "update home_introduction set item_name=#{item_name},item_description=#{item_description},item_detail=#{item_detail} where item_index=#{item_index}")
    void alterIntroduction(Introduction introduction);

    @Select(value = "delete from home_introduction WHERE item_index=#{item_index}")
    void deleteIntroduction(Integer item_index);


}
