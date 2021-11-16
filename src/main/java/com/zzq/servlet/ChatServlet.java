package com.zzq.servlet;

import com.zzq.netty.ChatServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ChatServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        ChatServer chatServer = new ChatServer(8888);
        chatServer.start();
        System.out.println("聊天服务器启动------");
    }
}
