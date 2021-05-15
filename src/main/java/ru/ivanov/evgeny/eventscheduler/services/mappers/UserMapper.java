package ru.ivanov.evgeny.eventscheduler.services.mappers;

import org.springframework.stereotype.Component;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.User;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.UserDto;

@Component
public class UserMapper {

    public User mapToEntity(UserDto userDto) {
        User user = new User();

        user.setUsername(
                userDto.getUsername()
        );
        user.setPassword(
                userDto.getPassword()
        );
        user.setEmail(
                userDto.getEmail()
        );
        user.setFirstName(
                userDto.getFirstName()
        );
        user.setMiddleName(
                userDto.getLastName()
        );

        return user;
    }

    public UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(
                (Long) user.getId()
        );
        userDto.setUsername(
                user.getUsername()
        );
        userDto.setPassword(
                user.getPassword()
        );
        userDto.setEmail(
                user.getEmail()
        );
        userDto.setFirstName(
                user.getFirstName()
        );
        userDto.setLastName(
                user.getMiddleName()
        );


        return userDto;
    }
}
