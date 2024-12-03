package com.abdelrhman.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private String sender;
    private List<String> recipients;
    private LocalDateTime sendTime;
    private String groupId;
    private String messageContent;
}
