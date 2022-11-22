package com.liveweather.Simulator;

import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.lang.TextContainer;
import cn.nukkit.network.SourceInterface;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.permission.Permission;
import cn.nukkit.permission.PermissionAttachment;
import cn.nukkit.permission.PermissionAttachmentInfo;
import cn.nukkit.plugin.Plugin;
import com.liveweather.instances.InstanceManager;
import org.apache.commons.io.LineIterator;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Console extends Player {
    //TODO: Fix Loggin overlapping user input field

    public static ArrayList<Command> com = new ArrayList<Command>();
    boolean first = true;
    public void init() {
        if(first) {
            com.add(new SwitchCommand("switch"));
            InstanceManager.setConsole(this);
            first = false;
        }
        boolean found = false;
        System.out.print("Console > ");
        String input = new Scanner(System.in).nextLine();
        String[] args = new String[] {};
        ArrayList<String> conv = new ArrayList<>();
        for(Command c : com) {
            try {
                //Arguments
                String test = input.trim().split(" ")[0];
                int curr = 0;
                for(String s : input.trim().split(" ")) {
                    if(curr>0) {
                        conv.add(s);
                    }
                    curr++;
                }
                input = input.trim().split(" ")[0];
                args = conv.toArray(new String[0]);
            }catch (ArrayIndexOutOfBoundsException ex) {
                //No args
            }
            if(input.equals("switch")) {
                player();
                found = true;
            }else {
                if (input.equals(c.getName())) {
                    found = true;
                    c.execute(new CommandSender() {
                        @Override
                        public void sendMessage(String s) {
                            System.out.println(s);
                        }

                        @Override
                        public void sendMessage(TextContainer textContainer) {

                        }

                        @Override
                        public Server getServer() {
                            return InstanceManager.getServer();
                        }

                        @Override
                        public String getName() {
                            return "Console";
                        }

                        @Override
                        public boolean isPlayer() {
                            return false;
                        }

                        @Override
                        public boolean isPermissionSet(String s) {
                            return false;
                        }

                        @Override
                        public boolean isPermissionSet(Permission permission) {
                            return false;
                        }

                        @Override
                        public boolean hasPermission(String s) {
                            return true;
                        }

                        @Override
                        public boolean hasPermission(Permission permission) {
                            return true;
                        }

                        @Override
                        public PermissionAttachment addAttachment(Plugin plugin) {
                            return null;
                        }

                        @Override
                        public PermissionAttachment addAttachment(Plugin plugin, String s) {
                            return null;
                        }

                        @Override
                        public PermissionAttachment addAttachment(Plugin plugin, String s, Boolean aBoolean) {
                            return null;
                        }

                        @Override
                        public void removeAttachment(PermissionAttachment permissionAttachment) {

                        }

                        @Override
                        public void recalculatePermissions() {

                        }

                        @Override
                        public Map<String, PermissionAttachmentInfo> getEffectivePermissions() {
                            return null;
                        }

                        @Override
                        public boolean isOp() {
                            return true;
                        }

                        @Override
                        public void setOp(boolean b) {

                        }
                    }, "", args);
                }
            }
        }
        if(!found) {
            System.out.println("Wrong command: '" + input.trim().split(" ")[0] + "' | Type 'help' for info");
        }
        init();
    }

    public void player() {
        boolean stop = false;
        System.out.print("Player 'DebugPlayer' > ");
        String input = new Scanner(System.in).nextLine();
        String[] args = new String[] {};
        ArrayList<String> conv = new ArrayList<>();
        for(Command c : com) {
            try {
                //Arguments
                String test = input.trim().split(" ")[0];
                int curr = 0;
                for (String s : input.trim().split(" ")) {
                    if (curr > 0) {
                        conv.add(s);
                    }
                    curr++;
                }
                input = input.trim().split(" ")[0];
                args = conv.toArray(new String[0]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                //No args
            }
            if(input.equals("switch")) {
                stop=true;
                break;
            }else{
                if (input.equals(c.getName())) {
                    c.execute(new PlayerCommandSender(), "", args);
                }
            }
        }
        if(!stop) {
            player();
        }else{
            init();
        }
    }
}
