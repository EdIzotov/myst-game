package com.intersog.mysteriousnumber.server.controllers.rest;

import com.intersog.mysteriousnumber.server.config.GameConfig;
import com.intersog.mysteriousnumber.server.controllers.Response;
import com.intersog.mysteriousnumber.server.models.PlayerModel;
import com.intersog.mysteriousnumber.server.services.GameService;
import com.intersog.mysteriousnumber.server.services.PlayerService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class GameController {
    private final static GameService gameService = GameService.getGameService();
    private final static PlayerService playerService = PlayerService.getPlayerService();
    public static void handle(HttpExchange request) throws IOException {
        String method = request.getRequestMethod();
        if (method.equals("POST")) {
            Map<String, String> parametersFromRequest = Response.getParametersFromRequest(request);
            String playerName = parametersFromRequest.get("player");
            if (playerName == null || playerName.isEmpty()) {
                Response.sendResponseBody(request, 400, Errors.PLAYERNAME_PARAM_MISSING);
            } else if (!Response.validatePlayerName(playerName)) {
                Response.sendResponseBody(request, 400, Errors.PLAYERNAME_IS_INVALID);
            } else {
                PlayerModel player = playerService.getPlayerByPlayerName(playerName);
                if (player == null) {
                    Response.sendResponseBody(request, 400, Errors.PLAYERNAME_NOT_FOUND);
                } else {
                    gameService.createGame(player);
                    Response.sendResponseBody(request, 200, "player=" + playerName + "&" + "game=" + true + "&" + "min=" + GameConfig.MIN + "&" + "max=" + GameConfig.MAX);
                }
            }
        } else if (method.equals("GET")) {
            String queryString = request.getRequestURI().getQuery();
            if (queryString != null) {
                Map<String, String> parametersMap = new HashMap<>();
                for (String param: queryString.split("&", -1)) {
                    String[] entry = param.split("=", -1);
                    parametersMap.put(entry[0], entry[1]);
                }
                if (!parametersMap.containsKey("player")) {
                    Response.sendResponseBody(request, 400, Errors.PLAYERNAME_PARAM_MISSING);
                }
                String playerName = parametersMap.get("player");
                if (playerName.isEmpty()) {
                    Response.sendResponseBody(request, 400, Errors.PLAYERNAME_PARAM_MISSING);
                } else if (!Response.validatePlayerName(parametersMap.get("player"))) {
                    Response.sendResponseBody(request, 400, Errors.PLAYERNAME_IS_INVALID);
                } else {
                    PlayerModel player = playerService.getPlayerByPlayerName(playerName);
                    if (player == null) {
                        Response.sendResponseBody(request, 400, Errors.PLAYERNAME_NOT_FOUND);
                    } else if (player.getGame() == null) {
                        Response.sendResponseBody(request, 400, Errors.GAME_DOESNT_EXIST);
                    } else {
                        Response.sendResponseBody(request, 200, "min=" + GameConfig.MIN + "&" + "max=" + GameConfig.MAX + "&" + "answers=" + URLEncoder.encode(player.getGame().getAnswersAsString(), "utf-8"));
                    }
                }
            } else {
                Response.sendResponseBody(request, 200, "games=" + gameService.getGames());
            }
        } else {
            Response.sendResponseBody(request, 405, Errors.METHOD_NOT_ALLOWED);
        }
    }
}
