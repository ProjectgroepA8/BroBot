package panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import customComponents.MapLabel;

public class RouteSplitpane extends JPanel {
	RouteSplitpaneDisplay rightpanel;
	RouteSplitpanePlanner leftpanel;
	private int rows, columns;
	JButton pauze, hervatten, clear, upload, automatisch;
	JPanel menubar;
	   
	public RouteSplitpane(){	
		//Linker paneel van de splitPane
		leftpanel = new RouteSplitpanePlanner();
		leftpanel.setMinimumSize(new Dimension(200, 100));
		
		//rechter paneel van de splitPane
		rightpanel = new RouteSplitpaneDisplay();
		rightpanel.setMinimumSize(new Dimension(200, 100));
		
		//aantal opties voor de splitpane
		JSplitPane split = new JSplitPane();
		split.setResizeWeight(0.5);
		split.setContinuousLayout(true);
		Component divider = split.getComponent(2);
		divider.setCursor(new Cursor(Cursor.HAND_CURSOR));

		//panels toevoegen aan de splitpane
		split.setLeftComponent(leftpanel);
		split.setRightComponent(rightpanel);
		
	    menubar = new JPanel(new BorderLayout());
	   	menubar.setLayout(new GridLayout(1,4));
		
	   	menubar.add(automatisch = new JButton("Automatische route"));
		menubar.add(upload = new JButton("Upload"));
	   	menubar.add(clear = new JButton("Clear"));
	   	clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rightpanel.refresh();
				leftpanel.clear();				
			}
		});
	   	menubar.add(pauze = new JButton("Pauze"));
		menubar.add(hervatten = new JButton("Hervatten"));
		this.setLayout(new BorderLayout());
		this.add(split, BorderLayout.CENTER);
		this.add(menubar, BorderLayout.SOUTH);
	}
	public void setFieldSize(int y, int x, ArrayList<ArrayList<Integer>> cor){
		rightpanel.setGridsize(x, y);
    	leftpanel.drawField(x, y, cor);
    	setRows(y);
    	setColumns(x);
	}
	public void automatischBerekenen(Iconbar iconbar){
		iconbar.setMessage("Zet eerste coordinaat", 0);
		
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}	
	
}
