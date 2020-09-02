package com.intersog.mysteriousnumber.server.repositories;

import com.intersog.mysteriousnumber.server.models.GameModel;
import com.intersog.mysteriousnumber.server.models.PlayerModel;

import java.util.ArrayList;
import java.util.List;

public class GameRepository {
    private static GameRepository gameRepository;
    private final List<GameModel> games;
    public static GameRepository getGameRepository() {
        if (gameRepository == null) {
            gameRepository = new GameRepository();
        }
        return gameRepository;
    }
    private GameRepository() {
        this.games = new ArrayList<>();
    }
    public List<GameModel> getGames() {
        return this.games;
    }
    public void createGame(PlayerModel playerModel) {
        GameModel gameModel = new GameModel(playerModel);
        playerModel.setGame(gameModel);
        games.add(gameModel);
    }
}
