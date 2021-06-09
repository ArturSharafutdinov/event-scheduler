package ru.ivanov.evgeny.eventscheduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.InviteRequestRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.InviteDecisionDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.InviteRequestDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.MinimalInviteRequestDto;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.InviteStatus;
import ru.ivanov.evgeny.eventscheduler.services.invite.InviteService;

import java.util.Set;
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
    public InviteStatus getInviteStatusByEventId(Account account, @RequestParam UUID eventId) {
        return inviteService.getInviteStatus(account, eventId);
    }

    @GetMapping("/invite")
    public Set<InviteRequestDto> getAllInvitesByEventId(Account account, @RequestParam UUID eventId) {
        return inviteService.fetchAllEventInviteRequest(account, eventId);
    }

    @PostMapping("/invite/accept")
    public ResponseEntity<String> acceptInvite(Account account, @RequestBody InviteRequestDto inviteRequestDto) {
        inviteService.approveInvite(account, inviteRequestDto.getId());
        return ResponseEntity.ok("Successfully approved");
    }

    @PostMapping("/invite/decline")
    public ResponseEntity<String> declineInvite(Account account, @RequestBody InviteRequestDto inviteRequestDto) {
        inviteService.rejectInvite(account, inviteRequestDto.getId());
        return ResponseEntity.ok("Successfully rejected");
    }


}
