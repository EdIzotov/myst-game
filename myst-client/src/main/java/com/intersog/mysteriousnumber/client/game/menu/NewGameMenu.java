package com.intersog.mysteriousnumber.client.game.menu;

import java.util.Map;

public class NewGameMenu extends Menu {
    private static NewGameMenu menu;
    private NewGameMenu() {
        this.addOption(new MenuOption(1, "New Game"));
        this.addOption(new MenuOption(2, "Continue Game"));
        this.addOption(new MenuOption(0, "Exit"));
    }
    public static NewGameMenu getMenu() {
        if (menu == null) {
            menu = new NewGameMenu();
        }
        return menu;
    }
    public void setDisplayedContinueGameMenuOption(boolean gameExists) {
        this.options.get(2).setDisplayed(false);
        for (Map.Entry<Integer, MenuOption> option: this.options.entrySet()) {
            if (option.getValue().getMenuTitle().equals("Continue Game")) {
                option.getValue().setDisplayed(gameExists);
            }
        }
    }
}
