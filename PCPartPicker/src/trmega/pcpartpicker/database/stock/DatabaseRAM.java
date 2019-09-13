package trmega.pcpartpicker.database.stock;

import java.io.File;

import trmega.pcpartpicker.database.Database;

public class DatabaseRAM extends Database {
	
	public DatabaseRAM(File file) {
		super(file);
	}

	/**
	 * Write a line to the ram.csv file
	 */
	public void writeToCSV(String productCode, String productName, String type, int size, int powerRequired, double price, String warranty) {
		
		this.primaryKey = productCode;
		
		this.deleteFromCSV(primaryKey);
		
		this.params = new String[] {
				productCode,
				productName, 
				type, 
				Integer.toString(size), 
				Integer.toString(powerRequired),
				Double.toString(price),
				warranty
				};
		
		super.write();
	}

}
