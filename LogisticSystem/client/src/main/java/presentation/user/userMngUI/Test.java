package presentation.user.userMngUI;

import org.jb2011.lnf.windows2.Windows2LookAndFeel;

import javax.swing.*;

/**
 *
 * Created by mist on 2015/12/3 0003.
 */
public class Test {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new Windows2LookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        UserMngUI userUI = new UserMngUI();
        userUI.setVisible(true);
    }
}
