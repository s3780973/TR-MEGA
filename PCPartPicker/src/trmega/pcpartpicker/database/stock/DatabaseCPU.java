package trmega.pcpartpicker.database.stock;

import java.io.File;

import trmega.pcpartpicker.database.Database;

public class DatabaseCPU extends Database {
	
	public DatabaseCPU(File file) {
		super(file);
	}
	
	/**
	 * Write a line to the cpu.csv file
	 */
	public void writeToCSV(String productCode, String productName, String processorNo, int numberOfCores, int numberOfThreads, int frequencySpeed,
			int powerRequired, String socketType, double price, String warranty) {
		
		this.primaryKey = productCode;
		
		this.deleteFromCSV(primaryKey);
		
		this.params = new String[] {
				productCode,
				productName, 
				processorNo, 
				Integer.toString(numberOfCores), 
				Integer.toString(numberOfThreads),
				Integer.toString(frequencySpeed),
				Integer.toString(powerRequired),
				socketType,
				Double.toString(price),
				warranty
				};
		
		super.write();
	}
}
