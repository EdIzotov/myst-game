package com.intersog.mysteriousnumber.client.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class ClientConfig {
    public static final String SERVER_URL = "server-url";
    public static final String PLAYERS_ENDPOINT = "/players";
    public static final String GAMES_ENDPOINT = "/games";
    public static final String ANSWERS_ENDPOINT = "/answers";
    public static Properties getConfig() throws IOException {
        Properties props = new Properties();
        FileInputStream configFile = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + "app.config");
        props.load(configFile);
        configFile.close();
        return props;
    }
}
