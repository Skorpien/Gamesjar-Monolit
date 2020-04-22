package com.monolit.gamesjar.backend.service;

import com.monolit.gamesjar.backend.domain.*;
import com.monolit.gamesjar.backend.repository.GameRepository;
import com.monolit.gamesjar.backend.repository.RoomRepository;
import com.monolit.gamesjar.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(final Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGame(final Long id) throws GameNotFoundException {
        return gameRepository.findById(id).orElseThrow(GameNotFoundException::new);
    }

    public Game saveGame(final Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(final Game game, Long id) throws GameNotFoundException {
        Game myGame = getGame(id);
        myGame.setName(game.getName());
        myGame.setCategories(game.getCategories());
        myGame.setTime(game.getTime());
        return gameRepository.save(myGame);
    }

    public void deleteGame(final Long id) {
        gameRepository.deleteById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoom(final Long id) throws RoomNotFoundException {
        return roomRepository.findById(id).orElseThrow(RoomNotFoundException::new);
    }

    public Room saveRoom(final Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(final Long id) {
        roomRepository.deleteById(id);
    }
}
