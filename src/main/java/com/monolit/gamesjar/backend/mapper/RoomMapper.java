package com.monolit.gamesjar.backend.mapper;

import com.monolit.gamesjar.backend.domain.Room;
import com.monolit.gamesjar.backend.domain.RoomDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomMapper {
    public Room mapToRoom(final RoomDto roomDto) {
        return new Room(
                roomDto.getId(),
                roomDto.getName()
        );
    }

    public RoomDto mapToRoomDto(final Room room) {
        return new RoomDto(
                room.getId(),
                room.getName()
        );
    }

    public List<RoomDto> mapToRoomDtoList(final List<Room> rooms) {
        return rooms.stream()
                .map(room -> new RoomDto(room.getId(), room.getName()))
                .collect(Collectors.toList());
    }
}
