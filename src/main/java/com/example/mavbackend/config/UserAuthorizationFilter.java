package com.example.mavbackend.config;

import com.example.mavbackend.dto.CredentialsDTO;
import com.example.mavbackend.service.implementation.AuthenticationService;
import com.example.mavbackend.service.interfac.IRolService;
import com.example.mavbackend.util.IConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserAuthorizationFilter  extends OncePerRequestFilter {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private AuthenticationService authenticationService;
    private IRolService rolService;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        if (httpServletRequest.getServletPath().startsWith("/api/person")
                && (Arrays.asList("POST","GET","PUT","DELETE").contains(httpServletRequest.getMethod()))) {
            var credentialsDTO = MAPPER.readValue(httpServletRequest.getInputStream(), CredentialsDTO.class);
            var user = this.authenticationService.authenticate(credentialsDTO);
            var rol = this.rolService.findById(user.getIdRol());
            if(rol.getName().equals(IConstants.USERROL)){
                filterChain.doFilter(httpServletRequest,httpServletResponse);
            }
        }

    }

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Autowired
    public void setRolService(IRolService rolService) {
        this.rolService = rolService;
    }
}
