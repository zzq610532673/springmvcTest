package com.zzq.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class role implements Serializable {
    
    private int id;
    private String rolename;

    public int getId() {
        return id;
    }

    public role setId(int id) {
        this.id = id;
        return this;
    }

    public String getRolename() {
        return rolename;
    }

    public role setRolename(String rolename) {
        this.rolename = rolename;
        return this;
    }
}
