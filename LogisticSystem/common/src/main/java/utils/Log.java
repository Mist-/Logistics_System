package utils;

import javax.swing.*;
import java.util.Calendar;

/**
 * Created by mist on 2015/12/30 0030.
 */
public class Log {
    static Log instance = null;
    JTextArea console;

    public static Log getInstance() {
        if (instance == null) instance = new Log();
        return instance;
    }

    public static void register(JTextArea console) {
        getInstance().setConsole(console);
    }

    public static void log(String info) {
        getInstance().getConsole().setText(info + " - " + Calendar.getInstance().getTime() + "\n" + getInstance().getConsole().getText());

    }

    JTextArea getConsole() {
        return console;
    }

    void setConsole(JTextArea console) {
        this.console = console;
    }
}
