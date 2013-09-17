/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		// You fill in the rest //
	}
	
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		// You fill this in //
		removeAll();
		list.clear();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		list.add(entry);
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		// You fill this in //
		removeAll();
		
		double curve_start = GRAPH_MARGIN_SIZE;
		double curve_end = this.getHeight() - GRAPH_MARGIN_SIZE;
		double scale = (curve_end - curve_start) / 1000;
		double interval = this.getWidth() / NDECADES;
		
		// Horizontal lines
		add(new GLine(0, GRAPH_MARGIN_SIZE, this.getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine(0, this.getHeight() - GRAPH_MARGIN_SIZE, this.getWidth(), this.getHeight() - GRAPH_MARGIN_SIZE));
		
		// Vertical lines and Decades labels
		for (int i = 0; i < NDECADES; i++) {
			add(new GLine(i * interval, 0, i * interval, this.getHeight()));
			add(new GLabel(Integer.toString(START_DECADE + i*10), i * interval + 2, this.getHeight()-5));
		}
		
		if (list.isEmpty() == false) {
			for (int i = 0; i < list.size(); i++) { // each entry
				Font font = new Font("monaco", 0, 14*this.getWidth()*this.getHeight()/480000);
				NameSurferEntry data = list.get(i);
				for (int j = 0; j < NDECADES-1; j++) { // each data point
					double y1 = data.getRank(1900+j*10);
					double y2 = data.getRank(1900+(j+1)*10);
					if (y1 == 0)
						y1 = 1000;
					if (y2 == 0)
						y2 = 1000;
					GLine myLine = new GLine(j*interval, 20+y1*scale, (j+1)*interval, 20+y2*scale);
					GLabel myLabel = new GLabel(data.getName()+" "+data.getRank(1900+j*10), j*interval+2, 20+y1*scale-5);
					myLine.setColor(myColor[i % 4]);
					myLabel.setColor(myColor[i % 4]);
					myLabel.setFont(font);
					add(myLine);
					add(myLabel);
				}
				double last_label_y = data.getRank(2000);
				if (last_label_y == 0)
					last_label_y = 1000;
				GLabel myLabel = new GLabel(data.getName()+" "+data.getRank(2000), 10*interval+2, 20+last_label_y*scale-5);
				myLabel.setColor(myColor[i % 4]);
				myLabel.setFont(font);
				add(myLabel);
			}
		}
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private ArrayList<NameSurferEntry> list = new ArrayList<NameSurferEntry>();
	private Color[] myColor = {Color.BLUE, Color.BLACK, Color.RED, Color.MAGENTA};
}
