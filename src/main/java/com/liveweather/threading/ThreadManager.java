package com.liveweather.threading;

import com.liveweather.instances.InstanceManager;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class ThreadManager {
    private ArrayList<Thread> threads = null;
    public ThreadManager() {
        threads = new ArrayList<>();
    }
    public int add(Thread t) {
        threads.add(t);
        return threads.size();
    }
    public void remove(Thread t) {
        threads.remove(t);
    }
    public Thread getThreadAt(int positionInArray) {
        try {
            return threads.get(positionInArray);
        }catch (ArrayIndexOutOfBoundsException aioobe) {
            InstanceManager.getLogger().error("Tried to get thread at '" + positionInArray + "' but the thread doesnt exist");
        }
        return null;
    }
    public void clear() {
        threads.clear();
    }
    public int getSize() {
        return threads.size();
    }
}
