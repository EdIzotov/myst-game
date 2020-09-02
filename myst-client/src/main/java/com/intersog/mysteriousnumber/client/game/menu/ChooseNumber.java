package com.intersog.mysteriousnumber.client.game.menu;

public class ChooseNumber {
    private static ChooseNumber menu;
    private ChooseNumber() {}
    public static ChooseNumber getMenu() {
        if (menu == null) {
            menu = new ChooseNumber();
        }
        return menu;
    }
}
