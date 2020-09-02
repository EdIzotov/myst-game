package com.intersog.mysteriousnumber.client.game.menu;

import com.intersog.mysteriousnumber.client.misc.ModString;

public class MenuOption {
    private int menuNumber;
    private String menuTitle;
    private boolean isDisplayed;
    public MenuOption(int menuNumber, String menuTitle) {
        this.menuNumber = menuNumber;
        this.menuTitle = menuTitle;
        this.isDisplayed = true;
    }
    public int getMenuNumber() {
        return menuNumber;
    }
    public String getMenuTitle() {
        return menuTitle;
    }
    public boolean isDisplayed() {
        return isDisplayed;
    }
    public void setDisplayed(boolean displayed) {
        isDisplayed = displayed;
    }
    @Override
    public String toString() {
        return ModString.tabString("--- " + this.menuNumber + "\t" + this.menuTitle);
    }
}
