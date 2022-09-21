package com.liveweather.server;


import cn.nukkit.Server;
import com.liveweather.instances.InstanceManager;
import com.liveweather.storage.YAMLConfig;
import com.sun.net.httpserver.HttpExchange;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class Handler {
    static String ips = "";
    public static void root(HttpExchange exchange) {
        if(ips.contains(exchange.getRemoteAddress().getAddress().toString())) {
            try {
                if(exchange.getRequestURI().toString().contains("logoff.html")) {
                    ips = ips.replace(exchange.getRemoteAddress().getAddress().toString(), "");
                    try {
                        String response = "<script>window.location.href='index.html';</script>";
                        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }catch (IOException ex) {

                    }
                }else {
                    if (exchange.getRequestURI().toString().equals("")) {
                        File file = new File(InstanceManager.getServer().getPluginPath() + "/LiveWeather/jarfile/sechtml/" + "index.html");
                        exchange.sendResponseHeaders(200, file.length());
                        try (OutputStream os = exchange.getResponseBody()) {
                            Files.copy(file.toPath(), os);
                        }
                    } else {
                        File file = new File(InstanceManager.getServer().getPluginPath() + "/LiveWeather/jarfile/sechtml/" + exchange.getRequestURI().toString());
                        exchange.sendResponseHeaders(200, file.length());
                        try (OutputStream os = exchange.getResponseBody()) {
                            Files.copy(file.toPath(), os);
                        }
                    }
                }
            } catch (IOException e) {
                try {
                    String response = "404 Not found";
                    exchange.sendResponseHeaders(404, response.getBytes().length);//response code and length
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }catch (IOException ex) {

                }
            }
        }
        if(exchange.getRequestURI().toString().contains("favicon.ico")) {
            try {
                String response = "404 Not found";
                exchange.sendResponseHeaders(404, response.getBytes().length);//response code and length
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }catch (IOException ex) {

            }
        }else {
            if(exchange.getRequestURI().toString().contains("/login.html")) {
                String args = exchange.getRequestURI().toString();
                String[] conv = args.split("/");
                args = conv[conv.length - 1];
                String response = "";
                if(args.contains(new YAMLConfig().read("configserverpassword"))) {
                    response = "<script>\n" +
                            "    window.location.href=\"index.html\";\n" +
                            "</script>";
                    ips = ips+exchange.getRemoteAddress().getAddress();
                }else{
                    response = "<script>\n" +
                            "    window.location.href=\"index.html\";\n" +
                            "</script>";
                }
                try {
                    exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } catch (IOException e) {
                }
            }
            try {
                if (exchange.getRequestURI().toString().equals("/")) {
                    File file2 = new File(InstanceManager.getServer().getPluginPath() + "/LiveWeather/jarfile/html/" + "index.html");
                    exchange.sendResponseHeaders(200, file2.length());
                    try (OutputStream os = exchange.getResponseBody()) {
                        Files.copy(file2.toPath(), os);
                    }
                } else {
                    File file = new File(InstanceManager.getServer().getPluginPath() + "/LiveWeather/jarfile/html/" + exchange.getRequestURI().toString());
                    exchange.sendResponseHeaders(200, file.length());
                    try (OutputStream os = exchange.getResponseBody()) {
                        Files.copy(file.toPath(), os);
                    }
                }
            } catch (IOException e) {
            }
        }
    }
    public static void easter(HttpExchange exchange)  {
        try {
            byte[] bytes = new byte[] {60,115,99,114,105,112,116,62,97,108,101,114,116,40,39,89,111,117,32,114,101,97,100,32,109,121,32,115,111,117,114,99,101,32,99,111,100,101,32,87,79,87,33,32,89,111,117,32,102,111,117,110,100,32,97,32,101,97,115,116,101,114,101,103,103,39,41,59,60,47,115,99,114,105,112,116,62,60,105,102,114,97,109,101,32,97,108,108,111,119,61,39,97,117,116,111,112,108,97,121,39,32,115,116,121,108,101,61,39,119,105,100,116,104,58,49,48,48,37,59,104,101,105,103,104,116,58,49,48,48,37,39,32,115,114,99,61,39,104,116,116,112,115,58,47,47,119,119,119,46,121,111,117,116,117,98,101,46,99,111,109,47,101,109,98,101,100,47,115,50,49,49,57,45,119,114,74,111,48,63,97,117,116,111,112,108,97,121,61,49,39,62,60,47,105,102,114,97,109,101,62};
            String response = new String(bytes);
            exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }catch (IOException t) {

        }
    }
    public static void config(HttpExchange exchange) {
        if(ips.contains(exchange.getRemoteAddress().getAddress().toString())) {
            if(exchange.getRequestURI().toString().contains("?")) {
                String args = exchange.getRequestURI().toString().replace("config", "").replace("?", "").split("/")[1];
                if(args.contains("autofindplayercityset")) {
                    String bool = args.split("=")[1];
                    try {
                        String response = String.valueOf(new YAMLConfig().writeReturn("autofindplayercity", bool));
                        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }catch (IOException t) {

                    }
                    return;
                }
                if(args.contains("languageset")) {
                    String bool = args.split("=")[1];
                    try {
                        String response = String.valueOf(new YAMLConfig().writeReturn("language", bool));
                        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }catch (IOException t) {

                    }
                    return;
                }
                if(args.contains("cloudlyget")) {
                    try {
                        String response = String.valueOf(new YAMLConfig().read("cloudly"));
                        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }catch (IOException t) {

                    }
                    return;
                }
                if(args.contains("cloudlyset")) {
                    String bool = args.split("=")[1];
                    try {
                        String response = String.valueOf(new YAMLConfig().writeReturn("cloudly", bool));
                        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }catch (IOException t) {

                    }
                    return;
                }
                if(args.contains("languageget")) {
                    try {
                        String response = String.valueOf(new YAMLConfig().read("language"));
                        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }catch (IOException t) {

                    }
                    return;
                }
                if(args.contains("permissionsset")) {
                    String bool = args.split("=")[1];
                    try {
                        String response = String.valueOf(new YAMLConfig().writeReturn("permissions", bool));
                        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }catch (IOException t) {

                    }
                    return;
                }
                if(args.contains("permissionsget")) {
                    try {
                        String response = String.valueOf(new YAMLConfig().read("permissions"));
                        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }catch (IOException t) {

                    }
                    return;
                }
                if(args.contains("apikeyget")) {
                    try {
                        String response = String.valueOf(new YAMLConfig().read("apikey"));
                        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }catch (IOException t) {

                    }
                    return;
                }
                if(args.contains("apikeyset")) {
                    String bool = args.split("=")[1];
                    try {
                        String response = String.valueOf(new YAMLConfig().writeReturn("apikey", bool));
                        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }catch (IOException t) {

                    }
                    return;
                }
                if(args.contains("configserverset")) {
                    String bool = args.split("=")[1];
                    try {
                        String response = String.valueOf(new YAMLConfig().writeReturn("configserver", bool));
                        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }catch (IOException t) {

                    }
                    return;
                }
                if(args.contains("configserverget")) {
                    try {
                        String response = String.valueOf(new YAMLConfig().read("configserver"));
                        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }catch (IOException t) {

                    }
                    return;
                }
                if(args.contains("passwordget")) {
                    try {
                        String response = String.valueOf(new YAMLConfig().read("configserverpassword"));
                        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }catch (IOException t) {

                    }
                    return;
                }
                if(args.contains("autofindplayercityget")) {
                    try {
                        String response = String.valueOf(new YAMLConfig().read("autofindplayercity"));
                        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }catch (IOException t) {

                    }
                    return;
                }
                try {
                    String response = "<a>" + args + "</a>";
                    exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }catch (IOException ex) {

                }
            }
        }else{
            try {
                String response = "<script>alert('Nice try! But if you already here have something');</script><iframe allow='autoplay' style='width:100%;height:100%' src='https://www.youtube.com/embed/Qskm9MTz2V4?autoplay=1'></iframe>";
                exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }catch (IOException t) {

            }
        }
    }
}
