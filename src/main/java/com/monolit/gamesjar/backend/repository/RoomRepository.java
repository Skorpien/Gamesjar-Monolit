package com.monolit.gamesjar.backend.repository;

import com.monolit.gamesjar.backend.domain.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long> {
    @Override
    List<Room> findAll();

    @Override
    Room save(Room room);

    @Override
    Optional<Room> findById(Long id);

    @Override
    void deleteById(Long id);
}
