package com.example.starwishbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.starwishbackend.domain.Specialty;
import com.example.starwishbackend.domain.UserMsg;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMsgMapper extends BaseMapper<UserMsg> {
    @Select(value = "SELECT * FROM user_msg WHERE phoneNum=#{phoneNum}")
    UserMsg findByPhone(String phoneNum);
    @Select(value = "SELECT * FROM user_msg WHERE phoneNum=#{phoneNum}")
    List<Map<String, UserMsg>> queryUserMsg(String phoneNum);

    @Select(value = "update user_msg set item_name=#{item_name},item_description=#{item_description},item_detail=#{item_detail} where item_index=#{item_index}")
    void alterUserMsg(UserMsg userMsg);

}