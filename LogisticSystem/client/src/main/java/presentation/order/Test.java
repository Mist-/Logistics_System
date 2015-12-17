package presentation.order;

import data.message.LoginMessage;
import data.message.ResultMessage;
import org.jb2011.lnf.windows2.Windows2LookAndFeel;

import javax.swing.*;
import java.awt.*;

/**
 *
 * Created by mist on 2015/12/5 0005.
 */
public class Test extends JFrame {

    public Test() {
        super();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new Windows2LookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        NewOrderDlg newOrderDlg = new NewOrderDlg(new Frame(), new LoginMessage(ResultMessage.SUCCESS, 10000));
        newOrderDlg.setVisible(true);
    }
}
