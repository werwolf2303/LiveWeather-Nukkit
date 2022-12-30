package com.liveweather;

@SuppressWarnings("ALL")
public interface Logger {
    void throwable(Throwable throwable);
    void fatal(String message);
    void critical(String message);
    void extension(String message);
    void warning(String message);
    void normal(String message);
    void error(String message);
    void debugging(String message);
}
