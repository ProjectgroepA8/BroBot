package customComponents;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MapLabel extends JLabel{
	
	int x1, y1;
	public int xcoord, ycoord;
	private int selected;
	
	public MapLabel(int x1, int y1, int xcoord, int ycoord){
		this.x1 = x1;
		this.y1 = y1;
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		selected = 0;
		isSelected(false);
		setBounds(x1 - 3,y1 - 3, 6, 6);
		setOpaque(true);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public void isSelected(boolean selected){
		if(selected){
			this.selected++;
		}else{
			if(this.selected != 0){
				this.selected--;
			}
		}
		if(isSelected()){
			setBackground(new Color(255,0,0));
			setBorder(BorderFactory.createLineBorder(new Color(255,0,0), 1, true));

		}else{
			setBackground(new Color(100, 100, 100));
			setBorder(BorderFactory.createLineBorder(new Color(80,80,80), 1, true));
		}
	}
	/*public void paintComponent(Graphics g){
		super.paintComponent(g);
	    Graphics2D graphics = (Graphics2D) g;
	    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    Shape circle = new Ellipse2D.Double(1, 1, 5, 5);
	    graphics.setPaint(Color.BLACK);
	    graphics.fill(circle);
	    graphics.draw(circle);
	    
	}*/
	public void setSize(int x, int y , int width, int height){
		setBounds(x - 3,y - 3, 6, 6);
		setX1(x);
		setY1(y);
	}
	public boolean isSelected() {
		if(selected > 0){
			return true;
		}else{
			return false;
		}
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getXcoord() {
		return xcoord;
	}

	public void setXcoord(int xcoord) {
		this.xcoord = xcoord;
	}

	public int getYcoord() {
		return ycoord;
	}

	public void setYcoord(int ycoord) {
		this.ycoord = ycoord;
	}
	
}
