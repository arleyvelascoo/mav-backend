package com.example.mavbackend.controller;

import com.example.mavbackend.config.UserAuthenticationProvider;
import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.mapper.SignUpDTO;
import com.example.mavbackend.service.interfac.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final IUserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/signIn")
    public ResponseEntity<UserDTO> signIn(@AuthenticationPrincipal UserDTO user) {
        user.setToken(userAuthenticationProvider.createToken(user));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@RequestBody @Valid SignUpDTO user) {
        UserDTO createdUser = userService.signUp(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/signOut")
    public ResponseEntity<Void> signOut(@AuthenticationPrincipal UserDTO user) {
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }
}