package com.liveweather.Simulator;

import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.lang.TextContainer;
import cn.nukkit.permission.Permission;
import cn.nukkit.permission.PermissionAttachment;
import cn.nukkit.permission.PermissionAttachmentInfo;
import cn.nukkit.plugin.Plugin;
import com.liveweather.instances.InstanceManager;

import java.util.Map;

public class PlayerCommandSender extends Player implements CommandSender {
    @Override
    public void sendMessage(String s) {
        new SimulatorLogger().player(s);
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
        return "DebugPlayer";
    }

    @Override
    public boolean isPlayer() {
        return true;
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
}
