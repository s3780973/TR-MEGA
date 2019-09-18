package trmega.pcpartpicker.gui.feature;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import trmega.pcpartpicker.database.Database;
import trmega.pcpartpicker.gui.Gui;

@SuppressWarnings("serial")
public class GuiMenuBar extends JPanel {
	
	private JLabel header = new JLabel();
	
	private JMenuBar menu = new JMenuBar();
	
	private JMenu file = new JMenu("File");
    private JMenu help = new JMenu("Help");
	
    private JMenuItem save = new JMenuItem();
	private JMenuItem saveAll = new JMenuItem("Save All Data");
	private JMenuItem exit = new JMenuItem("Exit");
	
	public GuiMenuBar() {
		
        menu.add(file);
        menu.add(help);
        
        save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Gui.output.println("Saving "+Gui.CURRENT_DATABASE.getDatabase().getName()+" data to CSV...");
				Database.exportToCSV(Gui.CURRENT_DATABASE);
			}
        	
        });
        
        saveAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Gui.output.println("Saving all data to CSV files...");
				for(int i = 0; i < GuiTable.guiDatabases.length; i++) {
					Database.exportToCSV(GuiTable.guiDatabases[i]);
				}
			}
        	
        });
        
        exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
        	
        });
        
        file.add(save);
        file.add(saveAll);
        file.add(exit);
        
        header.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.setLayout(new BorderLayout());
        
        this.add(menu, BorderLayout.WEST);
        this.add(header, BorderLayout.CENTER);
        
        this.setPreferredSize(new Dimension(0, Gui.BLOCK * 3)); //x, y
	}
	
	public void setTableName(GuiTable gui) {
		this.header.setText(gui.getDatabase().getName());
		
		this.save.setText("Save [" + gui.getDatabase().getName() + "] Data");
	}

}
