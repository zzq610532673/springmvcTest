package com.zzq.webSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ServerEndpoint(value="/websocketDemo/{userId}",configurator = SpringConfigurator.class)
public class WebSocketDemo {
    private Logger logger = LoggerFactory.getLogger(WebSocketDemo.class);

    private static int onlineCount = 0;

    private static Map<Integer, Set<WebSocketDemo>> userSocket = new HashMap<>();

    private Session session;

    private int userId;

    @OnOpen
    public void onOpen(@PathParam("userId") int userId,Session session){
        this.session=session;
        this.userId=userId;
        if (userSocket.containsKey(userId)){
            logger.debug("当前用户id:{}已有其他终端登录",this.userId);
            userSocket.get(this.userId).add(this);
        }else{
            logger.debug("当前用户id:{}第一个终端登录",this.userId);
            Set<WebSocketDemo> addUserSet = new HashSet<>();
            addUserSet.add(this);
            userSocket.put(this.userId, addUserSet);
        }
        logger.debug("用户{}登录的终端个数是为{}",userId,userSocket.get(this.userId).size());
        logger.debug("当前在线用户数为：{},所有终端个数为：{}",userSocket.size(),onlineCount);
    }

    @OnClose
    public void onClose(){
        if (userSocket.get(this.userId).size()==0){
            userSocket.remove(this.userId);
        }else{
            userSocket.get(this.userId).remove(this);
        }
        logger.debug("用户{}登录的终端个数是为{}",this.userId,userSocket.get(this.userId).size());
        logger.debug("当前在线用户数为：{},所有终端个数为：{}",userSocket.size(),onlineCount);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.debug("收到来自用户id为：{}的消息：{}",this.userId,message);
        if(session ==null)  logger.debug("session null");
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.debug("用户id为：{}的连接发送错误", this.userId);
        error.printStackTrace();
    }

    public Boolean sendMessageToUser(Long userId,String message){
        if (userSocket.containsKey(userId)) {

            logger.debug(" 给用户id为：{}的所有终端发送消息：{}",userId,message);
            for (WebSocketDemo WS : userSocket.get(userId)) {
                logger.debug("sessionId为:{}",WS.session.getId());
                try {
                    WS.session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }

        logger.debug("发送错误：当前连接不包含id为：{}的用户",userId);
        return false;
    }

}
