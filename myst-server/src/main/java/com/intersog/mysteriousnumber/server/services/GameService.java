package com.intersog.mysteriousnumber.server.services;

import com.intersog.mysteriousnumber.server.models.GameModel;
import com.intersog.mysteriousnumber.server.models.PlayerModel;
import com.intersog.mysteriousnumber.server.repositories.GameRepository;

import java.util.List;

public class GameService {
    private static GameService gameService;
    private final GameRepository gameRepository = GameRepository.getGameRepository();
    public static GameService getGameService() {
        if (gameService == null) {
            gameService = new GameService();
        }
        return gameService;
    }
    private GameService() {}
    public void createGame(PlayerModel playerModel) {
        gameRepository.createGame(playerModel);
    }
    public List<GameModel> getGames() {
        return gameRepository.getGames();
    }
}
