package gui;

public class Routeplanner
{
    private int rotation;
    private int x;
    private int y;
    private int maxx;
    private int maxy;
    private int ex;
    private int ey;
    public String coordinaten;
    private int lengte;
    public Routeplanner(int maxx, int maxy)
    {
        this.maxx = maxx;
        this.maxy = maxy;
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

    public String[] berekenRoute(int eindx, int eindy, int beginx, int beginy, int rotatie)
    {
        String[] route = new String[40];
        int teller = 0;
        int links = 0;
        rotation = rotatie;
        lengte = 0;
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
                    route[teller] = "vooruit";
                    teller ++;
                }
                if(rotation == 0)
                {
                    turnright();
                    vooruit();
                    route[teller] = "rechtsaf";
                    teller ++;
                }
                if(rotation == 270)
                {
                    keren();
                    vooruit();
                    route[teller] = "keren";
                    teller ++;
                }
                if(rotation == 180)
                {
                    turnleft();
                    vooruit();
                    route[teller] = "linksaf";
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
                        route[teller] = "vooruit";
                        teller ++;
                    }
                    if(rotation == 0)
                    {
                        turnleft();
                        vooruit();
                        route[teller] = "linksaf";
                        teller ++;
                    }
                    if(rotation == 90)
                    {
                        keren();
                        vooruit();
                        route[teller] = "keren";
                        teller ++;
                    }
                    if(rotation == 180)
                    {
                        turnright();
                        vooruit();
                        route[teller] = "rechtsaf";
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
                    route[teller] = "vooruit";
                    teller ++;
                }
                if(rotation == 270)
                {
                    turnright();
                    vooruit();
                    route[teller] = "rechtsaf";
                    teller ++;
                }
                if(rotation == 180)
                {
                    keren();
                    vooruit();
                    route[teller] = "keren";
                    teller ++;
                }
                if(rotation == 90)
                {
                    turnleft();
                    vooruit();
                    route[teller] = "linksaf";
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
                        route[teller] = "vooruit";
                        teller ++;
                    }
                    if(rotation == 90)
                    {
                        turnright();
                        vooruit();
                        route[teller] = "rechtsaf";
                        teller ++;
                    }
                    if(rotation == 0)
                    {
                        keren();
                        vooruit();
                        route[teller] = "keren";
                        teller ++;
                    }
                    if(rotation == 270)
                    {
                        turnleft();
                        vooruit();
                        route[teller] = "linksaf";
                        teller ++;
                    }
                }
            }
        }
        lengte = teller;
        teller = 0;
        while(teller < lengte)
        {
            System.out.println(route[teller]);
            teller ++;
        }
        System.out.println("Planning is met succes gemaakt" + geefCoordinaten());
        return route;
    }

    public int geefLengte()
    {
        return lengte;
    }

    public String[] ontwijk(int xx, int xy, int x, int y, int rotation)
    {
        this.x = x;
        this.y = y;
        String route[] = new String[4];
        int teller = 0;
        boolean ontwijk = false;
        if ((ey > xy && x < maxx && x == ex) || (ex < xx && y < maxy) && ontwijk == false)
        {
            turnright();
            vooruit();
            route[teller] = "rechtsaf";
            teller ++;
            turnleft();
            vooruit();
            route[teller] = "linksaf";
            teller ++;
            vooruit();
            route[teller] = "vooruit";
            teller ++;
            turnleft();
            vooruit();
            route[teller] = "linksaf"; 
            teller ++;
            ontwijk = true;
        }
        if (((ex > xx && y < maxy) || (ey < xy && x < maxx)) && ontwijk == false)
        {
            turnleft();
            vooruit();
            route[teller] = "linksaf";
            teller ++;
            turnright();
            vooruit();
            route[teller] = "rechtsaf";
            teller ++;
            vooruit();
            route[teller] = "vooruit";
            teller ++;
            turnright();
            vooruit();
            route[teller] = "rechtsaf"; 
            teller ++;
            ontwijk = true;
        }
        if (ex == xx && ex == xy)
        {
            System.out.println("ERROR: obstakel op eindstation");
        }
        lengte = teller;
        teller = 0;
        while(teller < lengte)
        {
            System.out.println(route[teller]);
            teller ++;
        }
        return route;
    }
    
    public int getRotation() {
    return rotation;
    }
    
    public char[] ontwijkBoebot(int xx, int xy, int x, int y, int rotation)
    {
        this.x = x;
        this.y = y;
        char route[] = new char[4];
        int teller = 0;
        boolean ontwijk = false;
        if ((ey > xy && x < maxx && x == ex) || (ex < xx && y < maxy) && ontwijk == false)
        {
            turnright();
            vooruit();
            route[teller] = 'r';
            teller ++;
            turnleft();
            vooruit();
            route[teller] = 'l';
            teller ++;
            turnleft();
            vooruit();
            route[teller] = 'l'; 
            teller ++;
            ontwijk = true;
        }
        if (((ex > xx && y < maxy) || (ey < xy && x < maxx)) && ontwijk == false)
        {
            turnleft();
            vooruit();
            route[teller] = 'l';
            teller ++;
            turnright();
            vooruit();
            route[teller] = 'r';
            teller ++;
            turnright();
            vooruit();
            route[teller] = 'r'; 
            teller ++;
            ontwijk = true;
        }
        if (ex == xx && ex == xy)
        {
            System.out.println("ERROR: obstakel op eindstation");
        }
        teller = 0;
        return route;
    }
    public char[] berekenRouteBoebot(int eindx, int eindy, int beginx, int beginy, int rotatie)
    {
        char[] route = new char[40];
        int teller = 0;
        int links = 0;
        rotation = rotatie;
        lengte = 0;
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
                    route[teller] = 'k';
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
                        turnleft();
                        vooruit();
                        route[teller] = 'l';
                        teller ++;
                    }
                    if(rotation == 90)
                    {
                        keren();
                        vooruit();
                        route[teller] = 'k';
                        teller ++;
                    }
                    if(rotation == 180)
                    {
                        turnright();
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
                    route[teller] = 'k';
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
                        route[teller] = 'k';
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
        lengte = teller;
        teller = 0;
        while(teller < lengte)
        {
            System.out.println(route[teller]);
            teller ++;
        }
        System.out.println("Planning is met succes gemaakt" + geefCoordinaten());
        return route;
    }
}
