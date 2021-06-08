package ru.ivanov.evgeny.eventscheduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.InviteRequestRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.MinimalInviteRequestDto;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.InviteStatus;
import ru.ivanov.evgeny.eventscheduler.services.invite.InviteService;

import java.util.UUID;

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

    @GetMapping("/invite/status")
    public InviteStatus getInviteStatus(Account account, @RequestParam UUID eventId) {
        return inviteService.getInviteStatus(account, eventId);
    }
}
