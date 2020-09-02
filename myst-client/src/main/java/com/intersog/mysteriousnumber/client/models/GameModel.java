package com.intersog.mysteriousnumber.client.models;

import com.intersog.mysteriousnumber.client.misc.Console;
import com.intersog.mysteriousnumber.client.misc.ModString;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameModel {
    private final PlayerModel player;
    private final int min;
    private final int max;
    private final List<Integer> answers;

    public GameModel(PlayerModel player, int min, int max) {
        this.player = player;
        this.min = min;
        this.max = max;
        answers = new ArrayList<>();
    }
    public void addAnswer(int answer) {
        this.answers.add(answer);
    }
    public void addAnswers(String answers) {
        if (!answers.isEmpty()) {
            int[] numbers = Arrays.stream(answers.split("\\+")).mapToInt(Integer::parseInt).toArray();
            for (int number: numbers) {
                this.answers.add(number);
            }
        }
    }
    public int chooseNumber() throws IOException {
        BufferedReader reader = Console.getReader();
        int number = this.min - 1;
        while (number == this.min - 1) {
            Console.clear();
            System.out.print(ModString.lnBeforeString(ModString.tabString("The mysterious number is between " + this.min + " and " + this.max)));
            System.out.print(ModString.tabString("You already entered: " + this.getAnswers()));
            System.out.print(ModString.lnBeforeString(ModString.tabString("Enter your answer (0 to exit): ")));
            String numberString = reader.readLine().trim();
            try {
                number = Integer.parseInt(numberString);
            } catch (NumberFormatException e) {
                this.printMessage("Not a number?");
            }
            if (number == 0) {
                return number;
            }
            if (number < this.min) {
                this.printMessage("Too small number " + player.getPlayerName() + "!");
                number = this.min - 1;
            } else if (number > this.max) {
                this.printMessage("Too large number " + player.getPlayerName() + "!");
                number = this.min - 1;
            }
            if (this.answers.contains(number)) {
                this.printMessage(player.getPlayerName() + ", you already entered this number!");
                number = this.min - 1;
            }
        }
        return number;
    }
    public void printMessage(String message) throws IOException {
        System.out.println(ModString.tabString(message));
        System.out.println(ModString.tabString("Press ENTER key to continue..."));
        BufferedReader reader = Console.getReader();
        reader.readLine();
    }
    public List<Integer> getAnswers() {
        return answers;
    }
}
