package trmega.pcpartpicker.database.transaction;

import java.io.File;

import trmega.pcpartpicker.database.Database;

public class DatabaseOrder extends Database {
	
	public DatabaseOrder(File file) {
		super(file);
	}

	/**
	 * Write a line to the order.csv file
	 */
	public void writeToCSV(String orderNo, String orderName, String orderDate, String orderTime, String progress) {
		
		this.primaryKey = orderNo;
		
		this.deleteFromCSV(primaryKey);
		
		this.params = new String[] {
				orderNo,
				orderName, 
				orderDate,
				orderTime,
				progress,
				};
		
		super.write();
	}

}
