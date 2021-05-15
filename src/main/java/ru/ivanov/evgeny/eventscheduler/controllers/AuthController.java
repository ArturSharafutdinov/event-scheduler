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
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AuthUserDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.JwtUserDto;
import ru.ivanov.evgeny.eventscheduler.security.jwt.TokenProvider;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/auth")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto user) {


        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication, true);
        final JwtUserDto jwtUser = (JwtUserDto) authentication.getPrincipal();

        Map<String, Object> authInfo = new HashMap<>();

        authInfo.put("token", "Bearer " + token);
        authInfo.put("user", jwtUser);

        return ResponseEntity.ok(authInfo);
    }

}
