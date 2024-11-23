package com.abdelrhman.chatservice.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
@Slf4j
public class WebsocketInterceptore implements HandshakeInterceptor {
    public static int state = 0;
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        String currentUserEmail = request.getHeaders().getFirst("X-User-Email");
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
