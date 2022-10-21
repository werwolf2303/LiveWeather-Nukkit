package com.liveweather.panel;

import cn.nukkit.Player;
import com.liveweather.commandline.LWLogging;
import com.liveweather.formapi.forms.elements.CustomForm;
import com.liveweather.formapi.forms.elements.ModalForm;
import com.liveweather.instances.InstanceManager;
import com.liveweather.server.Utils;
import com.liveweather.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.invoke.SerializedLambda;

public class AdminPanel {
    public void setup(Player p) {
        CustomForm form = new CustomForm("AdminPanel Setup").addLabel("Enter Password").addInput("Password");
        form.send(p, (targetPlayer, targetForm, data) -> {
            if(data == null) return;
            String c1 = data.toString().split("\\[")[1];
            String c2 = StringUtils.reverse(c1).replaceFirst( "\\]", "");
            String password = StringUtils.reverse(c2);
            new LWLogging().normal("Entered Password: " + password);
        });
    }
    public void open(Player p) {
        if(!new File(InstanceManager.getServer().getPluginPath().replace("\\", "/")+"/LiveWeather/pass.txt").exists()) {
            if(p.isOp()) {
                new LWLogging().normal("Enter setup");
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
