package com.liveweather.config;

import com.liveweather.PublicValues;
import com.liveweather.logging.LWLogging;
import com.liveweather.utils.IOUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;

@SuppressWarnings("unused")
public class Config {
    private final Yaml yaml;
    private LinkedHashMap<String, LinkedHashMap<String, Object>> config;

    public Config() throws IOException, IllegalStateException {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        yaml = new Yaml(dumperOptions);
        config = new LinkedHashMap<>();
        if(!PublicValues.configPath.exists()) {
            if(!PublicValues.configPath.createNewFile()) throw new RuntimeException("Couldn't create config file");
            writeDefault();
            save();
            writeComments();
        }
        config = yaml.load(new FileReader(PublicValues.configPath));
        checkConfig();
    }

    private void writeDefault() throws IllegalStateException {
        LinkedHashMap<String, LinkedHashMap<String, Object>> sections = new LinkedHashMap<>();
        ConfigValues[] reversed = ConfigValues.values();
        Collections.reverse(Arrays.asList(reversed));
        for (ConfigValues values : reversed) {
            String[] parts = values.name.split("\\.", 2);
            if (parts.length < 2) {
                throw new IllegalStateException("Invalid config name: " + values.name);
            }
            String section = parts[0];
            String key = parts[1];
            if(!sections.containsKey(section)) sections.put(section, new LinkedHashMap<>());
            sections.get(section).put(key, values.defaultValue);
        }
        config.putAll(sections);
    }

    private void save() throws IOException {
        FileWriter writer = new FileWriter(PublicValues.configPath);
        yaml.dump(config, writer);
        writer.close();
    }

    public void checkConfig() throws IOException {
        boolean foundInvalid = config == null;
        for (ConfigValues value : ConfigValues.values()) {
            if(config == null) {
                config = new LinkedHashMap<>();
                break;
            }
            if(config.get(value.name.split("\\.")[0]) == null) {
                LWLogging.warning("Section '" + value.name.split("\\.")[0] + "' doesn't exist! Creating...");
                foundInvalid = true;
                break;
            }
            if (!config.get(value.name.split("\\.")[0]).containsKey(value.name.split("\\.")[1])) {
                LWLogging.warning("Key '" + value.name + "' doesn't exist! Creating...");
                foundInvalid = true;
                break;
            }
        }
        for(String line : IOUtils.toString(Files.newInputStream(PublicValues.configPath.toPath()), StandardCharsets.UTF_8).split("\n")) {
            if(line.trim().startsWith("#")) {
                for(ConfigValues value: ConfigValues.values()) {
                    if(line.replace("  #", "").equals(value.comment)) {
                        foundInvalid = true;
                        break;
                    }
                }
            }
        }
        if (foundInvalid) {
            LinkedHashMap<String, LinkedHashMap<String, Object>> sections = new LinkedHashMap<>(config);
            if(!PublicValues.configPath.delete()) throw new IOException("Couldn't delete config file");
            if(!PublicValues.configPath.createNewFile()) throw new IOException("Couldn't create config file");
            config = new LinkedHashMap<>();
            writeDefault();
            for(String section : sections.keySet()) {
                for(String key : sections.get(section).keySet()) {
                    config.get(section).put(key, sections.get(section).get(key));
                }
            }
            save();
            writeComments();
        }
    }

    private void writeComments() throws IOException {
        ArrayList<String> newLines = new ArrayList<>();
        newLines.add("# DO NOT CHANGE THE LAYOUT OF THIS FILE");
        String config = IOUtils.toString(Files.newInputStream(PublicValues.configPath.toPath()), StandardCharsets.UTF_8);
        String[] lines = config.split("\n");
        ArrayList<ConfigValues> reversedValues = new ArrayList<>();
        Collections.addAll(reversedValues, ConfigValues.values());
        Collections.reverse(reversedValues);
        int configValue = 0;
        for (String line : lines) {
            if(line.trim().startsWith("#")) continue;
            if (!line.trim().endsWith(":")) {
                ConfigValues value = reversedValues.get(configValue);
                newLines.add("  # " + value.comment);
                newLines.add(line);
                configValue++;
            }else{
                newLines.add(line);
            }
        }
        Files.write(PublicValues.configPath.toPath(), String.join("\n", newLines).getBytes(StandardCharsets.UTF_8));
    }

    public Object get(String key) {
        try {
            return config.get(key.split("\\.")[0]).get(key.split("\\.")[1]);
        }catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public String getString(String name) {
        String ret = (String) get(name);
        if (ret == null) {
            ret = "";
        }
        return ret;
    }

    public Boolean getBoolean(String name) {
        return (Boolean) get(name);
    }

    public int getInt(String name) {
        return (int) get(name);
    }

    public Double getDouble(String name) {
        return (Double) get(name);
    }
}
