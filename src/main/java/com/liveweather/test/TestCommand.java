package com.liveweather.test;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.element.ElementInput;
import com.liveweather.commandline.LWLogging;
import com.liveweather.formapi.forms.custom.LoginForm;
import com.liveweather.formapi.forms.elements.CustomForm;
import com.liveweather.formapi.forms.elements.ImageType;
import com.liveweather.formapi.forms.elements.SimpleForm;
import com.liveweather.formapi.forms.handlers.LoginFormHandler;

public class TestCommand extends Command {
    public TestCommand(String name) {
        super(name);
    }

    private class handler implements LoginFormHandler {

        @Override
        public void onOk(String email, String password, Player p) {
            new LWLogging().normal("Entered Email: " + email);
            new LWLogging().normal("Entered Password: " + password);
        }

        @Override
        public void onOk(String password, Player p) {

        }

        @Override
        public void onCancel() {
            new LWLogging().normal("Cancel");
        }
    }
    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            LoginForm form = new LoginForm("Test Login");
            form.setHasEmail();
            form.setHandler(new handler());
            form.open(p);
        }
        return false;
    }
}
