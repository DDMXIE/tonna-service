package com.tony.tonna.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Xiem
 * Time: 2019/6/13 15:34
 */

@Entity
public class User {
    @Id
    private String uid;
    private String name;
    private Integer age;

    //必须要有构造函数
    public User() {
    }

    public String getUId() {
        return uid;
    }

    public void setUId(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

