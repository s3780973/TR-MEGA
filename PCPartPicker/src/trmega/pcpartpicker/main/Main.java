package trmega.pcpartpicker.main;

import javax.swing.SwingUtilities;

import trmega.pcpartpicker.gui.Frame;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {				
				new Frame();
			}		
		});
		
	}

}
