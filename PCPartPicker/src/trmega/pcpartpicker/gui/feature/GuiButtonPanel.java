package trmega.pcpartpicker.gui.feature;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import trmega.pcpartpicker.database.Database;
import trmega.pcpartpicker.gui.Gui;

@SuppressWarnings("serial")
public class GuiButtonPanel extends JPanel {
	
	public GuiButtonPanel(Gui gui) {	
		for(int i = 0; i < Database.databases.length; i++) {
			this.createButton(i);
		}
		
        this.setLayout(new GridLayout(Database.databases.length, 0)); //rows, coloumns
        
        this.setPreferredSize(new Dimension(Gui.BLOCK * 13, 0)); //x, y
	}
	
	private JButton createButton(final int index) {
		JButton button = new JButton(Database.databases[index].getName());

		button.setFocusable(false);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gui.getGui().switchDatabase(GuiDatabase.tables[index]);
				Gui.getGui().menuBar.setTableName(GuiDatabase.tables[index]);
			}
		});
		
		this.add(button);
		return button;
	}

}
