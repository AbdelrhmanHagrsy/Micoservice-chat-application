package com.abdelrhman.chatservice.config.websoket;

import com.abdelrhman.chatservice.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class WebsocketConnectHandler implements ApplicationListener<SessionConnectedEvent> {

    @Value("${spring.application.instance.id}")
    private String instanceId;
    @Autowired
    private RedisService redisService;


    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        // add session info in redis when user create a new websocket connection
        StompPrincipal userId = event.getMessage().getHeaders().get("simpUser", StompPrincipal.class);
        if(userId != null){
            System.out.println("session deleted from redis for user :"+ userId);
            redisService.storeUserSession(userId.getName(),instanceId);
        }
    }
}
