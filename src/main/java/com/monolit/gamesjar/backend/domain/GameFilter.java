package com.monolit.gamesjar.backend.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GameFilter {

    public List<Game> search(Room room, String name, PlayingTime time, Category category) {
        List<Game> gameList = new ArrayList<>();
        if (!name.equals("")) {
            gameList = room.getGames().stream()
                    .filter(game -> game.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            if(time != null) {
                gameList = room.getGames().stream()
                        .filter(game -> (game.getTime().equals(time)))
                        .collect(Collectors.toList());
            }
            if(category != null) {
                gameList = room.getGames().stream()
                        .filter(game -> (game.getCategories().equals(category)))
                        .collect(Collectors.toList());
            }
        }
        return gameList;
    }

    public Game randomize (List<Game> games) {
        Random random = new Random();
        int game = random.nextInt(games.size());
        return games.get(game);
    }
}
