package trmega.pcpartpicker.gui.feature;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import trmega.pcpartpicker.database.Database;
import trmega.pcpartpicker.gui.Gui;

public class GuiNavigationBar extends JPanel {
	
	public GuiNavigationBar() {
		for(int i = 0; i < Database.databases.length; i++) {
        	JButton button = new JButton(Database.databases[i].getClass().getSimpleName().substring(8));
        	this.add(button);
        }
		
        this.setLayout(new GridLayout(Database.databases.length, 0)); //rows, coloumns
        
        this.setPreferredSize(new Dimension(Gui.BLOCK * 18, 0)); //x, y
	}

}
