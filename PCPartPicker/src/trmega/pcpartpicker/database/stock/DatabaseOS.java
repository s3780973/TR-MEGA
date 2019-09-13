package trmega.pcpartpicker.database.stock;

import java.io.File;

import trmega.pcpartpicker.database.Database;

public class DatabaseOS extends Database {
	
	public DatabaseOS(File file) {
		super(file);
	}

	/**
	 * Write a line to the os.csv file
	 */
	public void writeToCSV(String productCode, String productName, String osType, String version, double price) {
		
		this.primaryKey = productCode;
		
		this.deleteFromCSV(primaryKey);
		
		this.params = new String[] {
				productCode,
				productName, 
				osType,
				version,
				Double.toString(price),
				};
		
		super.write();
	}

}
