package com.liveweather.simulator;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.storage.LWConfig;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecCommand extends Command {
    public ExecCommand(String name) {
        super(name);
    }

    @SuppressWarnings({"StringConcatenationInLoop", "ConstantValue"})
    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        try {
            String command = strings[0];
            String args = "";
            if (strings.length > 0) {
                int count = 0;
                for (String st : strings) {
                    if (count == 1) {
                        if (args.equals("")) {
                            args = st;
                        } else {
                            args = args + " " + st;
                        }
                    } else {
                        count++;
                    }
                }
            }
            if (command.equals("getyaml")) {
                System.out.println("YAML => {");
                BufferedReader reader;
                try {
                    reader = new BufferedReader(new FileReader(
                            LWConfig.config));
                    String line = reader.readLine();
                    while (line != null) {
                        System.out.println("Line: " + line);
                        line = reader.readLine();
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("}");
            }
        }catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("[EXEC] No command supplied");
        }
        return false;
    }
}
