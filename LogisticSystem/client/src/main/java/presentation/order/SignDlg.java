/*
 * Created by JFormDesigner on Tue Dec 08 19:20:13 CST 2015
 */

package presentation.order;

import java.awt.event.*;

import data.enums.POType;
import data.factory.DataServiceFactory;
import data.message.ResultMessage;
import data.po.OrderPO;
import data.service.DataService;
import data.vo.SignVO;

import java.awt.*;
import java.rmi.RemoteException;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author mist
 */
public class SignDlg extends JDialog {
    SignVO signVO = null;
    long sn = 0;
    ResultMessage result = ResultMessage.FAILED;

    /**
     * ��ʾ�Ի��򣬲�������������
     * @param sn ��Ҫǩ�յĶ�����
     * @return SUCCESS��ʾǩ�ճɹ������ҳɹ�������ǩ�յ���NOTEXIST��ʾҪǩ�յĶ��������ڣ�FAILED��ʾ�����Ѿ���ǩ�գ����߶�����û����Ч
     */
    public SignVO signOrder(long sn) {
        result = ResultMessage.FAILED;
        this.sn = sn;
        DataService ds = DataServiceFactory.getDataServiceByPO(POType.ORDER);
        if (ds == null) {
            JOptionPane.showMessageDialog(null, "��������ʧ�ܣ��޷����ǩ�ղ���");
        }
        OrderPO order = null;
        try {
            order = (OrderPO) ds.search(POType.ORDER, sn);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        labelOrderNum.setText("�����ţ�" + sn);
        labelRname.setText("�ռ���������" + order.getRname());
        labelRphone.setText("�ռ��˵绰��" + order.getRphone());
        this.setVisible(true);
        return signVO;
    }

    public SignDlg(Frame owner) {
        super(owner);
        initComponents();
    }

    public SignDlg(Dialog owner) {
        super(owner);
        initComponents();
    }


    /**
     * ȷ�ϰ�ťMouseRelease�¼�
     * @param e
     */
    private void button1MouseReleased(MouseEvent e) {
        if (textName.getText().matches("[ ]*")) {
            textName.requestFocus();
            return;
        }
        if (textPhone.getText().matches("[ ]*")) {
            textPhone.requestFocus();
            return;
        }
        if (!textPhone.getText().matches("[+]?[0-9]*")) {
            JOptionPane.showMessageDialog(this, "�绰��ʽ����");
            textPhone.requestFocus();
            return;
        }
        signVO = new SignVO(sn);
        signVO.sname = textName.getText();
        signVO.sphone = textPhone.getText();
        int result = JOptionPane.showConfirmDialog(this, "ȷ������������Ϣǩ�գ�\n" +
                "�����ţ�" + sn + "\n" +
                "ǩ���ߣ�" + signVO.sname + "\n" +
                "�绰��" + signVO.sphone + "\n", "ǩ��ȷ��", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            this.setVisible(false);
        }
        this.setVisible(false);
    }

    private void btCancelMouseReleased(MouseEvent e) {
        signVO = null;
        this.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        labelOrderNum = new JLabel();
        textName = new JTextField();
        label2 = new JLabel();
        textPhone = new JTextField();
        label3 = new JLabel();
        btOK = new JButton();
        btCancel = new JButton();
        labelRname = new JLabel();
        labelRphone = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //---- labelOrderNum ----
        labelOrderNum.setText("\u8ba2\u5355\u53f7\uff1a");
        labelOrderNum.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

        //---- textName ----
        textName.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

        //---- label2 ----
        label2.setText("\u59d3\u540d\uff1a");
        label2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

        //---- textPhone ----
        textPhone.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

        //---- label3 ----
        label3.setText("\u7535\u8bdd\uff1a");
        label3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

        //---- btOK ----
        btOK.setText("\u786e\u5b9a");
        btOK.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
        btOK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                button1MouseReleased(e);
            }
        });

        //---- btCancel ----
        btCancel.setText("\u53d6\u6d88");
        btCancel.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
        btCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                btCancelMouseReleased(e);
            }
        });

        //---- labelRname ----
        labelRname.setText("\u6536\u4ef6\u4eba\u59d3\u540d\uff1a");
        labelRname.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

        //---- labelRphone ----
        labelRphone.setText("\u6536\u4ef6\u4eba\u7535\u8bdd\uff1a");
        labelRphone.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label2)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textName, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label3))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(73, 73, 73)
                                    .addComponent(btOK)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(btCancel)
                                    .addGap(0, 84, Short.MAX_VALUE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(textPhone, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addContainerGap())))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(labelOrderNum)
                                .addComponent(labelRname))
                            .addGap(109, 109, 109)
                            .addComponent(labelRphone)
                            .addContainerGap(106, Short.MAX_VALUE))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(labelOrderNum)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelRname)
                        .addComponent(labelRphone))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textName)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btOK)
                        .addComponent(btCancel))
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        this.setModal(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel labelOrderNum;
    private JTextField textName;
    private JLabel label2;
    private JTextField textPhone;
    private JLabel label3;
    private JButton btOK;
    private JButton btCancel;
    private JLabel labelRname;
    private JLabel labelRphone;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
