package com.example.generate_form.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    
    private String secret = "mySecretKey123456789012345678901234567890";
    private int expiration = 86400000;
    
    public String getSecret() {
        return secret;
    }
    
    public void setSecret(String secret) {
        this.secret = secret;
    }
    
    public int getExpiration() {
        return expiration;
    }
    
    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }
}
