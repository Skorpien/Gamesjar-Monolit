package com.monolit.gamesjar.backend.facade;

import com.monolit.gamesjar.backend.domain.User;
import com.monolit.gamesjar.backend.domain.UserDto;
import com.monolit.gamesjar.backend.domain.UserNotFoundException;
import com.monolit.gamesjar.backend.mapper.UserMapper;
import com.monolit.gamesjar.backend.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserControllerFacade {

    @Autowired
    private DbService dbService;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> userList () {
        return userMapper.mapToUserDtoList(dbService.getAllUsers());
    }

    public UserDto getUser(Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(dbService.getUser(userId));
    }

    public void deleteUser(Long userId) {
        dbService.deleteUser(userId);
    }

    public User createUser (UserDto userDto) {
        return dbService.saveUser(userMapper.mapToUser(userDto));
    }

    public UserDto updateUser (UserDto userDto) {
        return userMapper.mapToUserDto(dbService.saveUser(userMapper.mapToUser(userDto)));
    }
}
