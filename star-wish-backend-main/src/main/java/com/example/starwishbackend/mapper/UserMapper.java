package com.example.starwishbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.starwishbackend.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select(value = "select * from user where phoneNum=#{phoneNum} and password = #{password}")
//使用注解方式，也可用xml方式（编写.xml文件放在resources下且要在application.yml中配置localtion等）
    User selectOneUser (User u);
}
