package com.liveweather.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class IOUtils {
    public static String toString(InputStream stream, Charset charset) {
        return new BufferedReader(
                new InputStreamReader(stream, charset))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}
