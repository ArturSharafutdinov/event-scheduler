package ru.ivanov.evgeny.eventscheduler.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AccountDto;
import ru.ivanov.evgeny.eventscheduler.services.auth.AccountService;
import ru.ivanov.evgeny.eventscheduler.services.files.FileService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AccountController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountService accountService;
    @Autowired
    private FileService fileService;

    @PostMapping("/signUp")
    public void registration(@RequestBody AccountDto accountDto) {
        Long accountId = accountService.register(accountDto);
        System.out.println(accountId);
    }

    @PostMapping("/account/avatar/{fileInfoId}")
    public void saveAccountAvatar(Account account, @PathVariable UUID fileInfoId) {
        fileService.saveAccountAvatar(account, fileInfoId);
    }

    @GetMapping("/account/getAvatar")
    @ResponseBody
    public ResponseEntity<Resource> getAccountAvatar(Account account, HttpServletRequest request) {
        return fileService.loadAvatar(account, request);
    }

}
