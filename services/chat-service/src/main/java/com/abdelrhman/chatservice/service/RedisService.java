package com.abdelrhman.chatservice.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public void storeUserSession(String userEmail,String instanceId){
        System.out.println("session save in redis :"+ userEmail +" and " + instanceId);
        redisTemplate.opsForHash().put("user-session",userEmail,instanceId);
    }

    public String getUserInstanceId(String userEmail){
        return (String) redisTemplate.opsForHash().get("user-session",userEmail);
    }

    public void deleteUserSession(String userEmail){
        System.out.println("session deleted from redis for user :"+ userEmail);
        redisTemplate.opsForHash().delete("user-session",userEmail);
    }
}
