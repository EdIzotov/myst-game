package com.intersog.mysteriousnumber.client.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.stream.Collectors;

public class RestClient {
    public static Response postPlayer(String playerName) throws IOException {
        URL url = new URL(ClientConfig.getConfig().getProperty(ClientConfig.SERVER_URL) + ClientConfig.PLAYERS_ENDPOINT);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("POST");
        request.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; utf-8");
        request.setRequestProperty("Accept", "application/x-www-form-urlencoded; utf-8");
        request.setDoOutput(true);
        try (OutputStreamWriter writer = new OutputStreamWriter(request.getOutputStream())) {
            String data = "player=" + playerName;
            writer.write(data);
            writer.flush();
            BufferedReader br;
            if (request.getResponseCode() >= 200 && request.getResponseCode() < 300) {
                br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(request.getErrorStream()));
            }
            String responseBody = br.lines().collect(Collectors.joining());
            return new Response(request.getResponseCode(), responseBody);
        } catch (ConnectException e) {
            return new Response(500, e.getMessage());
        }
    }
    public static Response postGame(String playerName) throws IOException {
        URL url = new URL(ClientConfig.getConfig().getProperty(ClientConfig.SERVER_URL) + ClientConfig.GAMES_ENDPOINT);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("POST");
        request.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; utf-8");
        request.setRequestProperty("Accept", "application/x-www-form-urlencoded; utf-8");
        request.setDoOutput(true);
        try (OutputStreamWriter writer = new OutputStreamWriter(request.getOutputStream())) {
            String data = "player=" + playerName;
            writer.write(data);
            writer.flush();
            BufferedReader br;
            if (request.getResponseCode() >= 200 && request.getResponseCode() < 300) {
                br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(request.getErrorStream()));
            }
            String responseBody = br.lines().collect(Collectors.joining());
            return new Response(request.getResponseCode(), responseBody);
        } catch (ConnectException e) {
            return new Response(500, e.getMessage());
        }
    }
    public static Response getGame(String playerName) throws IOException, InterruptedException {
        URL url = new URL(ClientConfig.getConfig().getProperty(ClientConfig.SERVER_URL) + ClientConfig.GAMES_ENDPOINT + "?player=" + URLEncoder.encode(playerName, "utf-8"));
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("GET");
        request.setRequestProperty("Accept", "application/x-www-form-urlencoded; utf-8");
        request.setDoOutput(true);
        BufferedReader br;
        if (request.getResponseCode() >= 200 && request.getResponseCode() < 300) {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(request.getErrorStream()));
        }
        String responseBody = br.lines().collect(Collectors.joining());
        return new Response(request.getResponseCode(), responseBody);
    }
    public static Response postAnswer(String playerName, int answer) throws IOException {
        URL url = new URL(ClientConfig.getConfig().getProperty(ClientConfig.SERVER_URL) + ClientConfig.ANSWERS_ENDPOINT);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("POST");
        request.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; utf-8");
        request.setRequestProperty("Accept", "application/x-www-form-urlencoded; utf-8");
        request.setDoOutput(true);
        try (OutputStreamWriter writer = new OutputStreamWriter(request.getOutputStream())) {
            String data = "player=" + playerName + "&answer=" + answer;
            writer.write(data);
            writer.flush();
            BufferedReader br;
            if (request.getResponseCode() >= 200 && request.getResponseCode() < 300) {
                br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(request.getErrorStream()));
            }
            String responseBody = br.lines().collect(Collectors.joining());
            return new Response(request.getResponseCode(), responseBody);
        } catch (ConnectException e) {
            return new Response(500, e.getMessage());
        }
    }
}
