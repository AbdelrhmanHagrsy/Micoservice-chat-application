package com.abdelrhman.chatservice.config.websoket;

import com.abdelrhman.chatservice.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebsocketDisconnectHandler implements ApplicationListener<SessionDisconnectEvent> {

    @Autowired
    private RedisService redisService;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        // when user websocket connection get closed delete session info from redis
        StompPrincipal userId = event.getMessage().getHeaders().get("simpUser", StompPrincipal.class);
        if(userId != null){
            redisService.deleteUserSession(userId.getName());
        }
    }
}
