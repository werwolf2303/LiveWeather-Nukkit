package com.liveweather.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class Resources {
    public String readToString(String path) {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        InputStream stream = getClass().getResourceAsStream(path);
        if (stream == null) {
            throw new RuntimeException(new FileNotFoundException());
        }
        return IOUtils.toString(stream, Charset.defaultCharset());
    }
    public InputStream readToInputStream(String path) {
        if(!path.startsWith("/")) {
            path = "/" + path;
        }
        InputStream stream = getClass().getResourceAsStream(path);
        if(stream==null) {
            throw new RuntimeException(new FileNotFoundException());
        }
        return stream;
    }
}
