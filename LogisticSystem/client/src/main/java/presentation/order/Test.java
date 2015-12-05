package presentation.order;

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
        NewOrderDlg newOrderDlg = new NewOrderDlg(new Frame());
        newOrderDlg.setVisible(true);
    }
}
