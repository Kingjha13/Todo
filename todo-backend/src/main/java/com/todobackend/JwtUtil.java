package com.todobackend;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final String secret = "12345678901234567890123456789012";
    private final Key key =
            Keys.hmacShaKeyFor(secret.getBytes());
    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

    // Extract information

    public Claims extractClaims(String token){
        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey)key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //Extract UserName

    public String extractUserName(String token){
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token){
        try {
            extractClaims(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
