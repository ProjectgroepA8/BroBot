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
	ArrayList<ArrayList<Integer>> automatischcor = new ArrayList<ArrayList<Integer>>();
	private boolean automatisch;
	private int orientation;
	
	private int x, y, ex, ey;
	
	
	
	public RouteSplitpanePlanner(){
		setLayout(new FlowLayout());
		coordinaten = new ArrayList<ArrayList<Integer>>();
		automatisch = false;
		orientation = 0;
	}

	public void drawField(int c, int r, ArrayList<ArrayList<Integer>> cor, ArrayList<Character> stappen){
		clear();
		labels.clear();
		this.removeAll();
		coordinaten = cor;
		this.steps = stappen;
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
						labelClick(label, e.getButton());
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
	    revalidate();
		repaint();
	}
	
	public void labelClick(MapLabel label, int e){
		if(automatisch){
			if(automatischcor.size() == 0 ){
				Iconbar.setMessage("Geef 2e coordinaat", 0);
				automatischcor.add(new ArrayList<Integer>());
				automatischcor.get(0).add(rows-label.xcoord-1);
				automatischcor.get(0).add(label.ycoord);
			}else{
				Iconbar.setMessage("Route gegenereerd!", 2000);
				label.isSelected(true);
				System.out.println(								automatischcor.get(0).get(0));
				System.out.println(								automatischcor.get(0).get(1));
				System.out.println(label.xcoord);
				System.out.println(label.ycoord);
				berekenRoute(label.ycoord, rows-label.xcoord-1, automatischcor.get(0).get(1), automatischcor.get(0).get(0), 0);
				System.out.println(coordinaten);
				System.out.println(steps);
				drawField(columns-1, rows-1, new ArrayList<ArrayList<Integer>>(coordinaten), new ArrayList<Character>(steps));
				
			}
		}else{
			if(e == 0x01){
				if(coordinaten.size() == 0){
					label.isSelected(true);
					coordinaten.add(new ArrayList<Integer>());
					coordinaten.get(coordinaten.size()-1).add(label.getXcoord());
					coordinaten.get(coordinaten.size()-1).add(label.getYcoord());
					 coordinaten.get(coordinaten.size()-1).add(0);
				}else if(checkInRange(label.getXcoord(), label.getYcoord())){
					label.isSelected(true);
					coordinaten.add(new ArrayList<Integer>());
					coordinaten.get(coordinaten.size()-1).add(label.getXcoord());
					coordinaten.get(coordinaten.size()-1).add(label.getYcoord());
					
					lines.add(new ArrayList<MapLabel>());
					lines.get(lines.size()-1).add(labels.get(coordinaten.get(coordinaten.size()-2).get(0)).get(coordinaten.get(coordinaten.size()-2).get(1)));
					lines.get(lines.size()-1).add(label);
					setStep();
					repaint();
				}
			}else if(label.isSelected() && e == 0x03){
				if(coordinaten.size() > 1){
					if(label.getXcoord() == coordinaten.get(coordinaten.size()-1).get(0) && label.getYcoord() == coordinaten.get(coordinaten.size()-1).get(1) ){
						lines.remove(lines.size() -1);
						coordinaten.remove(coordinaten.size() -1);
						label.isSelected(false);
						orientation = coordinaten.get(coordinaten.size()-1).get(2);
						steps.remove(steps.size() -1);
						repaint();
					}
				}
			}
		}
	}
	public void stepBackwards(){
		MapLabel label =labels.get(coordinaten.get(coordinaten.size()-1).get(0)).get(coordinaten.get(coordinaten.size()-1).get(1)); 
		labelClick(label, 0x03);
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
		automatisch = false;
		lines.clear();
		coordinaten.clear();
		steps.clear();
		orientation = 0;
		automatischcor.clear();
		ex = ey = x = y = 0;
		repaint();
	}
	
	public boolean checkInRange(int x, int y){
		int currentx = coordinaten.get(coordinaten.size() -1).get(0);
		int currenty = coordinaten.get(coordinaten.size() -1).get(1);

		if(currenty == y ){
			if(currentx + 1 == x){
				return true;
			}else if(currentx - 1 == x){
				return true;
			}
		}else if(currentx == x){
			if(currenty + 1 == y){
				return true;
			}else if(currenty - 1 == y){
				return true;
			}
		}
		return false;
	}

	public void setStep(){
		int last = coordinaten.size() -1;
		int x1 = coordinaten.get(last-1).get(1);
		int y1 = rows - coordinaten.get(last-1).get(0) -1;
		int x2 = coordinaten.get(last).get(1);
		int y2 = rows  - coordinaten.get(last).get(0) - 1;

        if(x1 < x2)
        {
            if(orientation == 90)
            {
                steps.add('v');
            }
            if(orientation == 0)
            {
                turnright();
                steps.add('r');
            }
            if(orientation == 270)
            {
                keren();
                steps.add('a');
            }
            if(orientation == 180)
            {
                turnleft();
                steps.add('l');
            }
        }
        else
        {
            if(x1 > x2)
            {
                if(orientation == 270)
                {
                	steps.add('v');
                }
                if(orientation == 0)
                {
                    turnleft();
                    steps.add('l');
                }
                if(orientation == 90)
                {
                    keren();
                    steps.add('k');
                }
                if(orientation == 180)
                {
                    turnright();
                    steps.add('r');
                }
            }
        }
        
        if(y1 < y2)
        {
            if(orientation == 0)
            {
                steps.add('v');
            }
            if(orientation == 270)
            {
                turnright();

                steps.add('r');
            }
            if(orientation == 180)
            {

                steps.add('a');
            }
            if(orientation == 90)
            {
                turnleft();

                steps.add('l');
            }
        }
        else
        {
            if(y1 > y2)
            {
                if(orientation == 180)
                {
                    steps.add('v');
                }
                if(orientation == 90)
                {
                    turnright();

                    steps.add('r');
                }
                if(orientation == 0)
                {
                    keren();

                    steps.add('a');
                }
                if(orientation == 270)
                {
                    turnleft();

                    steps.add('l');
                }
            }
        }
        coordinaten.get(coordinaten.size()-1).add(orientation);
		System.out.println(steps);
	}
	
	 public void turnleft()
	    {
	        orientation = orientation - 90;
	        if (orientation < 0)
	        {
	            orientation = 270;
	        }
	    }

	    public void turnright()
	    {
	        orientation = orientation + 90;
	        if (orientation > 270)
	        {
	            orientation = 0;
	        }
	    }

	    public void keren()
	    {
	        orientation = orientation + 180;
	        if (orientation == 360)
	        {
	            orientation = 0;
	        }
	        if(orientation == 450)
	        {
	            orientation = 90;
	        }
	    }
	    public void vooruit()
	    {
	        if (orientation == 0 && y < rows)
	        {
	            y ++;
	        }
	        if (orientation == 90 && x < columns)
	        {
	            x ++;
	        }
	        if (orientation == 180 && y > 0)
	        {
	            y --;
	        }
	        if (orientation == 270 && x > 0)
	        {
	            x --;
	        }
	        coordinaten.add(new ArrayList<Integer>());
	        coordinaten.get(coordinaten.size()-1).add(rows-y-1);
	        coordinaten.get(coordinaten.size()-1).add(x);
	        coordinaten.get(coordinaten.size()-1).add(orientation);
	    }
	public int getOrientation() {
			return orientation;
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
		boolean labelselected = false;
		for(ArrayList<MapLabel> row:labels){
			for(MapLabel label1:row){
				if(label1.isSelected()){
					labelselected = true;
				}
			}
		}
		if(labelselected){
			Iconbar.setMessage("Zet tweede coordinaat", 0);
			automatischcor.add(new ArrayList<Integer>());
			automatischcor.get(0).add(coordinaten.get(coordinaten.size()-1).get(0));
			automatischcor.get(0).add(coordinaten.get(coordinaten.size()-1).get(1));
			System.out.println(automatischcor.get(0).get(0));
			System.out.println(automatischcor.get(0).get(1));
		}
		else{
			Iconbar.setMessage("Zet eerste coordinaat", 0);
		}
		this.automatisch = automatisch;
		
	}
	
   public void berekenRoute(int eindx, int eindy, int beginx, int beginy, int rotatie)
    {
	   coordinaten.add(new ArrayList<Integer>());
       coordinaten.get(coordinaten.size()-1).add(rows-beginy-1);
       coordinaten.get(coordinaten.size()-1).add(beginx);
       coordinaten.get(coordinaten.size()-1).add(rotatie);
        char[] route = new char[40];
        int teller = 0;
        orientation = rotatie;
        int lengte = 0;
        if(eindx <= columns && eindy <= rows && eindx >= 0 && eindy >= 0)
        {
            ex = eindx;
            ey = eindy;
        }else{
            
        }
        if(beginx <= columns && beginy <= rows && beginx >= 0 && beginy >= 0)
        {
            x = beginx;
            y = beginy;
        }else{
          
        }
        while(ex != x)
        {
            if(x < ex)
            {
                if(orientation == 90)
                {
                    vooruit();
                    steps.add('v');
                    teller ++;
                }
                if(orientation == 0)
                {
                    turnright();
                    vooruit();
                    steps.add('r');
                    teller ++;
                }
                if(orientation == 270)
                {
                    keren();
                    vooruit();
                    steps.add('k');
                    teller ++;
                }
                if(orientation == 180)
                {
                    turnleft();
                    vooruit();
                    steps.add('l');
                    teller ++;
                }
            }
            else
            {
                if(x > ex)
                {
                    if(orientation == 270)
                    {
                        vooruit();
                        steps.add('v');
                        teller ++;
                    }
                    if(orientation == 0)
                    {
                        turnleft();
                        vooruit();
                        steps.add('l');
                        teller ++;
                    }
                    if(orientation == 90)
                    {
                        keren();
                        vooruit();
                        steps.add('k');
                        teller ++;
                    }
                    if(orientation == 180)
                    {
                        turnright();
                        vooruit();
                        steps.add('r');
                        teller ++;
                    }
                }
            }
        }
        while(ey != y)
        {
            if(y < ey)
            {
                if(orientation == 0)
                {
                    vooruit();
                    steps.add('v');
                    teller ++;
                }
                if(orientation == 270)
                {
                    turnright();
                    vooruit();
                    steps.add('r');
                    teller ++;
                }
                if(orientation == 180)
                {
                    keren();
                    vooruit();
                    steps.add('k');
                    teller ++;
                }
                if(orientation == 90)
                {
                    turnleft();
                    vooruit();
                    steps.add('l');
                    teller ++;
                }
            }
            else
            {
                if(y > ey)
                {
                    if(orientation == 180)
                    {
                        vooruit();
                        steps.add('v');
                        teller ++;
                    }
                    if(orientation == 90)
                    {
                        turnright();
                        vooruit();
                        steps.add('r');
                        teller ++;
                    }
                    if(orientation == 0)
                    {
                        keren();
                        vooruit();
                        steps.add('k');
                        teller ++;
                    }
                    if(orientation == 270)
                    {
                        turnleft();
                        vooruit();
                        steps.add('l');
                        teller ++;
                    }
                }
            }
        }
    }

}
