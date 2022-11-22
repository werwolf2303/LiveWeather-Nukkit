package com.liveweather;

public interface Logger {
    public void throwable(Throwable throwable);
    public void fatal(String message);
    public void critical(String message);
    public void extension(String message);
    public void warning(String message);
    public void normal(String message);
    public void error(String message);
    public void debugging(String message);
}
