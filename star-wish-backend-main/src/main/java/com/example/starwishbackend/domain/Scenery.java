package com.example.starwishbackend.domain;

import jakarta.persistence.*;

@Table(name = "home_scenery")
@Entity
public class Scenery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_index;
    private String item_name;
    private String item_description;
    private String item_detail;
    private String phoneNum;

    public int getItem_index() {
        return item_index;
    }

    public void setItem_index(int item_index) {
        this.item_index = item_index;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_detail() {
        return item_detail;
    }

    public void setItem_detail(String item_detail) {
        this.item_detail = item_detail;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
