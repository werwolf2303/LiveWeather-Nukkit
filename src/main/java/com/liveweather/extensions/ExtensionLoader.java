package com.liveweather.extensions;

import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;
import com.liveweather.threading.Low;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ExtensionLoader {
    String extensionname = "";
    Runnable l(File extension, boolean ondisable) {
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{extension.toURI().toURL()});
            Class<?> jarclass = classLoader.loadClass("com.extension.Init");
            Object t = jarclass.newInstance();
            boolean foundextensionname = false;
            if(ondisable) {
                for(Method m2 : jarclass.getDeclaredMethods()) {
                    if(m2.getName().equals("getExtensionName")) {
                        m2.setAccessible(true);
                        Object o = m2.invoke(t);
                        foundextensionname = true;
                    }
                }
                for (Method m : jarclass.getDeclaredMethods()) {
                    if(foundextensionname) {
                        if (m.getName().equals("onDisable")) {
                            m.setAccessible(true);
                            Object o = m.invoke(t);
                        }
                    }
                }
            }else {
                for(Method m2 : jarclass.getDeclaredMethods()) {
                    if (m2.getName().equals("getExtensionName")) {
                        m2.setAccessible(true);
                        Object o = m2.invoke(t);
                        extensionname = o.toString();
                        foundextensionname = true;
                        new LWLogging().extension(new Language().get("liveweather.extension.load") + " " + o.toString());
                    }
                }
                for (Method m : jarclass.getDeclaredMethods()) {
                    if(foundextensionname) {
                        if (m.getName().equals("onLoad")) {
                            m.setAccessible(true);
                            Object o = m.invoke(t);
                        }
                        if (m.getName().equals("dumpFunctions")) {
                            m.setAccessible(true);
                            Object o = m.invoke(t);
                            if (o.toString().equals("true")) {
                                dumpFunctions(extension);
                            } else {
                                new LWLogging().error(new Language().get("liveweather.extension.invaliddumpfunctions"));
                            }
                        }
                    }
                }
                if(!foundextensionname) {
                    new LWLogging().error(new Language().get("liveweather.extension.noname").replace("[name]", extension.getName()));
                }
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    Runnable dpf(File extension) {
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{extension.toURI().toURL()});
            Class<?> jarclass = classLoader.loadClass("com.extension.Init");
            Object t = jarclass.newInstance();
            new LWLogging().normal(new Language().get("liveweather.extension.startdump") + " "+ extensionname);
            for(Method m2 : jarclass.getDeclaredMethods()) {
                new LWLogging().normal(extensionname + " : " + m2.getName());
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void dumpFunctions(File name) {
        Low l = new Low(dpf(name));
        l.start();
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
