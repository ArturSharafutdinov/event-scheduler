package ru.ivanov.evgeny.eventscheduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.InviteRequestRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.MinimalInviteRequestDto;
import ru.ivanov.evgeny.eventscheduler.services.invite.InviteService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class InviteRequestController {

    @Autowired
    private InviteRequestRepository inviteRequestRepository;

    @Autowired
    private InviteService inviteService;

    @PostMapping("/invite/register")
    public void registerInvite(@RequestBody MinimalInviteRequestDto minimalInviteRequestDto) {
        inviteService.registerInvite(minimalInviteRequestDto);
    }
}
