package com.example.alura.forum.controller;

import com.example.alura.forum.domain.user.User;
import com.example.alura.forum.domain.user.dto.AuthenticationDTO;
import com.example.alura.forum.infra.security.token.TokenJWTDTO;
import com.example.alura.forum.infra.security.token.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity signIn(@RequestBody @Valid AuthenticationDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }
}

