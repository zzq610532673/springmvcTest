package com.zzq.mapper;

import com.zzq.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> findAll();
    
    User getUser(@Param("username") String username);

    void save(User user);

    void update(@Param("user")User user,@Param("id") int id);
}
