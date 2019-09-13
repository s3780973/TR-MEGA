package trmega.pcpartpicker.gui;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class Frame extends JFrame {
	
	public Frame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Database");
		this.pack();
		this.setSize(550 + Toolkit.getDefaultToolkit().getScreenSize().width / 4, 400 + Toolkit.getDefaultToolkit().getScreenSize().height / 4);
		this.setResizable(false);
		
		new JFrame();
		
		Gui gui = new Gui(this);
		this.add(gui);
	}

}
