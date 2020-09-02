package com.intersog.mysteriousnumber.server.repositories;

import com.intersog.mysteriousnumber.server.models.PlayerModel;
import com.intersog.mysteriousnumber.server.services.PlayerService;

import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {
    private static PlayerRepository playerRepository;
    private List<PlayerModel> players;
    public static PlayerRepository getPlayerRepository() {
        if (playerRepository == null) {
            playerRepository = new PlayerRepository();
        }
        return playerRepository;
    }
    private PlayerRepository() {
        this.players = new ArrayList<>();
    }
    public PlayerModel getPlayerByPlayerName(String playerName) {
        for (PlayerModel player: this.players) {
            if (player.getPlayerName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }
    public PlayerModel createPlayer(String playerName) {
        PlayerModel playerModel = new PlayerModel(playerName);
        players.add(playerModel);
        return playerModel;
    }
    public List<PlayerModel> getPlayers() {
        return players;
    }
}
