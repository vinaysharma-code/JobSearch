package com.jobSearch.security;

import com.jobSearch.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey ;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getSecretKey() {
        byte[] decodeKey = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(decodeKey);
    }

    public String generateToken(User user){
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("userId",user.getId().toHexString())
                .claim("role",user.getRole().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSecretKey())
                .compact();
    }

public  String extractEmail(String token){
    Claims claim = extractAllClaims(token);
    return claim.getSubject();
}

public boolean validateToken(String token, UserDetails userDetails){
String email = extractEmail(token);
        return  email.equals(userDetails.getUsername()) && !isTokenExpired(token);
}

private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token).getPayload();
}

private Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
}
private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
}
}
