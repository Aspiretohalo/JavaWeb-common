package com.example.starwishbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.starwishbackend.domain.Introduction;
import com.example.starwishbackend.domain.Specialty;

import java.util.List;
import java.util.Map;

public interface SpecialtyService extends IService<Specialty> {

    List<Map<String, Specialty>> getSpecialty(String phoneNum);

    Specialty findByPhone(String phoneNum);

    void setSpecialty(Specialty specialty);

    void alterSpecialty(Specialty specialty);

    void deleteSpecialty(int item_index);

}