package com.intersog.mysteriousnumber.server.models;

public class PlayerModel {
    private final String playerName;
    private GameModel game;

    public PlayerModel(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
    public GameModel getGame() {
        return game;
    }
    public void setGame(GameModel game) {
        this.game = game;
    }

    public boolean gameExists() {
        return this.game != null;
    }

    @Override
    public String toString() {
        return "playerName=" + playerName;
    }
}
