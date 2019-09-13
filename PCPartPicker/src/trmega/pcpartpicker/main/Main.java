package trmega.pcpartpicker.main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import trmega.pcpartpicker.database.Database;
import trmega.pcpartpicker.gui.Frame;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {				
				new Frame();		
			}		
		});
		
		//TODO clean up and fix certain databases (user_transactions, order_details etc)
		
		// Write data inputs or deletes here
		
		// Example:
		Database.USER.writeToCSV("User 1", "example@email.com", false);
		Database.USER.writeToCSV("User 2", "example@email.com", true);
		Database.CPU.writeToCSV("r", "", "", 0, 0, 0, 0, "", 0, "");
		Database.CPU.writeToCSV("t", "", "", 0, 0, 0, 0, "", 0, "");
		
		Database.wipeAll();
		
		//TODO ADDING LINE CALLS DELETE METHOD
		
	}

}
