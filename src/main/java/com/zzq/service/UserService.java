package com.zzq.service;

import com.zzq.bean.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> getAllUser();
    void saveUser(User user);
    User queryUser(String username);
    void update(User queryUser,int id);
}
