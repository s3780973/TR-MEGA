package trmega.pcpartpicker.database.stock;

import java.io.File;

import trmega.pcpartpicker.database.Database;

public class DatabaseCase extends Database {
	
	public DatabaseCase(File file) {
		super(file);
	}
	
	/**
	 * Write a line to the case.csv file
	 */
	public void writeToCSV(String productCode, String productName, String mbSupport, int size, double price, String warranty) {
		
		this.primaryKey = productCode;
		
		this.deleteFromCSV(primaryKey);
		
		this.params = new String[] {
				productCode,
				productName, 
				mbSupport, 
				Integer.toString(size), 
				Double.toString(price),
				warranty
				};
		
		super.write();
	}

}
