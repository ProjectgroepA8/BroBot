package panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.xml.stream.events.Characters;

import customComponents.MapLabel;

public class RouteSplitpanePlanner extends JPanel{
	
	private int rows, columns;
	private ArrayList<ArrayList<MapLabel>> labels = new ArrayList<ArrayList<MapLabel>>();
	ArrayList<ArrayList<Integer>> coordinaten;
	private ArrayList<ArrayList<MapLabel>> lines = new ArrayList<ArrayList<MapLabel>>();
	ArrayList<Character> steps = new ArrayList<Character>();
	private boolean automatisch;
	private int orientation;
	
	public RouteSplitpanePlanner(){
		setLayout(new FlowLayout());
		coordinaten = new ArrayList<ArrayList<Integer>>();
		automatisch = false;
		orientation = 0;
	}

	public void drawField(int c, int r, ArrayList<ArrayList<Integer>> cor){
		clear();
		coordinaten = cor;
		this.rows = r + 1;
		this.columns = c + 1;
	    for(int h = 0; h < rows -1; h++){
	    	labels.add(new ArrayList<MapLabel>());
	    	for(int v = 0; v < columns -1 ; v++){	
	    		labels.get(h).add(new MapLabel(0 ,0, h, v));
	    	}
	    }
	    
	    
		for(ArrayList<MapLabel> row : labels){
			for(final MapLabel label : row){
				add(label);
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(automatisch){
							
						}else{
							if(!label.isSelected() && e.getButton() == e.BUTTON1){
								if(coordinaten.size() == 0){
									label.isSelected(true);
									coordinaten.add(new ArrayList<Integer>());
									coordinaten.get(coordinaten.size()-1).add(label.getXcoord());
									coordinaten.get(coordinaten.size()-1).add(label.getYcoord());
								}else if(checkInRange(label.getXcoord(), label.getYcoord())){
									label.isSelected(true);
									coordinaten.add(new ArrayList<Integer>());
									coordinaten.get(coordinaten.size()-1).add(label.getXcoord());
									coordinaten.get(coordinaten.size()-1).add(label.getYcoord());
									
									lines.add(new ArrayList<MapLabel>());
									lines.get(lines.size()-1).add(labels.get(coordinaten.get(coordinaten.size()-2).get(0)).get(coordinaten.get(coordinaten.size()-2).get(1)));
									lines.get(lines.size()-1).add(label);
									repaint();
								}
							}else if(label.isSelected() && e.getButton() == e.BUTTON3){
								if(coordinaten.size() > 1){
									if(label.getXcoord() == coordinaten.get(coordinaten.size()-1).get(0) && label.getYcoord() == coordinaten.get(coordinaten.size()-1).get(1) ){
										lines.remove(lines.size() -1);
										coordinaten.remove(coordinaten.size() -1);
										label.isSelected(false);	
										repaint();
									}
								}
							}
						}
					}
				});
			}
		}
		boolean first = true;
		for(int i = 0; i < coordinaten.size(); i++){
			MapLabel label = labels.get(coordinaten.get(i).get(0)).get(coordinaten.get(i).get(1));
			label.isSelected(true);
			if(!first){
				lines.add(new ArrayList<MapLabel>());
				lines.get(lines.size()-1).add(labels.get(coordinaten.get(i-1).get(0)).get(coordinaten.get(i-1).get(1)));
				lines.get(lines.size()-1).add(label);
			}
			first = false;
		}
		repaint();
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	    Graphics2D graphics = (Graphics2D) g;
	    graphics.setStroke(new BasicStroke(3));
	    if( rows > 0 && columns > 0){
		    int rowheight = getHeight()/rows + 1;
		    int columnwidth = getWidth()/columns + 1; 
		    int x = 0;
		    int y = rowheight;
		    
			for(ArrayList<MapLabel> row : labels){
				for(final MapLabel label : row){
					label.setSize(x += columnwidth, y,  5, 5);
				}
				x = 0;
				y += rowheight;
			}
			x = y = 0;
		    for(int i = 1; i <= rows + 1 ; i++){
		    	graphics.drawLine(0, y += rowheight, getWidth(), y);
		    }
		    for(int i = 1; i <= columns + 1; i++){
		    	graphics.drawLine(x += columnwidth, 0, x, getHeight());
		    }
		    
		    graphics.setColor(new Color(255,0,0));
		    for(ArrayList<MapLabel> labels: lines){
		    	graphics.drawLine(labels.get(0).getX1(), labels.get(0).getY1(), labels.get(1).getX1(), labels.get(1).getY1());
		    }
	    }
	}
	
	public void clear(){
		for(ArrayList<MapLabel> row : labels){
			for(MapLabel label : row){
				label.isSelected(false);
			}
		}
		lines.clear();
		coordinaten.clear();
		steps.clear();
		orientation = 0;
		repaint();
	}
	
	public boolean checkInRange(int x, int y){
		int currentx = coordinaten.get(coordinaten.size() -1).get(0);
		int currenty = coordinaten.get(coordinaten.size() -1).get(1);

		if(currenty == y ){
			if(currentx + 1 == x){
				setStep("down");
				return true;
			}else if(currentx - 1 == x){
				setStep("up");
				return true;
			}
		}else if(currentx == x){
			if(currenty + 1 == y){
				setStep("right");
				return true;
			}else if(currenty - 1 == y){
				setStep("left");
				return true;
			}
		}
		return false;
	}

	public void setStep(String orient){
		
	}
	public void setOrientation(int o){
        orientation += o;
        if (orientation == 360)
        {
            orientation = 0;
        }else if(orientation == 450)
        {
            orientation = 90;
        }
		
	}
	public boolean isAutomatisch() {
		return automatisch;
	}

	public void setAutomatisch(boolean automatisch) {
		this.automatisch = automatisch;
		
	}
	
	

}
