package com.liveweather.audio.extensions;

import javax.sound.sampled.*;
import java.io.File;
import java.io.InputStream;

public class WAV extends Thread {
    public int loop = 0;
    private boolean isStream = false;
    private boolean isFile = false;
    private InputStream aud = null;
    File soundFile;
    String filename = "";
    int EXTERNAL_BUFFER_SIZE = 524288;

    public WAV(File file) {
        isFile = true;
        soundFile = file;
    }

    public WAV(String file) {
        filename = file;
    }

    public WAV(InputStream stream) {
        isStream = true;
        aud = stream;
    }

    public void playSound() {
        this.start();
    }

    public void stopSound() {
        this.stop();
    }

    public void run() {
        if (!isFile) {
            soundFile = new File(filename);
        }
        if (!soundFile.getPath().toLowerCase().contains(".wav")) {
            String[] extension = soundFile.getPath().split("\\.");
            System.out.println("[ERROR] File not supported: " + extension[1]);
            this.stop();
        }
        if (!soundFile.exists()) {
            System.err.println("[ERROR] Wave file not found: " + filename);
            return;
        }
        AudioInputStream audioInputStream = null;
        try {
            if (isStream) {
                audioInputStream = AudioSystem.getAudioInputStream(aud);
            } else {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    auline.write(abData, 0, nBytesRead);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            auline.drain();
            auline.close();
        }
    }
}