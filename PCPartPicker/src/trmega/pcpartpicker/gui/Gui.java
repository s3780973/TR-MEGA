package trmega.pcpartpicker.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import trmega.pcpartpicker.database.Database;
import trmega.pcpartpicker.gui.feature.GuiBottomPanel;
import trmega.pcpartpicker.gui.feature.GuiButton;
import trmega.pcpartpicker.gui.feature.GuiTable;
import trmega.pcpartpicker.gui.feature.GuiMenuBar;
import trmega.pcpartpicker.gui.feature.GuiOutput;

@SuppressWarnings("serial")
public class Gui extends JPanel implements Runnable {
	
	public Frame frame;
	
	/** Instance of the GUI */
	public static Gui gui;
	
	private Thread thread = new Thread(this);
	private boolean running;
	private int width, height;
	
	public static int BLOCK;
	
	public static GuiTable CURRENT_DATABASE = GuiTable.CPU;
	
	public GuiMenuBar menuBar;
	public JPanel navigationBar;
	public static GuiOutput output;
	public GuiBottomPanel bottomPanel;
	
	private JButton b;
	
	public Gui(Frame frame) {
		this.frame = frame;
		
		this.width = this.frame.getWidth();
		this.height = this.frame.getHeight();
		BLOCK = this.width / 100;
		
		menuBar = new GuiMenuBar();
		navigationBar = new GuiButton(this);
		output = new GuiOutput();
		bottomPanel = new GuiBottomPanel();
		
		//Creating the MenuBar and adding components
		menuBar.setTableName(CURRENT_DATABASE);
  

        //Creating the panel at bottom and adding components // the panel is not visible in output
		
		
		
        JPanel bottom = new JPanel();
        
        
        
        //menuBar.setBackground(Color.DARK_GRAY);
        
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.WEST, navigationBar);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        //frame.getContentPane().add(BorderLayout.EAST, output);
        
        //Add tables
        for(int i = 0; i < GuiTable.tables.length; i++) {
        	this.frame.getContentPane().add(BorderLayout.CENTER, GuiTable.tables[i]);
        }
		
        thread.start();
	}
	
	@Override
	public void run() {
		Gui.output.println("Started GUI");
		
		this.running = true;
		
		this.switchDatabase(GuiTable.CPU);
		
		while(running) {
			repaint();
			
			bottomPanel.update();
		}
	}
	
	public void switchDatabase(GuiTable gui) {
		//write to CSV when buttons is switched
		this.frame.getContentPane().remove(Gui.CURRENT_DATABASE);
		Gui.CURRENT_DATABASE = gui;
		this.frame.getContentPane().add(BorderLayout.CENTER, CURRENT_DATABASE);
	}
}
