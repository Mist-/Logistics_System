package presentation.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by wyc on 2015/12/24.
 */
public class DialogExit extends JDialog {
    //������Ҫ�����
    JDialog jDialog = null;
    JPanel jPanel = null;
    JButton buttonEnsure = null;
    JButton buttonExit = null;
    JLabel jLabel = null;

    public DialogExit(final JFrame jFrame){
        jDialog = new JDialog(jFrame,"�˳�ϵͳ");
        jPanel = new JPanel();
        buttonEnsure = new JButton("ȷ��");
        buttonExit = new JButton("ȡ��");
        jLabel = new JLabel("��ȷ�������޸��ѱ���!",JLabel.CENTER);
        jLabel.setBounds(0,10,300,40);
        buttonEnsure.setBounds(60,80,55,35);
        buttonExit.setBounds(160,80,55,35);
        //���������jDialog
        jDialog.setSize(300,200);
        jPanel.setLayout(null);
        jDialog.add(jPanel, BorderLayout.CENTER);
        jPanel.add(jLabel);
        jPanel.add(buttonEnsure);
        jPanel.add(buttonExit);
        jDialog.setModal(true);
        jDialog.setResizable(false);
        jDialog.setLocationRelativeTo(null);
        buttonEnsure.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                jFrame.dispose();
            }
        });
        buttonExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                jDialog.dispose();
            }
        });
        jDialog.setVisible(true);
    }
}
