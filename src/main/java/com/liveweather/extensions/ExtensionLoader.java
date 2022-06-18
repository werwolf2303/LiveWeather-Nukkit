package com.liveweather.extensions;

import com.liveweather.commandline.LWLogging;
import com.liveweather.threading.Low;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ExtensionLoader {
    Runnable l(File extension, boolean ondisable) {
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{extension.toURI().toURL()});
            Class<?> jarclass = classLoader.loadClass("com.extension.Init");
            Object t = jarclass.newInstance();
            if(ondisable) {
                for (Method m : jarclass.getDeclaredMethods()) {
                    if (m.getName().equals("onDisable")) {
                        m.setAccessible(true);
                        Object o = m.invoke(t);
                    }
                }
            }else {
                for (Method m : jarclass.getDeclaredMethods()) {
                    for (Method m2 : Extension.class.getDeclaredMethods()) {
                        if (m.getName().equals(m2.getName())) {
                            m.setAccessible(true);
                            Object o = m.invoke(t);
                        }
                    }
                }
            }
        } catch (MalformedURLException e) {
        } catch (ClassNotFoundException e2) {
        } catch (InvocationTargetException e3) {
        } catch (InstantiationException e4) {
        } catch (IllegalAccessException e5) {
        }
        return null;
    }
    Runnable lsm(File extension, String classname, String methodname) {
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{extension.toURI().toURL()});
            Class<?> jarclass = classLoader.loadClass(classname);
            Object t = jarclass.newInstance();
            for (Method m : jarclass.getDeclaredMethods()) {
                if(m.getName().equals(methodname)) {
                    m.setAccessible(true);
                    Object o = m.invoke(t);
                }
            }
        } catch (MalformedURLException | InvocationTargetException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
        }
        return null;
    }
    public void loadSpecificMethod(File extension, String classname, String methodname) {
        Low low = new Low(lsm(extension, classname, methodname));
        low.start();
    }
    public void load(File extension, boolean ondisable) {
        Low low = new Low(l(extension,ondisable));
        low.start();
    }
}
