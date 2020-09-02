package com.intersog.mysteriousnumber.server;

import com.intersog.mysteriousnumber.server.config.ServerConfig;
import com.intersog.mysteriousnumber.server.config.ServerExecutor;
import com.intersog.mysteriousnumber.server.controllers.Controller;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class StartGameServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(ServerConfig.HOSTNAME, ServerConfig.PORT), 0);
        server.createContext(ServerConfig.CONTEXT_URI, new Controller());
        server.setExecutor(ServerExecutor.get());
        server.start();
        System.out.println("PORT - " + ServerConfig.PORT);
        System.out.println("Server started...");
    }
}
