package com.intersog.mysteriousnumber.client.misc;

import com.intersog.mysteriousnumber.client.api.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void clear() {
        final String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Runtime.getRuntime().exec("clear");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void printIncorrectChoice() throws IOException {
        System.out.println(ModString.tabString("Incorrect choice!"));
        System.out.println(ModString.tabString("Press ENTER key to continue..."));
        reader.readLine();
    }
    public static void printAnswerGame(String message) throws IOException {
        System.out.println(ModString.tabString(message));
        System.out.println(ModString.tabString("Press ENTER key to continue..."));
        reader.readLine();
    }
    public static void printIncorrectResponse(Response response) throws IOException {
        System.out.println(ModString.tabString("Incorrect response!"));
        System.out.println(ModString.tabString(response.toString()));
        System.out.println();
        System.out.println(ModString.tabString("Press ENTER key to continue..."));
        reader.readLine();
    }
    public static void printTimer(String str) throws InterruptedException {
        Console.clear();
        System.out.print(ModString.lnBeforeString(ModString.tabString(str)));
        char dot = '.';
        for (int i = 0; i < 10; i++) {
            System.out.print(dot);
            Thread.sleep(100);
        }
    }
    public static BufferedReader getReader() {
        return reader;
    }
}
