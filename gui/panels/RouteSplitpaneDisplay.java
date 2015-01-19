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
public class RouteSplitpaneDisplay extends JPanel
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
   private int xrotation;
   private int ex;
   private int ey;
   private JPanel content;
   JButton pauze, hervatten;
   JPanel menubar;
   
   public RouteSplitpaneDisplay()
   {
       content = new JPanel(new GridLayout(1,1));
       menubar = new JPanel();
       map = new ArrayList<ArrayList<Kruispunt>>();
      
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
               if(row == (y - ey -1) && col == ex)
               {
                   kruispunt.setFinish();
               }
               if(row == (y - oy - 1) && col == ox)
               {
                   kruispunt.setBoebot(orotation);
               }
               if(row == (y - xy -1) && col == xx)
               {
                   kruispunt.setGat(xrotation);
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
   public void setGat(int x, int y, int o){
	   xx = x;
	   xy = y;
	   xrotation = o;
	   refresh();
   }
   public void setFinish(int x, int y){
	   ex= x;
	   ey = y;
	   refresh();
   }
}