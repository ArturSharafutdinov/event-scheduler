package ru.ivanov.evgeny.eventscheduler.services.authServices;

import org.springframework.security.core.userdetails.UserDetails;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.User;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.UserDto;

public interface UserService {


    void submit(UserDto userDto);

    UserDto getUserById(Long id);

    UserDetails createUserDetails(User user);

    User findByEmail(String email);

    Long register(UserDto userDto);

    boolean checkUserForExistsByEmail(String email);
}

