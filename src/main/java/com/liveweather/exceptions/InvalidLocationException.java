package com.liveweather.exceptions;

public class InvalidLocationException extends Exception {
    public InvalidLocationException() {
        super("Invalid location");
    }
}
