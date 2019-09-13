package trmega.pcpartpicker.database.stock;

import java.io.File;

import trmega.pcpartpicker.database.Database;

public class DatabaseStorage extends Database {
	
	public DatabaseStorage(File file) {
		super(file);
	}

	/**
	 * Write a line to the storage.csv file
	 */
	public void writeToCSV(String productCode, String productName, String type, int capacity, double transferSpeed, int rpm,
			String cache, double price, String warranty) {
		
		this.primaryKey = productCode;
		
		this.deleteFromCSV(primaryKey);
		
		this.params = new String[] {
				productCode,
				productName, 
				type, 
				Integer.toString(capacity), 
				Double.toString(transferSpeed),
				Integer.toString(rpm),
				cache,
				Double.toString(price),
				warranty
				};
		
		super.write();
	}

}
