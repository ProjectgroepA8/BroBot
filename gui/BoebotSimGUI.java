import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
/**
 * Write a description of class BoebotSimGUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BoebotSimGUI extends JFrame
{
   private ArrayList<ArrayList<Kruispunt>> map;
   private int teller = -1;
   private BoebotSimulator boebotSimulator;
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
   private JPanel scherm;
   public BoebotSimGUI(BoebotSimulator boebotSimulator)
   {
       super("Boebot Simulatie");
       this.boebotSimulator = boebotSimulator;
       this.x = 10;
       this.y = 10;
       int teller = 0;
       scherm = new JPanel(new BorderLayout());
       content = new JPanel(new GridLayout(x,y));
       JPanel menu = new JPanel (new FlowLayout());
       this.ox = 0;
       this.oy = 0;
       this.orotation = 0;
       this.xx = boebotSimulator.geefXX();
       this.xy = boebotSimulator.geefXY();
       this.ex = boebotSimulator.geefEX();
       this.ey = boebotSimulator.geefEY();
       if(boebotSimulator.ax.size() > 0)
       {
           ox = boebotSimulator.ax.get(0);
           oy = boebotSimulator.ay.get(0);
       }
       map = new ArrayList<ArrayList<Kruispunt>>();
       for(int row = 0; row < y; row ++)
       {
           map.add(row, new ArrayList<Kruispunt>());
           for(int col = 0; col < x; col ++)
           {
               map.get(row).add(new Kruispunt());
               Kruispunt kruispunt = map.get(row).get(col);
               if(row == (9 - ey) && col == ex)
               {
                   kruispunt.setFinish();
               }
               if(row == (9 - oy) && col == ox)
               {
                   kruispunt.setBoebot(orotation);
               }
               if(row == (9 - xy) && col == xx)
               {
                   kruispunt.setGat();
               }
               content.add(kruispunt);
           }
       }
       JButton previous = new JButton("vorige");
       menu.add(previous);
       JButton next = new JButton("volgende");
       menu.add(next);
       previous.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                vorigeStapBoebot();
            }
        });
        
        next.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                volgendeStapBoebot();
            }
        });
       scherm.add(content, BorderLayout.CENTER);
       scherm.add(menu, BorderLayout.SOUTH);
       setContentPane(scherm);
       setSize(950,700);
       setVisible(true);
   }
   public void vorigeStapBoebot()
   {
       if(teller > 0)
       {
           teller --;
           ox = boebotSimulator.ax.get(teller);
           oy = boebotSimulator.ay.get(teller);
           orotation = boebotSimulator.arotation.get(teller);
           refresh();
       }
   }
   public void volgendeStapBoebot()
   {
       if(teller < boebotSimulator.ax.size())
       {
           teller ++;
           ox = boebotSimulator.ax.get(teller);
           oy = boebotSimulator.ay.get(teller);
           orotation = boebotSimulator.arotation.get(teller);
           refresh();
       }
   }
   public void refresh()
   {
       content.removeAll();
       for(int row = 0; row < y; row ++)
       {
           map.add(row, new ArrayList<Kruispunt>());
           for(int col = 0; col < x; col ++)
           {
               map.get(row).add(new Kruispunt());
               Kruispunt kruispunt = map.get(row).get(col);
               if(row == (9 - ey) && col == ex)
               {
                   kruispunt.setFinish();
               }
               if(row == (9 - oy) && col == ox)
               {
                   kruispunt.setBoebot(orotation);
               }
               if(row == (9 - xy) && col == xx)
               {
                   kruispunt.setGat();
               }
               content.add(kruispunt);
           }
       }
       setContentPane(scherm);
   }
}
