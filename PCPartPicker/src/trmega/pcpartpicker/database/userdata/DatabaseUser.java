package trmega.pcpartpicker.database.userdata;

import java.io.File;

import trmega.pcpartpicker.database.Database;

public class DatabaseUser extends Database {
	
	public DatabaseUser(File file) {
		super(file);
	}

	/**
	 * Write a line to the user.csv file
	 */
	public void writeToCSV(String username, String email, boolean newsletterSub) {
		
		this.primaryKey = username;
		
		this.deleteFromCSV(primaryKey);
		
		this.params = new String[] {
				username,
				email, 
				Boolean.toString(newsletterSub),
				};
		
		super.write();
	}

}
