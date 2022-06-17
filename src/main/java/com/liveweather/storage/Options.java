package com.liveweather.storage;


import cn.nukkit.Server;
import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@Deprecated
public class Options {
    boolean usenew = false;
    public Options() {
        String name = "newoptions";
        String toret = "";
        try {
            File myObj = new File(config);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String check = data.split(":")[0];
                if (check.equals(name)) {
                    if (!data.contains("//")) {
                        toret = data.replace(name + ":", "");
                        break;
                    } else {
                        toret = data.split("//")[0].replace(name + ":", "");
                        break;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            new LWLogging().critical(new Language().get("liveweather.options.cantread"));
        }
        usenew = Boolean.getBoolean(toret);
    }
    public String config = Server.getInstance().getFilePath() + "/" + "plugins" + "/" + "LiveWeather" + "/" + "options.cfg";
    Options2 options2 = new Options2();
    public boolean createConfig() {
        if(usenew) {
            options2.createConfig();
            return true;
        }else {
            try {
                return new File(config).createNewFile();
            } catch (IOException e) {
                new LWLogging().critical(new Language().get("liveweather.options.cantcreate"));
                return false;
            }
        }
    }
    public boolean betterWriteConfig(String name, String value) {
        if(usenew) {
            options2.write(name,value);
            return true;
        }else {
            String cache = "";
            boolean replace = false;
            String mark = "";
            try {
                File myObj = new File(config);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.contains(name)) {
                        if (data.contains("//")) {
                            mark = data.split("//")[1];
                            data = data.split("//")[0].split(":")[0] + ":" + value;
                            replace = true;
                        } else {
                            data = data.split(":")[0] + ":" + value;
                            replace = true;
                        }
                    }
                    if (cache.equals("")) {
                        cache = data;
                    } else {
                        cache = data + "\n" + cache;
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                return false;
            }
            if (!replace) {
                cache = cache + "\n" + name + ":" + value;
            }
            try {
                FileWriter myWriter = new FileWriter(config);
                myWriter.write(cache);
                myWriter.close();
            } catch (IOException e) {
                return false;
            }
            boolean ret = false;
            try {
                File myObj = new File(config);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.contains(name)) {
                        ret = true;
                        if (data.contains(value)) {
                            ret = true;
                            break;
                        } else {
                            ret = false;
                            break;
                        }
                    } else {
                        ret = false;
                        break;
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                return false;
            }
            return ret;
        }
    }
    @Deprecated
    public boolean writeConfig(String name, String value) {
        if (usenew) {
            options2.write(name,value);
            return true;
        } else {
            String towrite = "";
            String old = "";
            try {
                File myObjs = new File(config);
                Scanner myReaders = new Scanner(myObjs);
                while (myReaders.hasNextLine()) {
                    String data = myReaders.nextLine();
                    if (old.contains("")) {
                        old = data;
                    } else {
                        old = old + "\n" + data;
                    }
                    if (!towrite.contains(name)) {
                        if (towrite.equals("")) {
                            towrite = data;
                        } else {
                            towrite = towrite + "\n" + data;
                        }
                    }
                }
                myReaders.close();
            } catch (FileNotFoundException es) {
                return false;
            }
            try {
                FileWriter myWriter = new FileWriter(config);
                myWriter.write(towrite + "\n" + name + ":" + value);
                myWriter.close();
            } catch (IOException e) {
                return false;
            }
            boolean suc = false;
            String reader = "";
            try {
                File myObj = new File(config);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.contains("")) {
                        reader = data;
                    } else {
                        reader = reader + "\n" + data;
                    }
                    if (data.contains(name)) {
                        suc = true;
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                return false;
            }
            return suc;
        }
    }
        public String getConfig(String name) {
        if(usenew) {
            return options2.read(name);
        }
            String toret = "";
            try {
                File myObj = new File(config);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String check = data.split(":")[0];
                    if (check.equals(name)) {
                        if (!data.contains("//")) {
                            toret = data.replace(name + ":", "");
                            break;
                        } else {
                            toret = data.split("//")[0].replace(name + ":", "");
                            break;
                        }
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                new LWLogging().critical(new Language().get("liveweather.options.cantread"));
            }
            return toret;
        }
    }
