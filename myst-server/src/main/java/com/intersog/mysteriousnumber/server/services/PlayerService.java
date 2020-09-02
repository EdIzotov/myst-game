package com.intersog.mysteriousnumber.server.services;

import com.intersog.mysteriousnumber.server.models.PlayerModel;
import com.intersog.mysteriousnumber.server.repositories.PlayerRepository;

import java.util.List;

public class PlayerService {
    private static PlayerService playerService;
    private final PlayerRepository playerRepository = PlayerRepository.getPlayerRepository();
    public static PlayerService getPlayerService() {
        if (playerService == null) {
            playerService = new PlayerService();
        }
        return playerService;
    }
    private PlayerService() {}
    public PlayerModel getPlayerByPlayerName(String playerName) {
        return playerRepository.getPlayerByPlayerName(playerName);
    }
    public PlayerModel createPlayer(String playerName) {
        return playerRepository.createPlayer(playerName);
    }
    public List<PlayerModel> getPlayers() {
        return playerRepository.getPlayers();
    }
}
