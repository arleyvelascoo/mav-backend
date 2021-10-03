package com.example.mavbackend.config;

import com.example.mavbackend.dto.CredentialsDTO;
import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.service.implementation.AuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@Component
public class UserAuthenticationProvider {
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    private final AuthenticationService authenticationService;

    public UserAuthenticationProvider(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    public String createToken(String username){
        var claims = Jwts.claims().setSubject(username);
        var now = new Date();
        var validity = new Date(now.getTime() + 3600000); // 1 hour
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication validateToken(String token){
        var username = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        UserDTO user = authenticationService.findByLogin(username);

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public Authentication validateCredentials(CredentialsDTO credentialsDto) {
        var user = authenticationService.authenticate(credentialsDto);
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
}
