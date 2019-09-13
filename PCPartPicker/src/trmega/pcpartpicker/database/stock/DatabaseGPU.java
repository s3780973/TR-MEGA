package trmega.pcpartpicker.database.stock;

import java.io.File;

import trmega.pcpartpicker.database.Database;

public class DatabaseGPU extends Database {

	public DatabaseGPU(File file) {
		super(file);
	}

	/**
	 * Write a line to the gpu.csv file
	 */
	public void writeToCSV(String productCode, String productName, String type, int capacity, int speed, String ramType, int ramBandwidth,
			int ramWidth, int powerRequired, double price, String warranty) {
		
		this.primaryKey = productCode;
		
		this.deleteFromCSV(primaryKey);
		
		this.params = new String[] {
				productCode,
				productName, 
				type, 
				Integer.toString(capacity), 
				Integer.toString(speed),
				ramType,
				Integer.toString(ramBandwidth),
				Integer.toString(ramWidth),
				Integer.toString(powerRequired),
				Double.toString(price),
				warranty
				};
		
		super.write();
	}

}
