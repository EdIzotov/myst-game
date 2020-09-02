package com.intersog.mysteriousnumber.server.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ServerExecutor {
    private static ThreadPoolExecutor executor;
    public static ThreadPoolExecutor get() {
        if (executor == null) {
            executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        }
        return executor;
    }
}
