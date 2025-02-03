package com.liveweather.config;

import com.liveweather.PublicValues;
import com.liveweather.logging.LWLogging;
import com.liveweather.utils.IOUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Config {
    private final cn.nukkit.utils.Config config;

    public Config() throws IOException, IllegalStateException {
        boolean didntExist = false;
        if(!PublicValues.configPath.exists()) {
            didntExist = true;
            if(!PublicValues.configPath.createNewFile()) throw new RuntimeException("Couldn't create config file");
        }
        config = new cn.nukkit.utils.Config(PublicValues.configPath, cn.nukkit.utils.Config.YAML);
        if(didntExist) writeDefault();
        checkConfig();
    }

    @SuppressWarnings("unchecked")
    private void writeDefault() throws IllegalStateException, IOException {
        LinkedHashMap<String, Object> sections = new LinkedHashMap<>();
        for(ConfigValues values: ConfigValues.values()) {
            if(!sections.containsKey(values.name.split("\\.")[0])) sections.put(values.name.split("\\.")[0], new LinkedHashMap<String, Object>());
            if(!(sections.get(values.name.split("\\.")[0]) instanceof LinkedHashMap)) {
                throw new IllegalStateException("Section value not instance of LinkedHashMap");
            }
            ((LinkedHashMap<String, Object>)sections.get(values.name.split("\\.")[0])).put(values.name.split("\\.")[1], values.defaultValue);
        }
        config.setAll(sections);
        config.save();
        writeComments();
    }

    public void checkConfig() throws IOException {
        boolean foundInvalid = false;
        for (ConfigValues value : ConfigValues.values()) {
            if (!config.exists(value.name)) {
                LWLogging.warning("Key '" + value.name + "' doesn't exist! Creating...");
                foundInvalid = true;
            }
        }
        for(String line : IOUtils.toString(Files.newInputStream(PublicValues.configPath.toPath()), StandardCharsets.UTF_8).split("\n")) {
            if(line.startsWith("  #")) {
                boolean foundComment = false;
                for(ConfigValues value: ConfigValues.values()) {
                    if(line.replace("  #", "").equals(value.comment)) {
                        foundComment = true;
                        break;
                    }
                }
                if(!foundComment) {
                    foundInvalid = true;
                }
            }
        }
        if (foundInvalid) {
            ArrayList<Object[]> configValues = new ArrayList<>();
            for(ConfigValues values: ConfigValues.values()) {
                if(config.exists(values.name)) {
                    configValues.add(new Object[] {values.name, config.get(values.name)});
                }
            }
            if(!PublicValues.configPath.delete()) throw new IOException("Couldn't delete config file");
            if(!PublicValues.configPath.createNewFile()) throw new IOException("Couldn't create config file");
            writeDefault();
            for(Object[] configValue: configValues) {
                config.set((String) configValue[0], configValue[1]);
            }
            config.save();
            writeComments();
        }
    }

    private void writeComments() throws IOException {
        StringBuilder newConfig = new StringBuilder();
        newConfig.append("# DO NOT CHANGE THE LAYOUT OF THIS FILE").append("\n");
        String config = IOUtils.toString(Files.newInputStream(PublicValues.configPath.toPath()), StandardCharsets.UTF_8);
        for(String line : config.split("\n")) {
            if(!line.startsWith(" ")) newConfig.append(line).append("\n");
            for(ConfigValues values: ConfigValues.values()) {
                if(line.contains(values.name.split("\\.")[1] + ":")) {
                    newConfig.append("  ").append("#").append(values.comment).append("\n").append(line).append("\n");
                }
            }
        }
        Files.write(PublicValues.configPath.toPath(), newConfig.toString().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Writes a new entry with the name and value to the config file
     *
     * @param name  Name of entry
     * @param value Value of entry
     */
    public void write(String name, String value) {
        config.set(name, value);
        config.save();
    }

    public String getString(String name) {
        String ret = config.getString((name));
        if (ret == null) {
            ret = "";
        }
        return ret;
    }

    public Boolean getBoolean(String name) {
        return config.getBoolean(name);
    }

    public int getInt(String name) {
        return config.getInt(name);
    }

    public Double getDouble(String name) {
        return config.getDouble(name);
    }
}
