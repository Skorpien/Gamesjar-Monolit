package com.monolit.gamesjar.backend.facade;

import com.monolit.gamesjar.backend.domain.Room;
import com.monolit.gamesjar.backend.domain.RoomDto;
import com.monolit.gamesjar.backend.domain.RoomNotFoundException;
import com.monolit.gamesjar.backend.mapper.RoomMapper;
import com.monolit.gamesjar.backend.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RoomControllerFacade {

    @Autowired
    private DbService dbService;

    @Autowired
    private RoomMapper roomMapper;

    public List<RoomDto> roomsList() {
        return roomMapper.mapToRoomDtoList(dbService.getAllRooms());
    }

    public RoomDto getRoom(Long roomId) throws RoomNotFoundException {
        return roomMapper.mapToRoomDto(dbService.getRoom(roomId));
    }

    public void deleteRoom(Long roomId) {
        dbService.deleteRoom(roomId);
    }

    public Room createRoom(RoomDto roomDto) {
        return dbService.saveRoom(roomMapper.mapToRoom(roomDto));
    }

    public RoomDto updateRoom(RoomDto roomDto) {
        return roomMapper.mapToRoomDto(dbService.saveRoom(roomMapper.mapToRoom(roomDto)));
    }
}
