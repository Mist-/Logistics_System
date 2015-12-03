package presentation.user.userMngUI;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 *
 * Created by Mouse on 2015/10/30 0030.
 */
public class GenderTableCellRenderer extends JComboBox implements TableCellRenderer {

    public GenderTableCellRenderer() {
        super();
        addItem("ÄÐ");
        addItem("Å®");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getForeground());
            super.setBackground(table.getBackground());
        }
        else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        boolean isMale = value.equals("ÄÐ");
        setSelectedIndex(isMale? 0 : 1);
        return this;
    }
}
