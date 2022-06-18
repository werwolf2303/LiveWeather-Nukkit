package com.liveweather.server;

import com.liveweather.commandline.LWLogging;
import com.liveweather.storage.Options;
import com.liveweather.threading.Normal;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CreateServer {
    Normal normal;
    HttpServer server;
    Runnable starting() {
        if(new Options().getConfig("configserver").equals("true")) {
            try {
                server = HttpServer.create(new InetSocketAddress(5678),0);
                server.createContext("/", Handler::root);
                server.createContext("/config", Handler::config);
                server.createContext("/youdiscoveredsourcecode", Handler::easter);
                server.setExecutor(null);
                server.start();
            } catch (IOException e) {
                new LWLogging().critical("Cant start config server");
            }
        }
        return null;
    }
    public void start() {
        normal = new Normal(starting());
        normal.start();
    }
    public void stop() {
        if(normal.isRunning()) {
            server.stop(0);
            normal.stop();
        }
    }
}
