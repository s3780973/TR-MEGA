package trmega.pcpartpicker.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableModel;

import trmega.pcpartpicker.directory.Directory;
import trmega.pcpartpicker.gui.feature.GuiDatabase;

import com.opencsv.CSVWriter;

public class Database {
	
	public static final Database CPU = new Database("CPU", Directory.CPU, new String[] {"Product Code", "Product Name", "Processor No", "No. of Cores", "No. of Threads", "Frequency Speed", "Power Required", "Socket Type", "Price", "Warranty"});
	public static final Database GPU = new Database("GPU", Directory.GPU, new String[] {"Product Code", "Product Name", "Type", "Capacity", "Speed", "RAM Type", "RAM Bandwidth", "RAM Width", "Power Required", "Price", "Warranty"});
	public static final Database MB = new Database("Motherboard", Directory.MB, new String[] {"Product Code", "Product Name", "Form Factor", "CPU Socket Type", "Chipset", "No. of PCie Slots", "PCie Type", "BIOS", "Price", "Warranty"});
	public static final Database RAM = new Database("RAM", Directory.RAM, new String[] {"Product Code", "Product Name", "Type", "Size", "Power Required", "Price", "Warranty"});
	public static final Database CPU_COOLING = new Database("CPU Cooling", Directory.CPU_COOLING, new String[] {"Product Code", "Product Name", "Type", "Sound", "Power Required", "Price", "Warranty"});
	public static final Database CASE = new Database("Case", Directory.CASE, new String[] {"Product Code", "Product Name", "MB Support", "Size", "Price", "Warranty"});
	public static final Database CASE_COOLING = new Database("Case Cooling", Directory.CASE_COOLING, new String[] {"Product Code", "Product Name", "Sound", "RPM", "Power Required", "Price", "Warranty"});
	public static final Database PSU = new Database("PSU", Directory.PSU, new String[] {"Product Code", "Product Name", "Type", "Wattage", "Grade", "Price", "Warranty"});
	public static final Database STORAGE = new Database("Storage", Directory.STORAGE, new String[] {"Product Code", "Product Name", "Type", "Capacity", "Transfer Speed", "RPM", "Cache", "Price", "Warranty"});
	public static final Database OS = new Database("OS", Directory.OS, new String[] {"Product Code", "Product Name", "OS Type", "Version", "Price"});
	
	public static final Database ORDER = new Database("Order", Directory.ORDER, new String[] {"Order No", "Order Name", "Date", "Time", "Progress"});
	public static final Database ORDER_DETAILS = new Database("Order Details", Directory.ORDER_DETAILS, new String[] {"Order No", "Product No", "Quantity"});
	
	public static final Database COMPATABILITY_LISTS = new Database("Compatability Lists", Directory.COMPATABILITY_LISTS, new String[] {"CPU Product Code", "MB Product Code", "RAM Product Code"});
	
	public static final Database USER = new Database("User", Directory.USER, new String[] {"Username", "Email", "Newsletter Subscriber"});
	public static final Database USER_TRANSACTIONS = new Database("User Transactions", Directory.USER_TRANSACTIONS, new String[] {"Username", "Past Transaction"});
	
	/** List of databases an array */
	public static final Database[] databases = new Database[] {CPU, GPU, MB, RAM, CPU_COOLING, CASE, CASE_COOLING, PSU, STORAGE, OS, ORDER, ORDER_DETAILS, COMPATABILITY_LISTS, USER, USER_TRANSACTIONS};
	
	/** The name of the Database */
	private String name;
	/** The file where the Database is stored */
	private File file;
	/** The headers of the Database table */
	private String[] headers;
	
	/** The primary key of the data that is being written or deleted
	 * @deprecated file deleting via primary key is no longer needed
	 */
	private String primaryKey;
	
	/** Checks whether to wipe a CSV when calling the delete method
	 * @deprecated wiping done through GUI
	 */
	private boolean wipe;
	
	/** Array of parameters to write to File
	 * @deprecated file writing is now done through GUI 
	 */
	private String[] params;
	
	public Database(String name, File file, String[] headers) {
		this.name = name;
		this.file = file;
		this.headers = headers;
		this.params = new String[] {};
		this.primaryKey = headers[0];
		this.wipe = false;
	}
	
	/** Return the file where the Database is stored */
	public File getFile() {
		return this.file;
	}
	
	/** Returns the headers of the Database table */
	public String[] getHeaders() {
		return this.headers;
	}
	
	/** Return the name of the Database */
	public String getName() {
		return this.name;
	}
	
    /** Universal method to delete a line from CSV, called by child class
     * @deprecated deleting is now done through GUI
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
	 * @deprecated wiping done through GUI
	 */
	public void wipeCSV() {
		this.wipe = true;
		this.delete();
	}
	
	/**
	 * Wipe all CSV files to their default state
	 * @deprecated wiping done through GUI
	 */
	public static void wipeAll() {
		for(int i = 0; i < databases.length; i++) {
			databases[i].wipeCSV();
		}
	}

	
	/**
	 * @deprecated no longer used as writing is done via Gui Table
	 */
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
	
	/**
	 * Delete selected params via primary key
	 * @deprecated deleting done through GUI table
	 */
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
	
	public static void exportToCSV(GuiDatabase guiDatabase) {
		exportToCSV(guiDatabase, guiDatabase.getDatabase().getFile());
	}
	
	/**
	 * Export data from table to a designated file path via the GUI
	 */
	public static boolean exportToCSV(GuiDatabase guiDatabase, File file) {

	    try {

	        TableModel model = guiDatabase.getTable().getModel();
	        FileWriter csv = new FileWriter(file);

	        /*
	        for (int i = 0; i < model.getColumnCount(); i++) {
	            csv.write(model.getColumnName(i) + ",");
	        }
	        */

	        //csv.write("\n");

	        for (int i = 0; i < model.getRowCount(); i++) {
	            for (int j = 0; j < model.getColumnCount(); j++) {
	                if(model.getValueAt(i, j) != null ) {
	                	csv.write(model.getValueAt(i, j).toString() + (j == model.getColumnCount() - 1 ? "" : ",")); //takes off last comma
	                } else {
	                	csv.write(j == model.getColumnCount() - 1 ? "" : ",");
	                }
	            }
	            
	            csv.write("\n");
	        }

	        csv.close();
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}
