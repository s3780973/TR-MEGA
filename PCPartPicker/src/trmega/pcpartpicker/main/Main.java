package trmega.pcpartpicker.main;

import javax.swing.SwingUtilities;

import trmega.pcpartpicker.gui.Frame;

public class Main {

	public static void main(String[] args) {
		
		//TODO clean up and fix certain databases (user_transactions, order_details etc)
		
		// Example:
		
		//Database.wipeAll();
		//TODO delete row by selecting a row
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {				
				new Frame();		
			}		
		});
		
	}

}
