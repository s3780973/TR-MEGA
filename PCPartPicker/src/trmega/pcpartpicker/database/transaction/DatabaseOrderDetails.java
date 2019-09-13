package trmega.pcpartpicker.database.transaction;

import java.io.File;

import trmega.pcpartpicker.database.Database;

public class DatabaseOrderDetails extends Database {
	
	public DatabaseOrderDetails(File file) {
		super(file);
	}

	/**
	 * Write a line to the order_details.csv file
	 */
	public void writeToCSV(String orderNo, String productCode, int quantity) {
		
		this.primaryKey = orderNo; //TODO may need changing to include productCode
		
		this.deleteFromCSV(primaryKey);
		
		this.params = new String[] {
				orderNo,
				productCode, 
				Integer.toString(quantity)
				};
		
		super.write();
	}

}
