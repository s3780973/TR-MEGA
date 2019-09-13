package trmega.pcpartpicker.database.stock;

import java.io.File;

import trmega.pcpartpicker.database.Database;

public class DatabaseCaseCooling extends Database {
	
	public DatabaseCaseCooling(File file) {
		super(file);
	}

	/**
	 * Write a line to the case_cooling.csv file
	 */
	public void writeToCSV(String productCode, String productName, String sound, int rpm, int powerRequired, double price, String warranty) {
		
		this.primaryKey = productCode;
		
		this.deleteFromCSV(primaryKey);
		
		this.params = new String[] {
				productCode,
				productName, 
				sound, 
				Integer.toString(rpm), 
				Integer.toString(powerRequired),
				Double.toString(price),
				warranty
				};
		
		super.write();
	}

}
