package com.intersog.mysteriousnumber.server.controllers;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Response {
    public static void sendResponseBody(HttpExchange request, int statusCode, String message) throws IOException {
        request.sendResponseHeaders(statusCode, message.length());
        OutputStream os = request.getResponseBody();
        os.write(message.getBytes());
        os.close();
    }
    public static Map<String, String> getParametersFromRequest(HttpExchange request) throws IOException {
        StringBuilder parameterString = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(request.getRequestBody(), StandardCharsets.UTF_8));
        String str;
        while ((str = in.readLine()) != null) {
            parameterString.append(str);
        }
        Map<String, String> parametersMap = new HashMap<>();
        String[] params = parameterString.toString().split("&");
        for (String param: params) {
            String[] kv = param.split("=", -1);
            parametersMap.put(kv[0], kv[1]);
        }
        in.close();
        return parametersMap;
    }
    public static boolean validatePlayerName(String playerName) {
        final Pattern pattern = Pattern.compile("^[A-Za-z0-9]++$");
        return pattern.matcher(playerName).matches();
    }
}
