package trmega.pcpartpicker.database.stock;

import java.io.File;

import trmega.pcpartpicker.database.Database;

public class DatabaseMB extends Database {
	
	public DatabaseMB(File file) {
		super(file);
	}

	/**
	 * Write a line to the mb.csv file
	 */
	public void writeToCSV(String productCode, String productName, String formFactor, String cpuSocketType, String chipset, int numberOfPCieSlots,
			String pcieType, String bios, double price, String warranty) {
		
		this.primaryKey = productCode;
		
		this.deleteFromCSV(primaryKey);
		
		this.params = new String[] {
				productCode,
				productName, 
				formFactor, 
				cpuSocketType, 
				chipset,
				Integer.toString(numberOfPCieSlots),
				pcieType,
				bios,
				Double.toString(price),
				warranty
				};
		
		super.write();
	}

}
