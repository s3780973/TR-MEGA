package trmega.pcpartpicker.gui.feature;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import trmega.pcpartpicker.gui.Gui;

public class GuiOutput extends JPanel {
	
	private JTextArea output = new JTextArea(Gui.BLOCK * 13, 13);
	private JScrollPane scrollPane = new JScrollPane(output);
	
	public GuiOutput() {
		output.setWrapStyleWord(true);
        output.setLineWrap(true);
        output.setEditable(false);
		this.add(scrollPane);
	}
	
	public void print(String text) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
	    Date date = new Date();  
	    System.out.println(formatter.format(date)); 
		
	    output.append("[" + formatter.format(date) + "]" + " " + text);
	}
	
	public void println(String text) {
		this.print(text + "\n");
	}

}
