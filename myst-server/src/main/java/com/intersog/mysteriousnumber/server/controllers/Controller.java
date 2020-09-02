package com.intersog.mysteriousnumber.server.controllers;

import com.intersog.mysteriousnumber.server.config.ServerConfig;
import com.intersog.mysteriousnumber.server.controllers.rest.AnswerController;
import com.intersog.mysteriousnumber.server.controllers.rest.ErrorController;
import com.intersog.mysteriousnumber.server.controllers.rest.GameController;
import com.intersog.mysteriousnumber.server.controllers.rest.PlayerController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class Controller implements HttpHandler {
    public void handle(HttpExchange request) throws IOException {
        final String PLAYERS_ENDPOINT = "/players";
        final String GAMES_ENDPOINT = "/games";
        final String ANSWERS_ENDPOINT = "/answers";
        System.out.print(request.getRequestMethod() + " ");
        System.out.println(request.getRequestURI().toString());
        String controllerPath = request.getRequestURI().getPath().substring(ServerConfig.CONTEXT_URI.length());
        switch (controllerPath) {
            case PLAYERS_ENDPOINT:
                PlayerController.handle(request);
                break;
            case GAMES_ENDPOINT:
                GameController.handle(request);
                break;
            case ANSWERS_ENDPOINT:
                AnswerController.handle(request);
                break;
            default:
                ErrorController.handle(request);
                break;
        }
    }
}
