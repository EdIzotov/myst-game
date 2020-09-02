package com.intersog.mysteriousnumber.server.controllers.rest;

import com.intersog.mysteriousnumber.server.controllers.Response;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class ErrorController {
    public static void handle(HttpExchange request) throws IOException {
        Response.sendResponseBody(request, 404, Errors.PAGE_NOT_FOUND);
    }
}
