package com.zzq.serviceImpl;

import com.zzq.bean.User;
import com.zzq.mapper.UserMapper;
import com.zzq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUser() {
        System.out.println("从数据库拿到的数据");
        List<User> users = userMapper.findAll();
        return users;
    }

    public void saveUser(User user) {
        userMapper.save(user);
    }

    @Override
    public User queryUser(String username) {
        User user = null;
        if (username!=null&&username!=""){
           user = userMapper.getUser(username);
        }
        return user;
    }

    @Override
    public void update(User user,int id) {
        userMapper.update(user,id);
    }
}
