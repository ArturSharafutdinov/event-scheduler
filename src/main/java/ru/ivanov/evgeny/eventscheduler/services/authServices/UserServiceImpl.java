package ru.ivanov.evgeny.eventscheduler.services.authServices;

import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.UserRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.User;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.JwtUserDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.UserDto;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.Role;
import ru.ivanov.evgeny.eventscheduler.services.mappers.UserMapper;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void submit(UserDto userDto) {
        User user = userMapper.mapToEntity(userDto);
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);

        userDao.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        User user = userDao.getOne(id);
        return userMapper.mapToDto(user);
    }

    @Override
    public UserDetails createUserDetails(User user) {
        return new JwtUserDto(
                (Long) user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getEnabled(),
                user.getRole()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public Long register(UserDto userDto) {
        boolean isUserExist = checkUserForExistsByEmail(userDto.getEmail());
        if (isUserExist)
            throw new IllegalArgumentException("There is an user with that email address:" + userDto.getEmail());

        User user = userMapper.mapToEntity(userDto);
        user.setRole(Role.ADMIN);
        user.setEnabled(true);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        userDao.save(user);

        return (Long) user.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkUserForExistsByEmail(String email) {
        return userDao.countUserByEmail(email) != 0;
    }

}
