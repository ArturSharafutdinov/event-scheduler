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
    public String registration(@Validated @RequestBody UserDto user) {
        User newUser = userRepository.findByEmail(user.getEmail());
        if (newUser == null) {
            newUser = new User();
            newUser.setUsername(
                    user.getUsername()
            );
            newUser.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );
            newUser.setEmail(
                    user.getEmail()
            );
            newUser.setFirstName(
                    user.getFirstName()
            );
            newUser.setRole(
                    Role.USER
            );
            newUser.setEnabled(true);
            userRepository.save(newUser);
            return "Signed up successfully";

        } else {
            return "User with this email exists";
        }


    }

}
