package com.enigma.restgarden.security;

import com.enigma.restgarden.service.UserDetailServiceDbImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    @Value("${secret}")
    public String secret;

    @Autowired
    UserDetailServiceDbImpl userDetailServiceDb;

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", userDetails.getUsername());
        claims.put("role", userDetails.getAuthorities());

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+(5*60*100000)))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    public UserDetails parseToken(String token){
        Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token);
        String userName = jwsClaims.getBody().getSubject();
        return userDetailServiceDb.loadUserByUsername(userName);
    }
}
