package trmega.pcpartpicker.gui.feature;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import trmega.pcpartpicker.database.Database;
import trmega.pcpartpicker.gui.Gui;

@SuppressWarnings("serial")
public class GuiTopPanel extends JPanel {
	
	private JLabel header = new JLabel();
	
	private JMenuBar menu = new JMenuBar();
	
	private JMenu file = new JMenu("File");
    private JMenu edit = new JMenu("Edit");
    private JMenu build = new JMenu("Build");
	
    private JMenuItem save = new JMenuItem();
	private JMenuItem saveAll = new JMenuItem("Save All Data");
	private JMenuItem exit = new JMenuItem("Exit");
	
	private JMenuItem wipe = new JMenuItem();
	private JMenuItem wipeAll = new JMenuItem("Delete All Data");
	
	private JMenuItem buildStock = new JMenuItem("Stock Data");
	
	public GuiTopPanel() {	
        menu.add(file);
        menu.add(edit);
        menu.add(build);
        
        save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Gui.output.println("Saving " + Gui.getGui().currentDatabase.getDatabase().getName() + " data to CSV...");
				Database.exportToCSV(Gui.getGui().currentDatabase);
			}
        	
        });
        
        saveAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Gui.output.println("Saving all data to CSV files...");
				for(int i = 0; i < GuiDatabase.tables.length; i++) {
					Database.exportToCSV(GuiDatabase.tables[i]);
				}
			}
        	
        });
        
        wipe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all " + header.getText() + " data?");
				
				if(confirm == JOptionPane.YES_OPTION) Gui.getGui().currentDatabase.wipe();
			}
        	
        });
        
        wipeAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ALL data? (Every database will be deleted!)");
				
				if(confirm == JOptionPane.YES_OPTION) {
					for(int i = 0; i < GuiDatabase.tables.length; i++) {
						GuiDatabase.tables[i].wipe();
					}
				}
				
			}
        	
        });
        
        exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int confirm = JOptionPane.showConfirmDialog(null, "Do you want to save?");
				
				if(confirm != JOptionPane.CANCEL_OPTION) {
					if(confirm == JOptionPane.YES_OPTION) {
						for(int i = 0; i < GuiDatabase.tables.length; i++) {
							Database.exportToCSV(GuiDatabase.tables[i]);
						}
						
					}
					
					System.exit(0);
				}
			}
        	
        });
        
        file.add(save);
        file.add(saveAll);
        file.add(exit);
        
        edit.add(wipe);
        edit.add(wipeAll);
        
        build.add(buildStock);
        
        header.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.setLayout(new BorderLayout());
        
        this.add(menu, BorderLayout.WEST);
        this.add(header, BorderLayout.CENTER);
        
        this.setPreferredSize(new Dimension(0, Gui.BLOCK * 3)); //x, y
	}
	
	public void setTableName(GuiDatabase gui) {
		this.header.setText(gui.getDatabase().getName());
		
		this.save.setText("Save [" + gui.getDatabase().getName() + "] Data");
		this.wipe.setText("Delete [" + gui.getDatabase().getName() + "] Data");
	}

}
