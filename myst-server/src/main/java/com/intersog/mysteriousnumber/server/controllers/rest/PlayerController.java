package com.intersog.mysteriousnumber.server.controllers.rest;

import com.intersog.mysteriousnumber.server.controllers.Response;
import com.intersog.mysteriousnumber.server.models.PlayerModel;
import com.intersog.mysteriousnumber.server.services.PlayerService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

public class PlayerController {
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
                if (player != null) {
                    boolean gameExists = player.gameExists();
                    Response.sendResponseBody(request, 200, "player=" + playerName + "&" + "game=" + gameExists);
                } else {
                    String name = playerService.createPlayer(playerName).getPlayerName();
                    Response.sendResponseBody(request, 200, "player=" + name + "&" + "game=" + false);
                }
            }
        } else if (method.equals("GET")) {
            Response.sendResponseBody(request, 200, "players=" + playerService.getPlayers());
        } else {
            Response.sendResponseBody(request, 405, Errors.METHOD_NOT_ALLOWED);
        }
    }
}
