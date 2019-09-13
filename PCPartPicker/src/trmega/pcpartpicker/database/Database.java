package trmega.pcpartpicker.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import trmega.pcpartpicker.database.compatability.DatabaseCompatabilityLists;
import trmega.pcpartpicker.database.stock.DatabaseCPU;
import trmega.pcpartpicker.database.stock.DatabaseCPUCooling;
import trmega.pcpartpicker.database.stock.DatabaseCase;
import trmega.pcpartpicker.database.stock.DatabaseCaseCooling;
import trmega.pcpartpicker.database.stock.DatabaseGPU;
import trmega.pcpartpicker.database.stock.DatabaseMB;
import trmega.pcpartpicker.database.stock.DatabaseOS;
import trmega.pcpartpicker.database.stock.DatabasePSU;
import trmega.pcpartpicker.database.stock.DatabaseRAM;
import trmega.pcpartpicker.database.stock.DatabaseStorage;
import trmega.pcpartpicker.database.transaction.DatabaseOrder;
import trmega.pcpartpicker.database.transaction.DatabaseOrderDetails;
import trmega.pcpartpicker.database.userdata.DatabaseUser;
import trmega.pcpartpicker.database.userdata.DatabaseUserTransactions;
import trmega.pcpartpicker.directory.Directory;

import com.opencsv.CSVWriter;

public class Database {
	
	public static final DatabaseCPU CPU = new DatabaseCPU(Directory.CPU);
	public static final DatabaseGPU GPU = new DatabaseGPU(Directory.GPU);
	public static final DatabaseMB MB = new DatabaseMB(Directory.MB);
	public static final DatabaseRAM RAM = new DatabaseRAM(Directory.RAM);
	public static final DatabaseCPUCooling CPU_COOLING = new DatabaseCPUCooling(Directory.CPU_COOLING);
	public static final DatabaseCase CASE = new DatabaseCase(Directory.CASE);
	public static final DatabaseCaseCooling CASE_COOLING = new DatabaseCaseCooling(Directory.CASE_COOLING);
	public static final DatabasePSU PSU = new DatabasePSU(Directory.PSU);
	public static final DatabaseStorage STORAGE = new DatabaseStorage(Directory.STORAGE);
	public static final DatabaseOS OS = new DatabaseOS(Directory.OS);
	
	public static final DatabaseOrder ORDER = new DatabaseOrder(Directory.ORDER);
	public static final DatabaseOrderDetails ORDER_DETAILS = new DatabaseOrderDetails(Directory.ORDER_DETAILS);
	
	public static final DatabaseCompatabilityLists COMPATABILITY_LISTS = new DatabaseCompatabilityLists(Directory.COMPATABILITY_LISTS);
	
	public static final DatabaseUser USER = new DatabaseUser(Directory.USER);
	public static final DatabaseUserTransactions USER_TRANSACTIONS = new DatabaseUserTransactions(Directory.USER_TRANSACTIONS);
	
	/** List of databases an array */
	public static final Database[] databases = new Database[] {CPU, GPU, MB, RAM, CPU_COOLING, CASE, CASE_COOLING, PSU, STORAGE, OS,
		ORDER, ORDER_DETAILS, COMPATABILITY_LISTS, USER, USER_TRANSACTIONS};
	
	/** The file location where the data will be written to */
	protected File file;
	
	/** The parameters to write to a CSV */
	protected String[] params;
	
	/** The primary key of the data that is being written or deleted */
	protected String primaryKey;
	
	/** Checks whether to wipe a CSV when calling the delete method */
	protected boolean wipe;
	
	public Database(File file) {
		this.file = file;
		this.params = new String[] {};
		this.primaryKey = "";
		this.wipe = false;
	}
	
    /** Universal method to delete a line from CSV, called by child class
     * @param primaryKey
     */
	public void deleteFromCSV(String primaryKey) {
		
		this.primaryKey = primaryKey;
		
		if(!this.primaryKey.startsWith("~") && !this.primaryKey.isEmpty()) {
			this.wipe = false;
			this.delete();
		}
	}
	
	/**
	 * Universal method to wipe an entire CSV, called by child class
	 */
	public void wipeCSV() {
		this.wipe = true;
		this.delete();
	}
	
	/**
	 * Wipe all CSV files to their default state
	 */
	public static void wipeAll() {
		for(int i = 0; i < databases.length; i++) {
			databases[i].wipeCSV();
		}
	}

	
	protected void write() {
		try {

			FileWriter outputfile = new FileWriter(file, true);

			CSVWriter writer = new CSVWriter(outputfile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

			List<String[]> data = new ArrayList<String[]>();

			String[] rowdata = params;

			data.add(rowdata);

			writer.writeAll(data);
			writer.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void delete() {
		try {

			RandomAccessFile file = new RandomAccessFile(this.file.toString(), "rw");

			String delete;
			String task = "";
			
			int lineNumber = 0;
			
			while ((delete = file.readLine()) != null) {
				lineNumber++;
				
				if(!delete.contains("~")) {
					if(!wipe) {
						if (delete.startsWith(primaryKey)) {
							continue;
						}
					} else if(wipe) {
						continue;
					}
				} else {
					if(lineNumber != 1) System.out.println("[Error] " + this.getClass().getSimpleName() + ":line " + lineNumber + " | Could not delete, line contains ~ ");	
				}
				
				task += delete + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.file.toString()));
			writer.write(task);
			file.close();
			writer.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
