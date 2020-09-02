package com.intersog.mysteriousnumber.client.models;

import com.intersog.mysteriousnumber.client.models.GameModel;
import com.intersog.mysteriousnumber.client.misc.Console;
import com.intersog.mysteriousnumber.client.misc.ModString;

import java.io.BufferedReader;
import java.io.IOException;

public class PlayerModel {
    private String playerName;
    private boolean gameExists;
    private GameModel game;

    public PlayerModel(String playerName) {
        this.playerName = playerName;
//        this.playerName = parametersFromRequest.get("player");
//        if (this.playerName == null) {
//            Console.printIncorrectResponse(response);
//            return;
//        }
//        this.gameExists = Boolean.parseBoolean(parametersFromRequest.get("game"));
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public boolean isGameExists() {
        return gameExists;
    }
    public void setGameExists(boolean gameExists) {
        this.gameExists = gameExists;
    }

    public GameModel getGame() {
        return game;
    }

    public void setGame(GameModel game) {
        this.game = game;
    }
}
