package trmega.pcpartpicker.directory;

import java.io.File;

public interface Directory {
	
	public static final File CPU = new File("database/stock/cpu.csv");
	public static final File GPU = new File("database/stock/gpu.csv");
	public static final File MB = new File("database/stock/mb.csv");
	public static final File RAM = new File("database/stock/ram.csv");
	public static final File CPU_COOLING = new File("database/stock/cpu_cooling.csv");
	public static final File CASE = new File("database/stock/case.csv");
	public static final File CASE_COOLING = new File("database/stock/case_cooling.csv");
	public static final File PSU = new File("database/stock/psu.csv");
	public static final File STORAGE = new File("database/stock/storage.csv");
	public static final File OS = new File("database/stock/os.csv");
	
	public static final File ORDER = new File("database/transaction/order.csv");
	public static final File ORDER_DETAILS = new File("database/transaction/order_details.csv");
	
	public static final File COMPATABILITY_LISTS = new File("database/compatability/compatability_lists.csv");
	
	public static final File USER = new File("database/userdata/user.csv");
	public static final File USER_TRANSACTIONS = new File("database/userdata/user_transactions.csv");

}
