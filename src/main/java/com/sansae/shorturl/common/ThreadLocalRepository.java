package com.sansae.shorturl.common;

public class ThreadLocalRepository {
    private static ThreadLocal<ApplicationLogHandler> threadlocal = new ThreadLocal<ApplicationLogHandler>() {

        @Override
        protected ApplicationLogHandler initialValue() {
            return new ApplicationLogHandler();
        };
    };

    public static ApplicationLogHandler getLogHandler() {
        return threadlocal.get();
    }
    public static void remove() {
        threadlocal.remove();
    }
}
