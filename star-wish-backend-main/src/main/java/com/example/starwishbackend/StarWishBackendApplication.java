package com.example.starwishbackend;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@ServletComponentScan //开启了对servlet组件的支持
@SpringBootApplication
@MapperScan("com.example.starwishbackend.mapper")
//@ServletComponentScan("com.example.starwishbackend.filter")
public class StarWishBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarWishBackendApplication.class, args);
        log.info("项目启动成功");
    }

}
