package com.intersog.mysteriousnumber.client;

import com.intersog.mysteriousnumber.client.game.menu.Menu;
import com.intersog.mysteriousnumber.client.misc.Console;
import com.intersog.mysteriousnumber.client.misc.ModString;
import com.intersog.mysteriousnumber.client.models.PlayerModel;

import java.io.BufferedReader;
import java.io.IOException;

public class StartMenu {
    private int startGameKey;
    private int newGameKey;
    private Menu playMenu;
    private PlayerModel player;

    public StartMenu() {
        this.startGameKey = -1;
        this.newGameKey = -1;
    }
    public int getStartGameKey() {
        return startGameKey;
    }
    public void setStartGameKey(int startGameKey) {
        this.startGameKey = startGameKey;
    }
    public int getNewGameKey() {
        return newGameKey;
    }
    public void setNewGameKey(int newGameKey) {
        this.newGameKey = newGameKey;
    }
    public void exitToStartGameMenu() {
        this.setNewGameKey(-1);
    }
    public Menu getPlayMenu() {
        return playMenu;
    }
    public void setPlayMenu(Menu playMenu) {
        this.playMenu = playMenu;
    }
    public PlayerModel getPlayer() {
        return player;
    }
    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

    public void printStartGame() throws InterruptedException {
        Console.clear();
        System.out.println(ModString.lnBeforeString(ModString.tabString("Welcome!")));
        Thread.sleep(2000);
        Console.printTimer("Loading");
    }
    public void printEndGame() throws InterruptedException {
        Console.clear();
        Console.printTimer("Exiting");
    }
    public String inputPlayerName() throws IOException {
        BufferedReader reader = Console.getReader();
        String playerName = "";
        while (playerName.length() == 0) {
            Console.clear();
            System.out.print(ModString.lnBeforeString(ModString.tabString("Enter name of player: ")));
            playerName = reader.readLine().trim();
        }
        return playerName;
    }
}
