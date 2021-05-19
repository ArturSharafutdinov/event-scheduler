package ru.ivanov.evgeny.eventscheduler.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.AccountRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.JwtAccountDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AccountDto;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.Role;
import ru.ivanov.evgeny.eventscheduler.services.mappers.AccountMapper;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    @Transactional
    public void submit(AccountDto accountDto) {
        Account account = accountMapper.mapToEntity(accountDto);
        String password = bCryptPasswordEncoder.encode(account.getPassword());
        account.setPassword(password);

        accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccountById(Long id) {
        Account account = accountRepository.getOne(id);
        return account;
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
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Long register(AccountDto accountDto) {
        boolean isAccountExists = checkAccountForExistsByEmail(accountDto.getEmail());
        if (isAccountExists)
            throw new IllegalArgumentException("There is an account with that email address:" + accountDto.getEmail());

        Account account = accountMapper.mapToEntity(accountDto);
        account.setRole(Role.ADMIN);
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

}
