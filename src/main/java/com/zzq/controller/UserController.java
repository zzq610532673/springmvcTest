package com.zzq.controller;

import com.zzq.aop.Operation;
import com.zzq.bean.User;
import com.zzq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

//@Operation(name="进入usercontroller")
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    //@Operation(name="usercontroller的hello")
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/allUsers")
    public ModelAndView findAll(){
        List<User> users = null;
        users = redisTemplate.opsForList().range("userList",0,-1);
        System.out.println(users);
        if (users.size()!=0) {
            System.out.println(users.get(0).getUsername());
            System.out.println("已从redis缓存中获取数据");
        }else{
            users=userService.getAllUser();
            redisTemplate.opsForList().rightPushAll("userList",users);
        }
        ModelAndView modelAndView = new ModelAndView("userList");
        modelAndView.addObject("userList",users);
        modelAndView.addObject("name", "张志奇");
        return modelAndView;
    }
    
    @RequestMapping("/updateOrAdd")
    public String updateOrAdd(User user){
        User queryUser = userService.queryUser(user.getUsername());
        if (queryUser != null) {
            userService.update(queryUser,user.getId());
        } else {
            userService.saveUser(queryUser);
        }
        return "userList";
    }
}
