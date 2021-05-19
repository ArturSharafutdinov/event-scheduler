package ru.ivanov.evgeny.eventscheduler.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;

import javax.transaction.Transactional;
import java.util.Objects;

@Transactional
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String email) {

        final Account account = accountService.findByEmail(email);

        if (Objects.isNull(account)) {
            throw new IllegalArgumentException("No user found with email: " + email);
        }

        boolean isEnabled = account.getEnabled();
        if (!isEnabled) {
            throw new IllegalArgumentException("User is banned");
        }

        return accountService.createUserDetails(account);
    }
}

