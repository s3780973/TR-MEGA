package trmega.pcpartpicker.database.compatability;

import java.io.File;

import trmega.pcpartpicker.database.Database;

public class DatabaseCompatabilityLists extends Database {
	
	public DatabaseCompatabilityLists(File file) {
		super(file);
	}

	/**
	 * Write a line to the compatability_lists.csv file
	 */
	public void writeToCSV(String cpuProductCode, String mbProductCode, String ramProductCode) {
		
		this.primaryKey = cpuProductCode; //TODO need updating to include mbProductCode, ramProductCode
		
		this.deleteFromCSV(primaryKey);
		
		this.params = new String[] {
				cpuProductCode,
				mbProductCode, 
				ramProductCode,
				};
		
		super.write();
	}

}
