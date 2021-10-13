package com.example.mavbackend.controller;

import com.example.mavbackend.config.UserAuthenticationProvider;
import com.example.mavbackend.dto.RolDTO;
import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.dto.SignUpDTO;
import com.example.mavbackend.mapper.RolMapper;
import com.example.mavbackend.service.interfac.IMinistryService;
import com.example.mavbackend.service.interfac.IRolService;
import com.example.mavbackend.service.interfac.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final IUserService userService;
    private final IRolService rolService;
    private final UserAuthenticationProvider userAuthenticationProvider;
    private final IMinistryService ministryService;
    private final RolMapper rolMapper;

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

    @GetMapping("/validarCreacionMinisterio")
    public ResponseEntity<Boolean> validateMinistryCreation(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "documento") String document
    ) {
        return ResponseEntity.ok(this.ministryService.validateMinistrySignUp(email, document));
    }

    @GetMapping("/validarCreacionUsuario")
    public ResponseEntity<Boolean> validateUserCreation(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "documento") String document
    ) {
        return ResponseEntity.ok(this.userService.validateUserSignUp(email, document));
    }

    @GetMapping("/existsUsername")
    public ResponseEntity<Boolean> validateUserCreation(
            @RequestParam(name = "username") String username
    ) {
        return ResponseEntity.ok(this.userService.existsUsername(username));
    }


    @GetMapping("/roles")
    public ResponseEntity<List<RolDTO>> selectRols() {
        var rols = this.rolService.getAll();
        if (rols == null || rols.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(rolMapper.toRolDTOList(rols));
    }

    @PostMapping("/signOut")
    public ResponseEntity<Void> signOut(@AuthenticationPrincipal UserDTO user) {
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }
}