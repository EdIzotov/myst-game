package com.intersog.mysteriousnumber.client.game.menu;

import com.intersog.mysteriousnumber.client.misc.Console;
import com.intersog.mysteriousnumber.client.misc.ModString;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Menu {
    Map<Integer, MenuOption> options = new LinkedHashMap<>();
    private final BufferedReader reader = Console.getReader();
    private int choiceNumber;
    protected void addOption(MenuOption option) {
        this.options.put(option.getMenuNumber(), option);
    }
    public int printMenu() throws IOException {
        Console.clear();
        System.out.println("\n\n\n\n");
        for (Map.Entry<Integer, MenuOption> entry: options.entrySet()) {
            if (entry.getValue().isDisplayed()) {
                System.out.println(entry.getValue());
            }
        }
        System.out.print(ModString.lnBeforeString(ModString.tabString("Make a choice in menu: ")));
        try {
            String choice = reader.readLine();
            this.choiceNumber = Integer.parseInt(choice);
            if (this.options.get(this.choiceNumber) == null || !this.options.get(this.choiceNumber).isDisplayed()) {
                Console.printIncorrectChoice();
                this.printMenu();
            }
        } catch (IOException | NumberFormatException e) {
            Console.printIncorrectChoice();
            this.printMenu();
        }
        return this.getChoiceNumber();
    }
    public int getChoiceNumber() {
        return choiceNumber;
    }
}
