package trmega.pcpartpicker.database.stock;

import java.io.File;

import trmega.pcpartpicker.database.Database;

public class DatabasePSU extends Database {
	
	public DatabasePSU(File file) {
		super(file);
	}

	/**
	 * Write a line to the psu.csv file
	 */
	public void writeToCSV(String productCode, String productName, String type, int wattage, String grade, double price, String warranty) {
		
		this.primaryKey = productCode;
		
		this.deleteFromCSV(primaryKey);
		
		this.params = new String[] {
				productCode,
				productName, 
				type, 
				Integer.toString(wattage), 
				grade,
				Double.toString(price),
				warranty
				};
		
		super.write();
	}

}
