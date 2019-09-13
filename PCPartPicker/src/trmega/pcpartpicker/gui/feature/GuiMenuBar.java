package trmega.pcpartpicker.gui.feature;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import trmega.pcpartpicker.database.Database;
import trmega.pcpartpicker.gui.Gui;

public class GuiMenuBar extends JMenuBar {
	
	public GuiMenuBar() {
		JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);
        
        this.add(mb);
        
        //this.setLayout(new GridLayout(Database.databases.length, 0)); //rows, coloumns
        
        this.setPreferredSize(new Dimension(0, Gui.BLOCK * 3)); //x, y
	}

}
