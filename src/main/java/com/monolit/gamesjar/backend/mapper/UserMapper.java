package com.monolit.gamesjar.backend.mapper;

import com.monolit.gamesjar.backend.domain.User;
import com.monolit.gamesjar.backend.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getPassword());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getPassword());
    }

    public List<UserDto> mapToUserDtoList(final List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getPassword()))
                .collect(Collectors.toList());
    }
}
