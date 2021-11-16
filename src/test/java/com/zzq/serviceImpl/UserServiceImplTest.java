package com.zzq.serviceImpl;

import com.zzq.bean.User;

import com.zzq.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class UserServiceImplTest {
    @Autowired
    public UserServiceImpl service;
    @Test
    public void getAllUser() {
        List<User> allUser = service.getAllUser();
        System.out.println(allUser.size());
    }

    @Test
    public void queryUser() {
        User user = service.queryUser("zzq");
        System.out.println(user.toString());
    }
    @Test
    public void save(){
        User user = new User();
        user.setUsername("qrj");
        user.setPassword("qrj123");
        service.saveUser(user);
    }
    @Test
    public void update(){
        User user = new User();
        user.setUsername("qrj");
        user.setPassword("qrj1234");
        service.update(user,7);
    }
}