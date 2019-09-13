package trmega.pcpartpicker.database.userdata;

import java.io.File;

import trmega.pcpartpicker.database.Database;

public class DatabaseUserTransactions extends Database {
	
	public DatabaseUserTransactions(File file) {
		super(file);
	}

	/**
	 * Write a line to the user_transactions.csv file
	 */
	public void writeToCSV(String username, String pastTransactions) { //TODO create array of past transactions
		
		this.primaryKey = username;
		
		this.deleteFromCSV(primaryKey);
		
		this.params = new String[] {
				username,
				pastTransactions //TODO use array
				};
		
		super.write();
	}

}
