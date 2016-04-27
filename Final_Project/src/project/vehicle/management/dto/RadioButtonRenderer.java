package project.vehicle.management.dto;
/**
 * 
 * @author Sana
 *
 */

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RadioButtonRenderer extends AbstractCellEditor implements TableCellRenderer, ActionListener {

    private JRadioButton radioButton;

    public RadioButtonRenderer() {
        this.radioButton = new JRadioButton();
        radioButton.addActionListener(this);
        radioButton.setOpaque(false);
    }
    

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        radioButton.setSelected(Boolean.TRUE.equals(value));
        return radioButton;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    	this.shouldSelectCell(e);
    //	stopCellEditing();
    }

    @Override
    public Object getCellEditorValue() {
        return radioButton.isSelected();
    }

}
