package com.monolit.gamesjar.backend.controller;

import com.monolit.gamesjar.backend.domain.User;
import com.monolit.gamesjar.backend.domain.UserDto;
import com.monolit.gamesjar.backend.domain.UserNotFoundException;
import com.monolit.gamesjar.backend.facade.UserControllerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/gamesjar")
public class UserController {

    @Autowired
    private UserControllerFacade facade;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<UserDto> userList () {
        return facade.userList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    public UserDto getUser(@PathVariable Long userId) throws UserNotFoundException {
        return facade.getUser(userId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        facade.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = APPLICATION_JSON_VALUE)
    public User createUser (@RequestBody UserDto userDto) {
        return facade.createUser(userDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    public UserDto updateUser (@RequestBody UserDto userDto) {
        return facade.updateUser(userDto);
    }
}
