package com.monolit.gamesjar.backend.controller;

import com.monolit.gamesjar.backend.domain.Game;
import com.monolit.gamesjar.backend.domain.GameDto;
import com.monolit.gamesjar.backend.domain.GameNotFoundException;
import com.monolit.gamesjar.backend.facade.GameControllerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/gamesjar")
public class GameController {
    @Autowired
    private GameControllerFacade facade;

    @RequestMapping(method = RequestMethod.GET, value = "/games")
    public List<GameDto> gamesList() {
        return facade.gamesList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/games/{gameId}")
    public GameDto getGame(@PathVariable Long gameId) throws GameNotFoundException {
        return facade.getGame(gameId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/games/{gameId}")
    public void deleteGame(@PathVariable Long gameId) {
        facade.deleteGame(gameId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/games", consumes = APPLICATION_JSON_VALUE)
    public Game createGame(@RequestBody GameDto gameDto) {
        return facade.createGame(gameDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/games/{gameId}")
    public void updateGame(@RequestBody GameDto gameDto, @PathVariable Long gameId) throws GameNotFoundException {
        facade.updateGame(gameDto, gameId);
    }
}
