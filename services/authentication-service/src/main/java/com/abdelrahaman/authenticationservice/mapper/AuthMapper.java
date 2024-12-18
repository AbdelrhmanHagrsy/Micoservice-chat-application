package com.abdelrahaman.authenticationservice.mapper;

import com.abdelrahaman.authenticationservice.dto.CustomerCreationRequest;
import com.abdelrahaman.authenticationservice.dto.RegistrationRequest;
import com.abdelrahaman.authenticationservice.dto.UserRoles;
import com.abdelrahaman.authenticationservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthMapper {

    private final PasswordEncoder passwordEncoder;
    public User convertRegisRequestToRegsEntity(RegistrationRequest registrationRequest){
        return User.builder()
                .firstName(registrationRequest.firstName())
                .lastName(registrationRequest.lastName())
                .userName(registrationRequest.userName())
                .password(passwordEncoder.encode(registrationRequest.password()))
                .telephone(registrationRequest.telephone())
                .userRoles(UserRoles.ROLE_USER)  // set role user as default role
                .build();
    }

    public CustomerCreationRequest convertRegisRequestToCustomerRequest(RegistrationRequest registrationRequest){
        return CustomerCreationRequest.builder()
                .userName(registrationRequest.userName())
                .firstName(registrationRequest.firstName())
                .lastName(registrationRequest.lastName())
                .telephone(registrationRequest.telephone())
                .build();
    }

}
