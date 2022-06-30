package com.plugindownloader;

import cn.nukkit.Server;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Downloader {
    public static boolean download(String remotePath, String localPath) {
        boolean toreturn = true;
        BufferedInputStream in = null;
        FileOutputStream out = null;
        localPath = localPath.replace("\\", "/") + remotePath.split("/")[remotePath.split("/").length-1];
        try {
            URL url = new URL(remotePath);
            URLConnection conn = url.openConnection();
            int size = conn.getContentLength();
            if (size < 0) {
                Server.getInstance().getLogger().error("Cant download plugin: Could not get file size");
                toreturn = false;
            }
            in = new BufferedInputStream(url.openStream());
            out = new FileOutputStream(localPath);
            byte data[] = new byte[1024];
            int count;
            double sumCount = 0.0;
            boolean activateloop = false;
            int last = 0;
            int current = 0;
            while ((count = in.read(data, 0, 1024)) != -1) {
                out.write(data, 0, count);
                sumCount += count;
                String perc = String.valueOf(Math.round(sumCount / size * 100.0) / 10);
                int iperc = Integer.parseInt(perc);
                if (size > 0) {
                    current = iperc;
                    activateloop = true;
                }
                if (activateloop) {
                    if(!(last ==current)) {
                        Server.getInstance().getLogger().info(perc + "0%");
                        last = iperc;
                    }
                }
            }
        } catch (MalformedURLException e1) {
            Server.getInstance().getLogger().info("!FAILURE! Cant download plugin");
            toreturn = false;
        } catch (IOException e2) {
            Server.getInstance().getLogger().info("!FAILURE! Cant download plugin");
            toreturn = false;
            e2.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e3) {
                    Server.getInstance().getLogger().info("!FAILURE! Cant download plugin");
                    toreturn = false;
                }
            if (out != null)
                try {
                    out.close();
                } catch (IOException e4) {
                    Server.getInstance().getLogger().info("!FAILURE! Cant download plugin");
                    toreturn = false;
                }
        }
        return toreturn;
    }
}
