package trmega.pcpartpicker.gui;

import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame {

	public Frame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Database");
		this.pack();
		this.setSize(750 + Toolkit.getDefaultToolkit().getScreenSize().width / 4, 400 + Toolkit.getDefaultToolkit().getScreenSize().height / 4);
		this.setResizable(false);
		
		Gui.gui = new Gui(this);
		this.add(Gui.gui);
	}

}
