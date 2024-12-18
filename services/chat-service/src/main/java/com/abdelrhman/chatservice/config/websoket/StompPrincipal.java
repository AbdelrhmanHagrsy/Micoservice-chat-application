package com.abdelrhman.chatservice.config.websoket;

import java.security.Principal;

public class StompPrincipal implements Principal {
    private final String name;

    public StompPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "StompPrincipal{name='" + name + "'}";
    }
}
