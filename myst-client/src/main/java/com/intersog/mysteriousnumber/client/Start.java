package com.intersog.mysteriousnumber.client;

import com.intersog.mysteriousnumber.client.api.Response;
import com.intersog.mysteriousnumber.client.api.RestClient;
import com.intersog.mysteriousnumber.client.game.menu.NewGameMenu;
import com.intersog.mysteriousnumber.client.game.menu.PlayMenu;
import com.intersog.mysteriousnumber.client.misc.Console;
import com.intersog.mysteriousnumber.client.models.GameModel;
import com.intersog.mysteriousnumber.client.models.PlayerModel;

import java.io.IOException;
import java.util.Map;

public class Start {
    public static void main(String[] args) throws IOException, InterruptedException {
        StartMenu startMenu = new StartMenu();
        startMenu.printStartGame();
        startMenu.setPlayMenu(PlayMenu.getMenu());
        startMenu.setStartGameKey(startMenu.getPlayMenu().printMenu());

        Response response;
        Map<String, String> parametersFromRequest;

        while (startMenu.getStartGameKey() >= 0) {
            switch (startMenu.getStartGameKey()) {
                case 1:
                    startMenu.setPlayer(new PlayerModel(startMenu.inputPlayerName()));
                    //validate request
                    response = RestClient.postPlayer(startMenu.getPlayer().getPlayerName());
                    if (response.getStatusCode() != 200) {
                        Console.printIncorrectResponse(response);
                        break;
                    }
                    parametersFromRequest = Response.getParametersFromRequest(response.getResponseMessage());
                    NewGameMenu newGameMenu = NewGameMenu.getMenu();
                    newGameMenu.setDisplayedContinueGameMenuOption(Boolean.parseBoolean(parametersFromRequest.get("game")));
                    startMenu.setNewGameKey(newGameMenu.printMenu());
                    while (startMenu.getNewGameKey() >= 0) {
                        String minParam;
                        String maxParam;
                        int min;
                        int max;
                        int chooseNumberKey;
                        switch (startMenu.getNewGameKey()) {
                            case 1:
                                response = RestClient.postGame(startMenu.getPlayer().getPlayerName());
                                if (response.getStatusCode() != 200) {
                                    Console.printIncorrectResponse(response);
                                    startMenu.exitToStartGameMenu();
                                    break;
                                }
                                parametersFromRequest = Response.getParametersFromRequest(response.getResponseMessage());
                                boolean gameCreated = Boolean.parseBoolean(parametersFromRequest.get("game"));
                                if (!gameCreated) {
                                    Console.printIncorrectResponse(response);
                                    break;
                                }
                                minParam = parametersFromRequest.get("min");
                                maxParam = parametersFromRequest.get("max");
                                if (minParam == null || minParam.length() == 0 || maxParam == null || maxParam.length() == 0) {
                                    Console.printIncorrectResponse(response);
                                    break;
                                }
                                try {
                                    min = Integer.parseInt(minParam);
                                    max = Integer.parseInt(maxParam);
                                } catch (NumberFormatException e) {
                                    Console.printIncorrectResponse(response);
                                    break;
                                }
                                startMenu.getPlayer().setGame(new GameModel(startMenu.getPlayer(), min, max));
                                chooseNumberKey = -1;
                                while (chooseNumberKey != 0) {
                                    chooseNumberKey = startMenu.getPlayer().getGame().chooseNumber();
                                    if (chooseNumberKey == 0) {
                                        startMenu.exitToStartGameMenu();
                                        startMenu.setStartGameKey(startMenu.getPlayMenu().printMenu());
                                        break;
                                    }
                                    response = RestClient.postAnswer(startMenu.getPlayer().getPlayerName(), chooseNumberKey);
                                    if (response.getStatusCode() != 200) {
                                        Console.printIncorrectResponse(response);
                                        continue;
                                    }
                                    Map<String, String> pars = Response.getParametersFromRequest(response.getResponseMessage());
                                    String answerPar = pars.get("answer");
                                    if (answerPar == null || answerPar.length() == 0) {
                                        Console.printIncorrectResponse(response);
                                        break;
                                    }
                                    switch (answerPar) {
                                        case "win":
                                            Console.printAnswerGame("You win!");
                                            chooseNumberKey = 0;
                                            startMenu.exitToStartGameMenu();
                                            startMenu.setStartGameKey(startMenu.getPlayMenu().printMenu());
                                            break;
                                        case "less":
                                            Console.printAnswerGame("Your number is more than the mysterious one...");
                                            break;
                                        case "more":
                                            Console.printAnswerGame("Your number is less than the mysterious one...");
                                            break;
                                    }
                                    startMenu.getPlayer().getGame().addAnswer(chooseNumberKey);
                                }
                                break;
                            case 2:
                                response = RestClient.getGame(startMenu.getPlayer().getPlayerName());
                                if (response.getStatusCode() != 200) {
                                    Console.printIncorrectResponse(response);
                                    startMenu.exitToStartGameMenu();
                                    startMenu.setStartGameKey(startMenu.getPlayMenu().printMenu());
                                    break;
                                }
                                parametersFromRequest = Response.getParametersFromRequest(response.getResponseMessage());
                                String answers = parametersFromRequest.get("answers");
                                minParam = parametersFromRequest.get("min");
                                maxParam = parametersFromRequest.get("max");
                                if (minParam == null || minParam.length() == 0 || maxParam == null || maxParam.length() == 0) {
                                    Console.printIncorrectResponse(response);
                                    break;
                                }
                                try {
                                    min = Integer.parseInt(minParam);
                                    max = Integer.parseInt(maxParam);
                                } catch (NumberFormatException e) {
                                    Console.printIncorrectResponse(response);
                                    break;
                                }
                                startMenu.getPlayer().setGame(new GameModel(startMenu.getPlayer(), min, max));
                                startMenu.getPlayer().getGame().addAnswers(answers);
                                chooseNumberKey = -1;
                                while (chooseNumberKey != 0) {
                                    chooseNumberKey = startMenu.getPlayer().getGame().chooseNumber();
                                    if (chooseNumberKey == 0) {
                                        startMenu.exitToStartGameMenu();
                                        startMenu.setStartGameKey(startMenu.getPlayMenu().printMenu());
                                        break;
                                    }
                                    response = RestClient.postAnswer(startMenu.getPlayer().getPlayerName(), chooseNumberKey);
                                    if (response.getStatusCode() != 200) {
                                        Console.printIncorrectResponse(response);
                                        continue;
                                    }
                                    Map<String, String> pars = Response.getParametersFromRequest(response.getResponseMessage());
                                    String answerPar = pars.get("answer");
                                    if (answerPar == null || answerPar.length() == 0) {
                                        Console.printIncorrectResponse(response);
                                        break;
                                    }
                                    switch (answerPar) {
                                        case "win":
                                            Console.printAnswerGame("You win!");
                                            chooseNumberKey = 0;
                                            startMenu.exitToStartGameMenu();
                                            startMenu.setStartGameKey(startMenu.getPlayMenu().printMenu());
                                            break;
                                        case "less":
                                            Console.printAnswerGame("Your number is more than the mysterious one...");
                                            break;
                                        case "more":
                                            Console.printAnswerGame("Your number is less than the mysterious one...");
                                            break;
                                    }
                                    startMenu.getPlayer().getGame().addAnswer(chooseNumberKey);
                                }
                                break;
                            case 0:
                                startMenu.exitToStartGameMenu();
                                startMenu.setStartGameKey(startMenu.getPlayMenu().printMenu());
                                break;
                        }
                    }
                    break;
                case 0:
                    startMenu.printEndGame();
                    startMenu.setStartGameKey(-1);
                    break;
            }
        }
    }
}
