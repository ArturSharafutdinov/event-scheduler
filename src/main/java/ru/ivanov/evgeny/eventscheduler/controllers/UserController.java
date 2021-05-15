package ru.ivanov.evgeny.eventscheduler.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.UserRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.User;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.UserDto;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.Role;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signUp")
    public void registration(@RequestBody UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user == null) {
            user = new User();
            user.setUsername(
                    userDto.getUsername()
            );
            user.setPassword(
                    passwordEncoder.encode(userDto.getPassword())
            );
            user.setEmail(
                    userDto.getEmail()
            );
            user.setFirstName(
                    userDto.getFirstName()
            );
            user.setRole(
                    Role.USER
            );
            // TODO убрать после подтверждения email
            user.setEnabled(true);
            userRepository.save(user);

        } else {
            throw new RuntimeException("User with this email exists");
        }


    }

}
