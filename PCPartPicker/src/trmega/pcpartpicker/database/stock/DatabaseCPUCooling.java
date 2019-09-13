package trmega.pcpartpicker.database.stock;

import java.io.File;

import trmega.pcpartpicker.database.Database;

public class DatabaseCPUCooling extends Database {
	
	public DatabaseCPUCooling(File file) {
		super(file);
	}

	/**
	 * Write a line to the cpu_cooling.csv file
	 */
	public void writeToCSV(String productCode, String productName, String type, String sound, int powerRequired, double price, String warranty) {
		
		this.primaryKey = productCode;
		
		this.deleteFromCSV(primaryKey);
		
		this.params = new String[] {
				productCode,
				productName, 
				type, 
				sound, 
				Integer.toString(powerRequired),
				Double.toString(price),
				warranty
				};
		
		super.write();
	}

}
