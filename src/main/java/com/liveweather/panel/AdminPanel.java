package com.liveweather.panel;

import cn.nukkit.Player;
import com.liveweather.commandline.LWLogging;
import com.liveweather.formapi.forms.elements.CustomForm;
import com.liveweather.formapi.forms.elements.LoginForm;
import com.liveweather.formapi.forms.elements.ModalForm;
import com.liveweather.formapi.forms.handlers.ClickedButton;
import com.liveweather.instances.InstanceManager;
import com.liveweather.server.Utils;
import com.liveweather.utils.FileUtils;

import java.io.File;
import java.lang.invoke.SerializedLambda;

public class AdminPanel {
    private class handling implements ClickedButton {

        @Override
        public void onOk() {
            new LWLogging().normal("Ok");
        }

        @Override
        public void onCancel() {
            new LWLogging().normal("Cancel");
        }
    }
    public void setup(Player p) {
        CustomForm form = new CustomForm("AdminPanel Setup").addLabel("Enter Password").addInput("Password");
        form.send(p, (targetPlayer, targetForm, data) -> {
            if(data == null) return;

        });
    }
    public void open(Player p) {
        LoginForm login = new LoginForm("Test Login");
        login.setCancelText("Cancel");
        login.setOkText("Ok");
        login.setHandler(new handling());
        login.open(p);
    }
    public void oldopen(Player p) {
        if(!new File(InstanceManager.getServer().getPluginPath().replace("\\", "/")+"/LiveWeather/pass.txt").exists()) {
            if(p.isOp()) {
                setup(p);
            }else{
                ModalForm form = new ModalForm("Restricted", "Reported to admin\n\nAdditional Info: \n\nPlayer Name: " + p.getName() + "\nPlayer Address: " + p.getAddress(), "Ok");
                form.send(p, (targetPlayer, targetForm, data) -> {
                    if (data == -1) return;
                });
            }
        }
        if (p.isOp()) {
            //Is Operator proceed

        } else {
            if(openLoginForm(p)) {
                //Password right proceed

            }else{
                ModalForm form = new ModalForm("Login Failed", "Login failed.\n\nReported to admin\n\nAdditional Info: \n\nPlayer Name: " + p.getName() + "\nPlayer Address: " + p.getAddress(), "Ok");
                form.send(p, (targetPlayer, targetForm, data) -> {
                    if (data == -1) return;
                });
            }
        }
    }
    public boolean openLoginForm(Player p) {
        String pass = new FileUtils().getContent(InstanceManager.getServer().getPluginPath().replace("\\", "/")+"/LiveWeather/pass.txt");
        System.out.println("Password: " + pass);
        return true;
    }
}
