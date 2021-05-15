package ru.ivanov.evgeny.eventscheduler.services.authServices;

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
import ru.ivanov.evgeny.eventscheduler.services.mappers.UserMapper;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AccountRepository userDao;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void submit(AccountDto accountDto) {
        Account user = userMapper.mapToEntity(accountDto);
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);

        userDao.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto getUserById(Long id) {
        Account user = userDao.getOne(id);
        return userMapper.mapToDto(user);
    }

    @Override
    public UserDetails createUserDetails(Account user) {
        return new JwtAccountDto(
                (Long) user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getEnabled(),
                user.getRole()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Account findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public Long register(AccountDto accountDto) {
        boolean isUserExist = checkUserForExistsByEmail(accountDto.getEmail());
        if (isUserExist)
            throw new IllegalArgumentException("There is an user with that email address:" + accountDto.getEmail());

        Account user = userMapper.mapToEntity(accountDto);
        user.setRole(Role.ADMIN);
        user.setEnabled(true);
        user.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));

        userDao.save(user);

        return (Long) user.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkUserForExistsByEmail(String email) {
        return userDao.countUserByEmail(email) != 0;
    }

}
