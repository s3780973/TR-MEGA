package trmega.pcpartpicker.gui.feature;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import trmega.pcpartpicker.database.Database;
import trmega.pcpartpicker.gui.Gui;

@SuppressWarnings("serial")
public class GuiButton extends JPanel {
	
	private Gui gui;
	
	public GuiButton(Gui gui) {
		this.gui = gui;
		
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
				gui.switchDatabase(GuiDatabase.guiDatabases[index]);
				gui.menuBar.setTableName(GuiDatabase.guiDatabases[index]);
			}
		});
		this.add(button);
		return button;
	}

}
