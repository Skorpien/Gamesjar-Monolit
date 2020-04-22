package com.monolit.gamesjar.backend.controller;

import com.monolit.gamesjar.backend.domain.Room;
import com.monolit.gamesjar.backend.domain.RoomDto;
import com.monolit.gamesjar.backend.domain.RoomNotFoundException;
import com.monolit.gamesjar.backend.facade.RoomControllerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("v1/gamesjar")
public class RoomController {
    @Autowired
    private RoomControllerFacade facade;

    @RequestMapping(method = RequestMethod.GET, value = "/rooms")
    public List<RoomDto> roomsList() {
        return facade.roomsList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rooms/{roomId}")
    public RoomDto getRoom(@PathVariable Long roomId) throws RoomNotFoundException {
        return facade.getRoom(roomId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/rooms/{roomId}")
    public void deleteRoom(@PathVariable Long roomId) {
        facade.deleteRoom(roomId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rooms", consumes = APPLICATION_JSON_VALUE)
    public Room createRoom(@RequestBody RoomDto roomDto) {
        return facade.createRoom(roomDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rooms")
    public RoomDto updateRoom(@RequestBody RoomDto roomDto) {
        return facade.updateRoom(roomDto);
    }
}
