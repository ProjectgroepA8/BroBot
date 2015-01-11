package boebot;
public class Aansturing
{
   private int rotation;
   private int x;
   private int y;
   private int maxx;
   private int maxy;
   private int ex;
   private int ey;
   public String coordinaten;
   public Aansturing()
   {
       maxx = 0;
       maxy = 0;
       rotation = 0;
       x = 0;
       y = 0;
       ex = 0;
       ey = 0;
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
       if (rotation == 0 && y < maxy)
       {
           y ++;
       }
       if (rotation == 90 && x < maxx)
       {
           x ++;
       }
       if (rotation == 180 && y > 0)
       {
           y --;
       }
       if (rotation == 270 && x > 0)
       {
           x --;
       }
   }
   public String geefCoordinaten()
   {
       coordinaten = "(" + x + "," + y + ")";
       return coordinaten;
   }
   public String gaNaarPunt(int eindx, int eindy)
   {
       if(eindx <= maxx && eindy <= maxy && eindx >= 0 && eindy >= 0)
       {
           ex = eindx;
           ey = eindy;
       }
       while(ex != x)
       {
           if(x < ex)
           {
               if(rotation == 90)
               {
                   vooruit();
                   System.out.println("vooruit");
               }
               else
               {
                   turnleft();
                   System.out.println("turnleft");
               }
           }
           else
           {
               if(x > ex)
               {
                   if(rotation == 270)
                   {
                       vooruit();
                       System.out.println("vooruit");
                   }
                   else
                   {
                       turnleft();
                       System.out.println("turnleft");
                   }
               }
           }
       }
       while(ey != y)
       {
           if(y < ey)
           {
               if(rotation == 0)
               {
                   vooruit();
                   System.out.println("vooruit");
               }
               else
               {
                   turnright();
                   System.out.println("turnright");
               }
           }
           else
           {
               if(y > ey)
               {
                   if(rotation == 180)
                   {
                       vooruit();
                       System.out.println("vooruit");
                   }
                   else
                   {
                       turnright();
                       System.out.println("turnright");
                   }
               }
           }
       }
       return "bestemming bereikt " + geefCoordinaten();
   }
   
   public char[] berekenRoute(int eindx, int eindy, int beginx, int beginy, int rotatie, int maxxInput, int maxyInput)
   {
	   maxx = maxxInput;
	   maxy = maxyInput;

       char[] route = new char[40];
       int teller = 0;
       int links = 0;
       x = beginx;
       y = beginy;
       rotation = rotatie;
       if(eindx <= maxx && eindy <= maxy && eindx >= 0 && eindy >= 0)
       {
           ex = eindx;
           ey = eindy;
       }else{
           return route;
        }
        if(beginx <= maxx && beginy <= maxy && beginx >= 0 && beginy >= 0)
       {
           x = beginx;
           y = beginy;
       }else{
           return route;
        }
       while(ex != x)
       {
           if(x < ex)
           {
               if(rotation == 90)
               {
                   vooruit();
                   route[teller] = 'v';
                   teller ++;
               }
               if(rotation == 0)
               {
                   turnright();
                   vooruit();
                   route[teller] = 'r';
                   teller ++;
               }
               if(rotation == 270)
               {
                   keren();
                   vooruit();
                   route[teller] = 'a';
                   teller ++;
               }
               if(rotation == 180)
               {
                   turnleft();
                   vooruit();
                   route[teller] = 'l';
                   teller ++;
               }
           }
           else
           {
               if(x > ex)
               {
                   if(rotation == 270)
               {
                   vooruit();
                   route[teller] = 'v';
                   teller ++;
               }
               if(rotation == 0)
               {
                   turnright();
                   vooruit();
                   route[teller] = 'l';
                   teller ++;
               }
               if(rotation == 90)
               {
                   keren();
                   vooruit();
                   route[teller] = 'a';
                   teller ++;
               }
               if(rotation == 180)
               {
                   turnleft();
                   vooruit();
                   route[teller] = 'r';
                   teller ++;
               }
               }
           }
       }
       while(ey != y)
       {
           if(y < ey)
           {
               if(rotation == 0)
               {
                   vooruit();
                   route[teller] = 'v';
                   teller ++;
               }
               if(rotation == 270)
               {
                   turnright();
                   vooruit();
                   route[teller] = 'r';
                   teller ++;
               }
               if(rotation == 180)
               {
                   keren();
                   vooruit();
                   route[teller] = 'a';
                   teller ++;
               }
               if(rotation == 90)
               {
                   turnleft();
                   vooruit();
                   route[teller] = 'l';
                   teller ++;
               }
           }
           else
           {
               if(y > ey)
               {
                   if(rotation == 180)
               {
                   vooruit();
                   route[teller] = 'v';
                   teller ++;
               }
               if(rotation == 90)
               {
                   turnright();
                   vooruit();
                   route[teller] = 'r';
                   teller ++;
               }
               if(rotation == 0)
               {
                   keren();
                   vooruit();
                   route[teller] = 'a';
                   teller ++;
               }
               if(rotation == 270)
               {
                   turnleft();
                   vooruit();
                   route[teller] = 'l';
                   teller ++;
               }
               }
           }
       }
       System.out.println("bestemming bereikt " + geefCoordinaten());
       return route;
   }
}
