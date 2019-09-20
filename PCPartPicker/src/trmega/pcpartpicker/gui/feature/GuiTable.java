package trmega.pcpartpicker.gui.feature;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import trmega.pcpartpicker.database.Database;

@SuppressWarnings("serial")
public class GuiTable extends JPanel {
	
	public static GuiTable CPU = new GuiTable(Database.CPU);
	public static GuiTable GPU = new GuiTable(Database.GPU);
	public static GuiTable MB = new GuiTable(Database.MB);
	public static GuiTable RAM = new GuiTable(Database.RAM);
	public static GuiTable CPU_COOLING = new GuiTable(Database.CPU_COOLING);
	public static GuiTable CASE = new GuiTable(Database.CASE);
	public static GuiTable CASE_COOLING = new GuiTable(Database.CASE_COOLING);
	public static GuiTable PSU = new GuiTable(Database.PSU);
	public static GuiTable STORAGE = new GuiTable(Database.STORAGE);
	public static GuiTable OS = new GuiTable(Database.OS);
	public static GuiTable ORDER = new GuiTable(Database.ORDER);
	public static GuiTable ORDER_DETAILS = new GuiTable(Database.ORDER_DETAILS);
	public static GuiTable COMPATABILITY_LISTS = new GuiTable(Database.COMPATABILITY_LISTS);
	public static GuiTable USER = new GuiTable(Database.USER);
	public static GuiTable USER_TRANSACTIONS = new GuiTable(Database.USER_TRANSACTIONS);
	
	public static GuiTable[] tables = new GuiTable[] {CPU, GPU, MB, RAM, CPU_COOLING, CASE, CASE_COOLING, PSU, STORAGE, OS,
		ORDER, ORDER_DETAILS, COMPATABILITY_LISTS, USER, USER_TRANSACTIONS};
	
	/** The Database that is associated with the table GUI */
	private Database database;
	
	/** The table of the Database GUI */
	private JTable table;
	
	private DefaultTableModel tableModel;
	
	public GuiTable(Database database) {
		super(new BorderLayout());
		
		this.database = database;
		
		JTable table = new JTable();
		this.table = table;

		try {
			table.setModel(new DefaultTableModel(readCSV(database.getFile()), database.getHeaders()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JScrollPane sp = new JScrollPane(table);
		
		sp.getVerticalScrollBar().setPreferredSize(new Dimension(20, Integer.MAX_VALUE));
		sp.getHorizontalScrollBar().setPreferredSize(new Dimension(Integer.MAX_VALUE, 20));
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		sp.getViewport().setBackground(Color.BLACK);
		//table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionBackground(new Color(80, 255, 240, 70));
	    table.setRowHeight(23);
	    table.setBackground(new Color(250, 250, 250));
	    
	    this.tableModel = (DefaultTableModel) table.getModel();
	    this.add(sp, BorderLayout.CENTER);
	}
	
	private static Object[][] readCSV(File file) throws IOException {
	    List<Object[]> lines = new ArrayList<>();
	    try (BufferedReader r = new BufferedReader(new FileReader(file))) {
	        String line;
	        while ((line = r.readLine()) != null)
	            lines.add(line.split(","));
	    }
	    return lines.toArray(new Object[lines.size()][]);
	}
	
	
	/** Add a row to the table */
	public void addRow() {
	    tableModel.addRow(new Object[] {});
	}
	
	public void deleteRow() {
		int[] rows = table.getSelectedRows();
	    //int colIndex = table.getSelectedColumn();
		
	    try {
	    	for(int i = rows.length-1; i >= 0; i--) {
	    		tableModel.removeRow(rows[i]);
	    	}
	    } catch(Exception e) {
	    	System.out.println("No row selected to delete!");
	    }
	}
	
	public void wipe() {
		int rowCount = table.getRowCount();
		
		for(int i = rowCount-1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
	}
	
	/** Return the table being used by the GUI */
	public JTable getTable() {
		return this.table;
	}
	
	/** Return the Database associated with the GUI */
	public Database getDatabase() {
		return this.database;
	}

}
