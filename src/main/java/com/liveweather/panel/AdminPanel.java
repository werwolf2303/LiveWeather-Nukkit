package com.liveweather.panel;

import cn.nukkit.Player;
import com.liveweather.Initiator;
import com.liveweather.commandline.LWLogging;
import com.liveweather.formapi.forms.custom.LoginForm;
import com.liveweather.formapi.forms.elements.CustomForm;
import com.liveweather.formapi.forms.elements.ModalForm;
import com.liveweather.formapi.forms.handlers.LoginFormHandler;
import com.liveweather.instances.InstanceManager;
import com.liveweather.utils.FileUtils;
import com.liveweather.utils.Password;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.Optional;
import java.util.Random;

public class AdminPanel {
    public boolean openLogin(Player p, boolean skip) {
        if(skip) {
            return true;
        }
        LoginForm form = new LoginForm("Login Form");
        form.open(p);
        boolean ret = false;
        form.setHandler(new LoginFormHandler() {
            @Override
            public void onOk(String email, String password, Player p) {

            }

            @Override
            public void onOk(String password, Player p) {

            }

            @Override
            public void onCancel() {

            }
        });
        return ret;
    }
    public void setup(Player p) {
        CustomForm form = new CustomForm("AdminPanel Setup");
        form.addInput("Password");
        form.send(p, (targetPlayer, targetForm, data) -> {
            if(data == null) return;
            String c1 = data.toString().replaceFirst("\\[", "");
            String c2 = StringUtils.reverse(c1).replaceFirst("\\]", "");
            String pass = StringUtils.reverse(c2);
            File pw = new File(Initiator.pluginlocation + "/pwd.txt");
            try {
                FileWriter w = new FileWriter(pw);
                w.write(new Password(new Random().nextInt(100)).hash(pass));
                w.close();
            } catch (IOException e) {
                new LWLogging().error("Failed to write password");
            }

        });
    }
    public void open(Player p) {
        boolean ignore = false;
        if(!new File(Initiator.pluginlocation + "/pwd.txt").exists()) {
            setup(p);
            ignore = true;
        }
        if(openLogin(p, ignore)) {
            openPanel(p);
        }else{
            ModalForm form = InstanceManager.getModalForm("Reported", "Access denied! You have been reported to admins.\n\nPlayer Name:" + p.getName() + "\nPlayer Address: " + p.getAddress(), "Ok", "Cancel");
            form.send(p, (targetPlayer, targetForm, data) -> {
                if(data == -1) return;
            });
        }
    }
    public void openPanel(Player p) {
        CustomForm form = new CustomForm("AdminPanel");
        form.addInput("");
        form.addToggle("ConfigServer");
        form.send(p);
    }
}
