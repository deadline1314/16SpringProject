package project.vehicle.management.dto;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;

public class RadioButtonEditor extends DefaultCellEditor implements ItemListener {

	private JRadioButton button;
	private JRadioButton jb;

	public RadioButtonEditor(JCheckBox checkBox) {
		super(checkBox);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (value == null)
			return null;
		if (value instanceof JRadioButton) {
			button = (JRadioButton) value;
			button.addItemListener(this);
		}
		return button;
	}

	public Object getCellEditorValue() {
		if (button instanceof JRadioButton)
			button.removeItemListener(this);
		return button;

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		jb = (JRadioButton)this.getCellEditorValue();
	}

	public JRadioButton getRB(JRadioButton jb){
		this.jb = jb;
		return jb;
	}

}
