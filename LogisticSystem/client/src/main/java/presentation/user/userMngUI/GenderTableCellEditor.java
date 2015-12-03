package presentation.user.userMngUI;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

/**
 *
 * Created by Mouse on 2015/10/30 0030.
 */

public class GenderTableCellEditor extends JComboBox implements TableCellEditor{


    private EventListenerList listenerList = new EventListenerList();

    private ChangeEvent changeEvent = new ChangeEvent(this);


    public GenderTableCellEditor() {
        super();
        addItem("ÄÐ");
        addItem("Å®");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        boolean isMale = value.equals("ÄÐ");
        setSelectedIndex(isMale ? 0 : 1);
        return this;
    }

    @Override
    public Object getCellEditorValue() {
        return getSelectedItem();
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        return true;
    }

    @Override
    public void cancelCellEditing() {

    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        listenerList.add(CellEditorListener.class, l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        listenerList.remove(CellEditorListener.class, l);
    }

    private void fireEditingStopped() {
        CellEditorListener listener;
        Object[] listeners = listenerList.getListenerList();
        for(int i = 0; i < listeners.length; i++){
            if(listeners[i] == CellEditorListener.class){
                listener = (CellEditorListener)listeners[i + 1];
                listener.editingStopped(changeEvent);
            }
        }
    }
}
