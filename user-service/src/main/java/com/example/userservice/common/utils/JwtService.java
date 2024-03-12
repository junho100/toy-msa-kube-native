package com.example.userservice.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    public String createJwtTokenByUserId(Long userId) {
        Date now = new Date();
        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis()))
            .claim("id", userId)
            .signWith(key)
            .compact();
    }

    public Long getUserIdFromJwtToken(String token) {
        Claims claims = parseJwtToken(token);
        return claims.get("id", Long.class);
    }

    public Claims parseJwtToken(String token) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        Claims claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();

        return claims;
    }
}
