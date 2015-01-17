package panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;

import menubar.Menubar;
import windows.Remote;
import customComponents.ComWriter;
import customComponents.Filehandling;


public class MainMenu extends JPanel{
	Menubar menubar;
	RouteSplitpane routeSplitpane;
	Iconbar iconbar;
	ComWriter bluetooth;
	Remote remote;
	Filehandling file;
	
	String currentRouteFile;
	int maxx, maxy;
	ArrayList<ArrayList<Integer>> currentRouteCoordinates;
	ArrayList<Character> currentRouteSteps;
	
	public MainMenu(){
    	menubar = new Menubar();
    	routeSplitpane = new RouteSplitpane();
    	iconbar = new Iconbar();
    	file = new Filehandling();
    	//bluetooth = new ComWriter();
    	remote = new Remote(new AfstandbedieningPanel(bluetooth));
    	remote.dispose();
    	
    	
    	setLayout(new BorderLayout(0,0));
    	add(menubar, BorderLayout.NORTH);
    	add(routeSplitpane);
    	add(iconbar, BorderLayout.SOUTH);
    	add(iconbar, BorderLayout.SOUTH);
    	
    	currentRouteCoordinates = new ArrayList<ArrayList<Integer>>();
    	currentRouteSteps = new ArrayList<Character>();    	
    	
    	
    	menubarMouseListeners();	
    	
    	currentRouteFile = "";
    	iconbar.currentroute.setText("Geen bestand");  
    	menubar.menu_file.opslaan.setEnabled(false);
    	menubar.menu_file.opslaanals.setEnabled(false);
    	
    	}
 

    
    public String getCurrentRouteFile() {
		return currentRouteFile;
	}
    
    //methode om een route in te laden of om een nieuwe route aan te maken.
	public void setCurrentRouteFile(String route, ArrayList<ArrayList<Integer>> tempcorarray ){
		int c = JOptionPane.showConfirmDialog(null, "Weet u zeker dat u de huidige route wilt afsluiten?", "Alert: " + "Weet u het zeker?", JOptionPane.YES_NO_OPTION);  
    	if (c == JOptionPane.YES_OPTION) {
	        	if(tempcorarray != null){	
	        		maxx = tempcorarray.get(0).get(0);
	        		maxy = tempcorarray.get(0).get(1);
		        	routeSplitpane.rightpanel.setGridsize(maxx, maxy);
		    		iconbar.currentGridSize.setText( maxx + " x " + maxy);
		        	tempcorarray.remove(0);
		        	currentRouteCoordinates = tempcorarray;
		        	currentRouteFile = route;
		    		iconbar.currentroute.setText(currentRouteFile);
		        	menubar.menu_file.opslaan.setEnabled(true);
		        	menubar.menu_file.opslaanals.setEnabled(true);
	  	  	  }else{
	  	  		int[] tempsize = sizePopup();
	  	  		if(tempsize != null){
			  	 	routeSplitpane.rightpanel.setGridsize(tempsize[0], tempsize[1]);
		    		iconbar.currentGridSize.setText(tempsize[0] + " x " + tempsize[1]);
		        	currentRouteCoordinates.clear();
		        	currentRouteFile = "new";
		    		iconbar.currentroute.setText("Nieuw bestand");
		        	menubar.menu_file.opslaan.setEnabled(false);
		        	menubar.menu_file.opslaanals.setEnabled(true);
	  	  		}
		  	  }
	       }
	}
	
	//popup om de grootte van het veld in te voeren.
  public int[] sizePopup(){
	    NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(40);
	    formatter.setCommitsOnValidEdit(true);
		
		JFormattedTextField field1 = new JFormattedTextField(formatter);
		JFormattedTextField field2 = new JFormattedTextField(formatter);

		JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Hoogte:"));
        panel.add(field1);
        panel.add(new JLabel("Breedte:"));
        panel.add(field2);
        int result = JOptionPane.showConfirmDialog(null, panel, "Veld grootte",   JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        	if(field1.getValue() == null || field2.getValue() == null){
        		JOptionPane.showMessageDialog(null, "Ongeldige grootte");
        		return null;
        	}else{
        		return new int[]{(int) field1.getValue(),(int) field2.getValue()} ; 
        	}
        } else {
        	return null;
        }
  }
	
    public void menubarMouseListeners(){
    	//maakt de mouse listners voor de menubar
		menubar.menu_file.openen.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
			      JFileChooser c = new JFileChooser();
			      c.setFileFilter(new FileNameExtensionFilter("Route bestanden", "rt"));
			      int rVal = c.showOpenDialog(MainMenu.this);
			      if (rVal == JFileChooser.APPROVE_OPTION) {
			    	  setCurrentRouteFile(c.getSelectedFile().getAbsolutePath(), file.readRouteFile(c.getSelectedFile().getAbsolutePath()));
			      }			
			}
		});
		menubar.menu_file.opslaan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				file.writeRouteFile(maxx, maxy, currentRouteCoordinates, currentRouteFile);				
			}
		});
		menubar.menu_file.opslaanals.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 JFileChooser c = new JFileChooser();
			      c.setFileFilter(new FileNameExtensionFilter("Route bestanden", "rt"));
			      int rVal = c.showOpenDialog(MainMenu.this);
			      if (rVal == JFileChooser.APPROVE_OPTION) {
			    	  file.writeRouteFile(maxx, maxy, currentRouteCoordinates, c.getSelectedFile().getAbsolutePath());
			      }		
			}
		});
		menubar.menu_file.nieuw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCurrentRouteFile("Nieuwe route", null);
			}
		});
		
		iconbar.irremote.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createRemote();
			}
		});
    }

    public void createRemote(){
    	if(remote.isShowing()){
    		remote.dispose();
    	}else{
    		remote.show(true);
    	}
    }
}
