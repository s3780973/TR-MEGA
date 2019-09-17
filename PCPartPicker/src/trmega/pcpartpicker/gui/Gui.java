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
import javax.swing.SwingUtilities;

import trmega.pcpartpicker.database.Database;
import trmega.pcpartpicker.gui.feature.GuiButton;
import trmega.pcpartpicker.gui.feature.GuiDatabase;
import trmega.pcpartpicker.gui.feature.GuiMenuBar;
import trmega.pcpartpicker.gui.feature.GuiOutput;

@SuppressWarnings("serial")
public class Gui extends JPanel implements Runnable {
	
	public Frame frame;
	private Thread thread = new Thread(this);
	private boolean running;
	private int width, height;
	
	public static int BLOCK;
	
	public static GuiDatabase CURRENT_DATABASE = GuiDatabase.CPU;
	
	public GuiMenuBar menuBar;
	public JPanel navigationBar;
	public static GuiOutput output;
	
	public Gui(Frame frame) {
		this.frame = frame;
		
		this.width = this.frame.getWidth();
		this.height = this.frame.getHeight();
		BLOCK = this.width / 100;
		
		menuBar = new GuiMenuBar();
		navigationBar = new GuiButton(this);
		output = new GuiOutput();
		
		//Creating the MenuBar and adding components
		menuBar.setTableName(CURRENT_DATABASE);
  

        //Creating the panel at bottom and adding components // the panel is not visible in output
		
		
		
        JPanel bottom = new JPanel();
        
        JButton edit1 = new JButton("Save");
        edit1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Database.exportToCSV(Gui.CURRENT_DATABASE);
			}
        	
        });
        bottom.add(edit1);
        
        JButton button2 = new JButton("Add Row");
        button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Gui.CURRENT_DATABASE.addRow();
			}
        	
        });
        bottom.add(button2);
        
        JButton button3 = new JButton("Delete Row");
        button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Gui.CURRENT_DATABASE.deleteRow();
			}
        	
        });
        bottom.add(button3);
        
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.WEST, navigationBar);
        frame.getContentPane().add(BorderLayout.SOUTH, bottom);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        //frame.getContentPane().add(BorderLayout.EAST, output);

        this.switchDatabase(GuiDatabase.CPU);
		
        thread.start();
	}
	
	@Override
	public void run() {
		Gui.output.println("Started GUI");
		
		this.running = true;
		
		while(running) {
			//run code
			//repaint();
		}
	}
	
	public void switchDatabase(GuiDatabase gui) {
		//write to CSV when buttons is switched
		this.frame.remove(Gui.CURRENT_DATABASE);
		
		Gui.CURRENT_DATABASE = gui;
		this.frame.getContentPane().add(BorderLayout.CENTER, CURRENT_DATABASE);
		frame.setVisible(true);
	}
}
