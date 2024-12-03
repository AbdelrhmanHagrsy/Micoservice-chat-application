package com.abdelrhman.chatservice.config.websoket;

import com.abdelrhman.chatservice.dto.InstanceName;
import com.abdelrhman.chatservice.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
@Slf4j
public class WebsocketInterceptore implements HandshakeInterceptor {





    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        // String currentUserEmail = request.getHeaders().getFirst("X-User-Email");
        String currentUserEmail = "omar@gmail.com";

        if(currentUserEmail == null) {
            log.error("Current user email not found in request header!");
            return false;
        }
        StompPrincipal principal = new StompPrincipal(currentUserEmail);
        attributes.put("userEmail", principal);
        System.out.println("Principal set with name: " + principal.getName());

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    }
}
