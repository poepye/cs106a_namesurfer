/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
		// Graph
		gra = new NameSurferGraph();
		add(gra);
		
		// Label
		JLabel name = new JLabel("Name");
		add(name, NORTH);
		
		// Text Field
		tf = new JTextField(20);
		tf.addActionListener(this);
		add(tf, NORTH);
		
		// Button
		graph = new JButton("Graph"); add(graph, NORTH);
		clear = new JButton("Clear"); add(clear, NORTH);
		
		addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		// Read txt into 'file'
		NameSurferDataBase file = new NameSurferDataBase(FILE_NAME);
		
		if(e.getSource() == tf || e.getActionCommand().equals("Graph")) {
			String input = tf.getText();
			NameSurferEntry entry = file.findEntry(input);
			if (entry != null) {
				gra.addEntry(entry);
				gra.update();
			}
			tf.setText("");
		}
			
		if(e.getActionCommand().equals("Clear")) {
			gra.clear();
			gra.update();
		}
	}
	
	private JTextField tf;
	private JButton graph, clear;
	private NameSurferGraph gra;
}
