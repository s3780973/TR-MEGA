package trmega.pcpartpicker.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import trmega.pcpartpicker.gui.feature.GuiBottomPanel;
import trmega.pcpartpicker.gui.feature.GuiButtonPanel;
import trmega.pcpartpicker.gui.feature.GuiDatabase;
import trmega.pcpartpicker.gui.feature.GuiTopPanel;
import trmega.pcpartpicker.gui.feature.GuiOutput;

@SuppressWarnings("serial")
public class Gui extends JPanel implements Runnable {
	
	public Frame frame;
	
	/** Instance of the GUI */
	public static Gui gui;
	
	private Thread thread = new Thread(this);
	private boolean running;
	private int width;
	
	public static int BLOCK;
	
	public GuiDatabase currentDatabase = GuiDatabase.CPU;
	
	public GuiTopPanel menuBar;
	public JPanel navigationBar;
	public static GuiOutput output;
	public GuiBottomPanel bottomPanel;
	
	public Gui(Frame frame) {
		this.frame = frame;
		
		this.width = this.frame.getWidth();
		BLOCK = this.width / 100;
		
		menuBar = new GuiTopPanel();
		navigationBar = new GuiButtonPanel(this);
		output = new GuiOutput();
		bottomPanel = new GuiBottomPanel();
		
		//Creating the MenuBar and adding components
        
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.WEST, navigationBar);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        //frame.getContentPane().add(BorderLayout.EAST, output);
        
        //Add tables
        for(int i = 0; i < GuiDatabase.tables.length; i++) {
        	this.frame.getContentPane().add(BorderLayout.CENTER, GuiDatabase.tables[i]);
        }
		
        thread.start();
	}
	
	@Override
	public void run() {
		Gui.output.println("Started GUI");
		
		this.running = true;
		
		menuBar.setTableName(currentDatabase);
		this.switchDatabase(GuiDatabase.CPU);
		
		
		while(running) {
			repaint();
			
			bottomPanel.update();
		}
	}
	
	/** Get the instance of the GUI */
	public static Gui getGui() {
		return gui;
	}
	
	public void switchDatabase(GuiDatabase gui) {
		//write to CSV when buttons is switched
		this.frame.getContentPane().remove(this.currentDatabase);
		this.currentDatabase = gui;
		this.frame.getContentPane().add(BorderLayout.CENTER, currentDatabase);
	}
}
