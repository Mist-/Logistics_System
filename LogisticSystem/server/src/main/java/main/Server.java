package main;

import org.jb2011.lnf.windows2.Windows2LookAndFeel;
import presentation.MonitorUI;

import javax.swing.*;
import java.io.*;

/**
 *
 * Created by mist on 2015/12/13 0013.
 */
public class Server {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new Windows2LookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        MonitorUI monitorUI = new MonitorUI();
        monitorUI.setVisible(true);
    }
}

