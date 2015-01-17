import java.util.*;
/**
 * Write a description of class BoebotSimulator here.
 * 
 * @author Remco Sannen
 * @version (a version number or a date)
 */
public class BoebotSimulator
{
   private int rotation;
   private int x;
   private int y;
   private int xx;
   private int xy;
   private int ex;
   private int ey;
   public String coordinaten;
   public ArrayList<Integer> ax;
   public ArrayList<Integer> ay;
   public ArrayList<Integer> arotation;
   public BoebotSimulator(int x, int y, int rotation)
   {
       this.x = x;
       this.y = y;
       xx = -1;
       xy = -1;
       rotation = 0;
       ax = new ArrayList<Integer>();
       ay = new ArrayList<Integer>();
       arotation = new ArrayList<Integer>();
   }
   public void turnleft()
   {
       rotation = rotation - 90;
       if (rotation < 0)
       {
           rotation = 270;
       }
   }
   public void turnright()
   {
       rotation = rotation + 90;
       if (rotation > 270)
       {
           rotation = 0;
       }
   }
   public void keren()
   {
       rotation = rotation + 180;
       if (rotation == 360)
       {
           rotation = 0;
       }
       if(rotation == 450)
       {
           rotation = 90;
       }
   }
   public void vooruit()
   {
       if (rotation == 0)
       {
           y ++;
       }
       if (rotation == 90)
       {
           x ++;
       }
       if (rotation == 180)
       {
           y --;
       }
       if (rotation == 270)
       {
           x --;
       }
   }
   public String geefCoordinaten()
   {
       coordinaten = "(" + x + "," + y + ")";
       return coordinaten;
   }
   public boolean voerOpdrachtUit(String[] opdracht, int bx, int by, int lengte, int ex, int ey)
   {
       this.x = bx;
       this.y = by;
       this.ey = ey;
       this.ex = ex;
       int teller = 0;
       boolean botsing = false;
       ax.add(x);
       ay.add(y);
       arotation.add(rotation);
       while(teller < lengte)
       {
           if(opdracht[teller].equals("vooruit") && !(x == xx && y == xy))
           {
               vooruit();
           }
           if(opdracht[teller].equals("linksaf")&& !(x == xx && y == xy))
           {
               turnleft();
               vooruit();
           }
           if(opdracht[teller].equals("rechtsaf")&& !(x == xx && y == xy))
           {
               turnright();
               vooruit();
           }
           if(opdracht[teller].equals("keren")&& !(x == xx && y == xy))
           {
               keren();
               vooruit();
           }
           if(x == xx && y == xy)
           {
               teller = lengte - 1;
               botsing = true;
               keren();
               vooruit();
               keren();
           }
           ax.add(x);
           ay.add(y);
           arotation.add(rotation);
           teller ++;
       }
       if(x == ex && y == ey)
       {
           System.out.println("Boebot is op juiste bestemming()");
       }
       else
       {
           System.out.println("Boebot is gecrasht");
       }
       return botsing;
   }
   public String plaatsObstakel(int xx, int xy)
   {
       this.xx = xx;
       this.xy = xy;
       return "Obstakel geplaatst op: " + "(" + xx + "," + xy + ")";
   }
   public int geefX()
   {
       return x;
   }
   public int geefY()
   {
       return y;
   }
   public int geefRotation()
   {
       return rotation;
   }
   public int geefXX()
   {
       return xx;
   }
   public int geefXY()
   {
       return xy;
   }
   public int geefEX()
   {
       return ex;
   }
   public int geefEY()
   {
       return ey;
   }
}
