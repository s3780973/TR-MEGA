package trmega.pcpartpicker.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.Document;

import trmega.pcpartpicker.database.Database;
import trmega.pcpartpicker.gui.features.GuiMenuBar;
import trmega.pcpartpicker.gui.features.GuiNavigationBar;

public class Gui extends JPanel implements Runnable {
	
	private Frame frame;
	
	private Thread thread = new Thread(this);

	private boolean running;
	
	private int width, height;
	
	public static int BLOCK;
	
	boolean test = true;
	
	public static JMenuBar MENU_BAR;
	public static JPanel NAVIGATION_BAR;
	
	public Gui(Frame frame) {
		this.frame = frame;
		
		this.width = this.frame.getWidth();
		this.height = this.frame.getHeight();
		BLOCK = this.width / 100;
		
		MENU_BAR = new GuiMenuBar();
		NAVIGATION_BAR = new GuiNavigationBar();
		
		//Creating the MenuBar and adding components
  

        //Creating the panel at bottom and adding components // the panel is not visible in output
        JPanel bottom = new JPanel();
        JPanel center = new JPanel();
        JLabel label = new JLabel("Enter Text");
        /*
        JTextField tf = new JTextField(17); // accepts upto 10 characters
        this.add(label); // Components Added using Flow Layout
        add(label); // Components Added using Flow Layout
        add(tf);
        */
        
        JEditorPane edit = new JEditorPane();
        edit.setEditable(true);
        edit.setBorder(new LineBorder(Color.ORANGE, 2));
        edit.setForeground(Color.BLUE);
        JScrollPane spEditor = new JScrollPane(edit,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        spEditor.setBounds(0, 0, 200, 300); //200 300
        
        JButton edit1 = new JButton("Edit");
        bottom.add(edit1);

        // Text Area at the Center
        JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.WEST, NAVIGATION_BAR);
        frame.getContentPane().add(BorderLayout.SOUTH, bottom);
        frame.getContentPane().add(BorderLayout.NORTH, MENU_BAR);
        frame.getContentPane().add(BorderLayout.EAST, ta);
        frame.getContentPane().add(BorderLayout.CENTER, spEditor);
        frame.setVisible(true);
		
        thread.start();
	}
	
	@Override
	public void run() {
		System.out.println("Starting GUI...");
		System.out.println(BLOCK);
		
		this.running = true;
		
		while(running) {
		    //frame.setBackground(new Color(255, 0, (int)c*5));
			//repaint();
			//if(c == 400000) NAVIGATION_BAR.setVisible(false);
		}
		
		
	}
}
