package trmega.pcpartpicker.gui;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class Frame extends JFrame {
	
	public Frame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Database");
		this.pack();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
		this.setResizable(false);
		
		new JFrame();
		
		Gui screen = new Gui(this);
		this.add(screen);
	}

}
