package trmega.pcpartpicker.gui.feature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import trmega.pcpartpicker.database.Database;
import trmega.pcpartpicker.gui.Gui;

public class GuiBottomPanel extends JPanel {
	
	private boolean rowSelected = false;
	
	private JButton addRow = new JButton("Add Row");
	private JButton deleteRow = new JButton("Delete Row");
	private JButton moveUp = new JButton("Move Up");
	private JButton moveDown = new JButton("Move Down");
	
	public GuiBottomPanel() {
		
        addRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Gui.CURRENT_DATABASE.addRow();
			}
        	
        });
        
        deleteRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Gui.CURRENT_DATABASE.deleteRow();
			}
        	
        });
        
        moveUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Gui.CURRENT_DATABASE.moveRowUp();
			}
        	
        });
        
        moveDown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Gui.CURRENT_DATABASE.moveRowDown();	
			}
        	
        });
        
        this.add(addRow);
        this.add(deleteRow);
        this.add(moveUp);
        this.add(moveDown);
	}
	
	public void update() {
		this.rowSelected = Gui.CURRENT_DATABASE.getTable().getSelectedRow() >= 0 ? true : false;
		
		if(this.rowSelected) {
			this.deleteRow.setEnabled(true);
			this.moveUp.setEnabled(true);
			this.moveDown.setEnabled(true);
		} else {
			this.deleteRow.setEnabled(false);
			this.moveUp.setEnabled(false);
			this.moveDown.setEnabled(false);
		}
	}

}
