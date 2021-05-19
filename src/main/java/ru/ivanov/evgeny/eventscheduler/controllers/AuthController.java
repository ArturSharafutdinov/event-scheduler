package ru.ivanov.evgeny.eventscheduler.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AuthAccountDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.JwtAccountDto;
import ru.ivanov.evgeny.eventscheduler.security.jwt.TokenProvider;
import ru.ivanov.evgeny.eventscheduler.services.auth.AccountService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/auth")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthAccountDto authAccountDto) {
        Map<String, Object> authInfo = accountService.login(authAccountDto);
        return ResponseEntity.ok(authInfo);
    }

}
