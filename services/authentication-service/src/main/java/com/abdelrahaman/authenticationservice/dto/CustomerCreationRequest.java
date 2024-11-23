package com.abdelrahaman.authenticationservice.dto;

import lombok.Builder;

@Builder
public record CustomerCreationRequest(
        String userName,
        String password,
        String firstName,
        String lastName,
        String telephone
) {
}