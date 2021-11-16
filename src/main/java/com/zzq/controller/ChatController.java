package com.zzq.controller;

import com.zzq.netty.ChatServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {
    @RequestMapping("/chat")
    public String chat(){
        ChatServer chatServer = new ChatServer(8888);
        chatServer.start();
        System.out.println("after chatServer start");
        return "chat";
    }
}
