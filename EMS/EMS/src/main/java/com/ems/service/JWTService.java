package com.ems.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface JWTService {
    public String generateToken(UserDetails userDetails);
     String extractUsername(String token);
     boolean isTokenValid(String token, UserDetails userDetails);
    String generateRefreshToken(Map<Object,String> claims, UserDetails userDetails);
}
