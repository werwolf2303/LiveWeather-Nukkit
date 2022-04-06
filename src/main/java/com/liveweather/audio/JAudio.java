package com.liveweather.audio;

import com.liveweather.audio.extensions.MIDI;
import com.liveweather.audio.extensions.MP3;
import com.liveweather.audio.extensions.WAV;

import java.io.File;
import java.io.InputStream;
import java.util.Locale;

public class JAudio {
    String extension = "";
    String name = "";
    WAV wav = new WAV("null");
    MP3 mp3 = new MP3();
    MIDI midi = new MIDI();
    boolean init = false;
    public JAudio(String filename) {
        name = filename;
    }
    public JAudio(File file) {
        name = file.getName();
    }
    public void play() {
        if(!init) {
            if (name.toLowerCase().contains(".wav")) {
                wav = new WAV(name);
                init = true;
                wav.playSound();
            } else {
                if (name.toLowerCase().contains(".mp3")) {
                    init = true;
                    mp3.play(name);
                } else {
                    if (name.toLowerCase().contains(".midi")) {
                        midi = new MIDI();
                        init = true;
                        midi.changeDev(1);
                        midi.repeat = MIDI.cycleType.none;
                        midi.changeCycleMethod();
                        midi.changeMidi(new File(name), true);
                    }
                }
            }
        }else{
            if(name.toLowerCase().contains(".midi")) {
                midi.togglePause();
            }
        }
    }
    public void pause() {
        if(name.toLowerCase().contains(".midi")) {
            if(init) {
                midi.togglePause();
            }
        }
    }
    public void stop() {
        if(name.toLowerCase().contains(".wav")) {
            if(init) {
                wav.stopSound();
            }
        }else{
            if(name.toLowerCase().contains(".mp3")) {
                if(init) {
                    mp3.stopAudio();
                }
            }else{
                if(name.toLowerCase().contains(".midi")) {
                    if(init) {
                        midi.midiStop();
                    }
                }
            }
        }
    }
}
