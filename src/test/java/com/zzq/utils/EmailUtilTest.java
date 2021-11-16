package com.zzq.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class EmailUtilTest {
    
    @Autowired
    EmailUtil emailUtil;
    @Test
    public void sendSimpleMail() {
        String subject = "美女艳照";
        String content = "木秀于林，风必摧之";
        //emailUtil.sendHtmlMail(subject, content, "1411581508@qq.com");
        emailUtil.sendPictureMail(subject,content,"1411581508@qq.com","D:\\1.jpg");
    }
}