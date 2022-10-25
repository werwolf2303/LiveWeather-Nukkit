package com.liveweather.utils;

import java.io.InputStream;
import java.util.Scanner;

public class Resources {
    public String read(String path) throws IllegalArgumentException {
        try {
            Scanner s = new Scanner(getFileFromResourceAsStream(path)).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            return result;
        }catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException();
        }
    }
    public InputStream getFileFromResourceAsStream(String fileName) throws IllegalArgumentException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException();
        } else {
            return inputStream;
        }
    }
}
