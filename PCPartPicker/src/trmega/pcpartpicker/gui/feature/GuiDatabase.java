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

import trmega.pcpartpicker.database.Database;

@SuppressWarnings("serial")
public class GuiDatabase extends JPanel {
	
	public static GuiDatabase CPU = new GuiDatabase(Database.CPU);
	public static GuiDatabase GPU = new GuiDatabase(Database.GPU);
	public static GuiDatabase MB = new GuiDatabase(Database.MB);
	public static GuiDatabase RAM = new GuiDatabase(Database.RAM);
	public static GuiDatabase CPU_COOLING = new GuiDatabase(Database.CPU_COOLING);
	public static GuiDatabase CASE = new GuiDatabase(Database.CASE);
	public static GuiDatabase CASE_COOLING = new GuiDatabase(Database.CASE_COOLING);
	public static GuiDatabase PSU = new GuiDatabase(Database.PSU);
	public static GuiDatabase STORAGE = new GuiDatabase(Database.STORAGE);
	public static GuiDatabase OS = new GuiDatabase(Database.OS);
	public static GuiDatabase ORDER = new GuiDatabase(Database.ORDER);
	public static GuiDatabase ORDER_DETAILS = new GuiDatabase(Database.ORDER_DETAILS);
	public static GuiDatabase COMPATABILITY_LISTS = new GuiDatabase(Database.COMPATABILITY_LISTS);
	public static GuiDatabase USER = new GuiDatabase(Database.USER);
	public static GuiDatabase USER_TRANSACTIONS = new GuiDatabase(Database.USER_TRANSACTIONS);
	
	public static GuiDatabase[] guiDatabases = new GuiDatabase[] {CPU, GPU, MB, RAM, CPU_COOLING, CASE, CASE_COOLING, PSU, STORAGE, OS,
		ORDER, ORDER_DETAILS, COMPATABILITY_LISTS, USER, USER_TRANSACTIONS};
	
	/** The Database that is associated with the table GUI */
	private Database database;
	
	/** The table of the Database GUI */
	private JTable table;
	
	public GuiDatabase(Database database) {
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionBackground(new Color(80, 255, 240, 70));
	    table.setRowHeight(23);
	    table.setBackground(new Color(250, 250, 250));
	    
		this.add(sp, BorderLayout.CENTER);
		this.setVisible(true);
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
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.addRow(new Object[] {});
	}
	
	public void deleteRow() {
		int[] rows = table.getSelectedRows();
	    //int colIndex = table.getSelectedColumn();
	      
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
		
	    try {
	    	for(int i = 0; i < rows.length; i++) {
	    		System.out.println(rows[i]);
	    		model.removeRow(rows[i]);
	    	}
	    } catch(Exception e) {
	    	System.out.println("No row selected to delete!");
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
