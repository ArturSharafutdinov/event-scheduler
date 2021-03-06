package ru.ivanov.evgeny.eventscheduler.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.AccountRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AccountDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AuthAccountDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.JwtAccountDto;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.Role;
import ru.ivanov.evgeny.eventscheduler.security.jwt.TokenProvider;
import ru.ivanov.evgeny.eventscheduler.services.mappers.AccountMapper;

import java.util.HashMap;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    @Transactional
    public Account submit(AccountDto accountDto) {
        Account account;
        if (accountDto.getId() != null) {
            account = accountRepository.findById(accountDto.getId()).orElse(null);
            if (account == null) {
                throw new IllegalStateException("account.id");
            }
        } else {
            account = accountMapper.mapToEntity(accountDto);
            String password = bCryptPasswordEncoder.encode(account.getPassword());
            account.setPassword(password);
            account = accountRepository.saveAndFlush(account);
        }
        return account;
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No account has id = " + id));
    }

    @Override
    public UserDetails createUserDetails(Account account) {
        return new JwtAccountDto(
                account.getId(),
                account.getEmail(),
                account.getPassword(),
                account.getEnabled(),
                account.getRole()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Account findAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Long register(AccountDto accountDto) {
        boolean isAccountExists = checkAccountForExistsByEmail(accountDto.getEmail());
        if (isAccountExists)
            throw new IllegalArgumentException("There is an account with that email address:" + accountDto.getEmail());

        Account account = accountMapper.mapToEntity(accountDto);
        account.setRole(Role.USER);
        account.setEnabled(true);
        account.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
        accountRepository.save(account);
        return account.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkAccountForExistsByEmail(String email) {
        return accountRepository.countAccountByEmail(email) != 0;
    }

    @Override
    public HashMap<String, Object> login(AuthAccountDto authAccountDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authAccountDto.getEmail(), authAccountDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication, true);
        final JwtAccountDto jwtUser = (JwtAccountDto) authentication.getPrincipal();
        final AccountDto accountDto = accountMapper.mapToDto(accountRepository.findById(jwtUser.getId()).get());

        return new HashMap<>() {{
            put("token", "Bearer " + token);
            put("userAccessInfo", jwtUser);
            put("userPersonalInfo",accountDto);
        }};
    }

    @Override
    @Transactional(readOnly = true)
    public Account findAccount(Authentication authentication) {
        User userDetails = (User) authentication.getPrincipal();

        if (userDetails.getUsername() != null) {
            Account byIdEquals = accountRepository.findByEmail(userDetails.getUsername());
            return byIdEquals;
        }

        throw new IllegalArgumentException();

    }

}
