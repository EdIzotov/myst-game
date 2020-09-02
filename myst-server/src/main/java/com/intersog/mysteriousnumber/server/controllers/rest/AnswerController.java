package com.intersog.mysteriousnumber.server.controllers.rest;

import com.intersog.mysteriousnumber.server.config.GameConfig;
import com.intersog.mysteriousnumber.server.controllers.Response;
import com.intersog.mysteriousnumber.server.models.GameModel;
import com.intersog.mysteriousnumber.server.models.PlayerModel;
import com.intersog.mysteriousnumber.server.services.PlayerService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

public class AnswerController {
    private final static PlayerService playerService = PlayerService.getPlayerService();
    public static void handle(HttpExchange request) throws IOException {
        String method = request.getRequestMethod();
        if (method.equals("POST")) {
            Map<String, String> parametersFromRequest = Response.getParametersFromRequest(request);
            String playerName = parametersFromRequest.get("player");
            String answerParam = parametersFromRequest.get("answer");
            if (playerName.isEmpty()) {
                Response.sendResponseBody(request, 400, Errors.PLAYERNAME_PARAM_MISSING);
                return;
            }
            if (answerParam.isEmpty()) {
                Response.sendResponseBody(request, 400, Errors.ANSWER_PARAM_MISSING);
                return;
            }
            if (!Response.validatePlayerName(playerName)) {
                Response.sendResponseBody(request, 400, Errors.PLAYERNAME_IS_INVALID);
                return;
            }
            PlayerModel player = playerService.getPlayerByPlayerName(playerName);
            if (player == null) {
                Response.sendResponseBody(request, 400, Errors.PLAYERNAME_NOT_FOUND);
                return;
            }
            try {
                int answer = Integer.parseInt(answerParam);
                if (answer < GameConfig.MIN || answer > GameConfig.MAX) {
                    Response.sendResponseBody(request, 400, Errors.ANSWER_IS_OUT_RANGE);
                    return;
                }
                GameModel game = player.getGame();
                if (game == null) {
                    Response.sendResponseBody(request, 400, Errors.GAME_DOESNT_EXIST);
                    return;
                }
                Response.sendResponseBody(request, 200, "player=" + playerName + "&" + "answer=" + game.answer(answer));
            } catch (NumberFormatException e) {
                Response.sendResponseBody(request, 400, Errors.ANSWER_IS_NOT_NUMBER);
            }
        } else {
            Response.sendResponseBody(request, 405, Errors.METHOD_NOT_ALLOWED);
        }
    }
}
