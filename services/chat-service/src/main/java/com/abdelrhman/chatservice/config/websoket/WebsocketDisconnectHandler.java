package com.abdelrhman.chatservice.config.websoket;

import com.abdelrhman.chatservice.service.RedisService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebsocketDisconnectHandler implements ApplicationListener<SessionDisconnectEvent> {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        StompPrincipal userId = event.getMessage().getHeaders().get("simpUser", StompPrincipal.class);
        if(userId != null){
            System.out.println("session deleted from redis for user :"+ userId);
            redisTemplate.opsForHash().delete("user-session",userId.getName());
        }
    }
}
