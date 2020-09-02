package com.intersog.mysteriousnumber.client.game.menu;

public class PlayMenu extends Menu {
    private static PlayMenu menu;
    public PlayMenu() {
        this.addOption(new MenuOption(1, "Play"));
        this.addOption(new MenuOption(0, "Exit"));
    }
    public static PlayMenu getMenu() {
        if (menu == null) {
            menu = new PlayMenu();
        }
        return menu;
    }
}
