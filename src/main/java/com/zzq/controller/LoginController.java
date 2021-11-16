package com.zzq.controller;

import com.zzq.dto.CodeConstant;
import com.zzq.dto.Message;
import com.zzq.utils.ValidateCodeGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("captcha") String inputStr,
                        HttpSession session){
        String randomcodekey = (String) session.getAttribute("RANDOMCODEKEY");
        
        if (username!=null&&password!=null&&username!=""&&password!=""){
            Message message = new Message();
            Subject subject = SecurityUtils.getSubject();
            //利用登录的用户名和密码生成令牌，可以使用令牌功能实现记住我的功能
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            //认证
            subject.login(token);
            int i = randomcodekey.compareToIgnoreCase(inputStr);
            System.out.println(i);
            //判断是否通过验证
            if (randomcodekey.compareToIgnoreCase(inputStr)==0){
                if (subject.isAuthenticated()) {
                    logger.debug("User Login Succeed",username);
                    message.setCode(CodeConstant.SUCCESS.getIndex());
                    message.setMsg(CodeConstant.SUCCESS.getMsg());
                    return "redirect:allUsers";
                }else {
                    return "index.html";
                }
            }else{
                return "redirect:/";
            }
            
        }
        return "error";
    }
    
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model){
        SecurityUtils.getSubject().logout();
        return "redirect:/index.html";
    }
    
    @RequestMapping("/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expire",0);
        ValidateCodeGenerator validateCodeGenerator = new ValidateCodeGenerator();
        try {
            validateCodeGenerator.getRandom(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
