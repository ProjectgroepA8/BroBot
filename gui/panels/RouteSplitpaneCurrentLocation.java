package panels;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import customComponents.Kruispunt;
/**
 * Write a description of class BoebotSimGUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RouteSplitpaneCurrentLocation extends JPanel
{
   private ArrayList<ArrayList<Kruispunt>> map;
   private int teller = -1;
   private int ox;
   private int oy;
   private int orotation;
   private int y;
   private int x;
   private int xx;
   private int xy;
   private int ex;
   private int ey;
   private JPanel content;
   JButton pauze, hervatten;
   JPanel menubar;
   
   public RouteSplitpaneCurrentLocation()
   {
       content = new JPanel(new GridLayout(1,1));
       menubar = new JPanel();
       map = new ArrayList<ArrayList<Kruispunt>>();
       //boeboet
       this.ox = 2;
       this.oy = 2;
       this.orotation = 90;
       //object
       this.xx = 1;
       this.xy = 1;
       //finish
       this.ex = 4;
       this.ey = 4;

	   	menubar.setLayout(new GridLayout(1,4));
		menubar.add(pauze = new JButton("Pauze"));
		menubar.add(hervatten = new JButton("Hervatten"));
		
       this.setLayout(new BorderLayout());
       this.add(content, BorderLayout.CENTER);
       this.add(menubar, BorderLayout.SOUTH);
   }
   public void vorigeStapBoebot()
   {
       if(teller > 0)
       {
           teller --;
           //ox = boebotSimulator.ax.get(teller);
           //oy = boebotSimulator.ay.get(teller);
           //orotation = boebotSimulator.arotation.get(teller);
           refresh();
       }
   }
   public void volgendeStapBoebot()
   {
       if(teller < 0)
       {
           teller ++;
           //ox = boebotSimulator.ax.get(teller);
           //oy = boebotSimulator.ay.get(teller);
           //orotation = boebotSimulator.arotation.get(teller);
           refresh();
       }
   }
   public void refresh()
   {
       content.removeAll();
       content.setLayout(new GridLayout(y, x));
       map.clear();
       for(int row = 0; row < y; row ++)
       {
           map.add(row, new ArrayList<Kruispunt>());
           for(int col = 0; col < x; col ++)
           {
               map.get(row).add(new Kruispunt());
               Kruispunt kruispunt = map.get(row).get(col);
               if(row == (y - ey) && col == ex)
               {
                   kruispunt.setFinish();
               }
               if(row == (y - oy) && col == ox)
               {
                   kruispunt.setBoebot(orotation);
               }
               if(row == (y - xy) && col == xx)
               {
                   kruispunt.setGat();
               }
               content.add(kruispunt);
           }
       }
   }
   public void setGridsize(int x, int y){
	   this.x = x;
	   this.y = y;
	   refresh();
   }
   public void setBoebot(int x,  int y, int o){
	   ox = x;
	   oy = y;
	   orotation = o;
	   refresh();
   }
}
