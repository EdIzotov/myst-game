package com.intersog.mysteriousnumber.server.models;

import com.intersog.mysteriousnumber.server.config.GameConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameModel {
    private PlayerModel playerModel;
    private final int number;
    private final List<Integer> answers;
    public GameModel(PlayerModel playerModel) {
//        SecureRandom secureRandom = new SecureRandom();
//        this.number = secureRandom.nextInt() & Integer.MAX_VALUE;
        this.number = ThreadLocalRandom.current().nextInt(GameConfig.MIN, GameConfig.MAX + 1);
        this.answers = new ArrayList<>();
        this.playerModel = playerModel;
    }
    public String answer(int answer) {
        this.answers.add(answer);
        if (answer == this.number) {
            this.playerModel.setGame(null);
            this.playerModel = null;
            return "win";
        }
        System.out.println(answer < this.number ? "more" : "less");
        System.out.println(answer);
        System.out.println(this.number);
        return answer < this.number ? "more" : "less";
    }

    public String getAnswersAsString() {
        String stringifiedIntegers = "";
        for (Integer answer: this.answers) {
            stringifiedIntegers += answer + " ";
        }
        return stringifiedIntegers;
    }
}
