package com.monolit.gamesjar.backend.facade;

import com.monolit.gamesjar.backend.domain.Game;
import com.monolit.gamesjar.backend.domain.GameDto;
import com.monolit.gamesjar.backend.domain.GameNotFoundException;
import com.monolit.gamesjar.backend.mapper.GameMapper;
import com.monolit.gamesjar.backend.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameControllerFacade {

    @Autowired
    private DbService dbService;

    @Autowired
    private GameMapper gameMapper;

    public List<GameDto> gamesList() {
        return gameMapper.mapToGameDtoList(dbService.getAllGames());
    }

    public GameDto getGame(Long gameId) throws GameNotFoundException {
        return gameMapper.mapToGameDto(dbService.getGame(gameId));
    }

    public void deleteGame(Long gameId) {
        dbService.deleteGame(gameId);
    }

    public Game createGame(GameDto gameDto) {
        return dbService.saveGame(gameMapper.mapToGame(gameDto));
    }

    public void updateGame(GameDto gameDto, Long id) throws GameNotFoundException {
        gameMapper.mapToGameDto(dbService.updateGame(gameMapper.mapToGame(gameDto), id));
    }
}
