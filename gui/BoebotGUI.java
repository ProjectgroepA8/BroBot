import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class BoebotGUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BoebotGUI extends JFrame
{
    private Routeplanner routeplanner;
    private BoebotSimulator boebotSimulator;
    private BoebotSimGUI boebotSimGUI;
    private int intx;
    private int inty;
    private int intbx;
    private int intby;
    public BoebotGUI()
    {
        super("Brobot GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel content = new JPanel(null);
        final Routeplanner routeplanner = new Routeplanner(10,10);
        final BoebotSimulator boebotSimulator = new BoebotSimulator(0,0,0);
        JButton addButton = new JButton("geefRoute");
        content.add(addButton);
        JButton startSimulatie = new JButton("testRoute");
        content.add(startSimulatie);
        JButton object = new JButton("plaats object");
        content.add(object);
        JButton popup = new JButton("Boebot Simulatie");
        content.add(popup);
        final JLabel coordinaten = new JLabel("Coördinaten van Boebot");
        content.add(coordinaten);
        final JLabel begin = new JLabel("begin");
        content.add(begin);
        final JLabel eind = new JLabel("eind");
        content.add(eind);
        final JLabel objectlabel = new JLabel("object");
        content.add(objectlabel);
        final JLabel letterx = new JLabel("x");
        content.add(letterx);
        final JLabel lettery = new JLabel("y");
        content.add(lettery);
        final JTextField x = new JTextField(20);
        content.add(x);
        final JTextField y = new JTextField(20);
        content.add(y);
        final JTextField bx = new JTextField(20);
        content.add(bx);
        final JTextField by = new JTextField(20);
        content.add(by);
        final JTextField ox = new JTextField(20);
        content.add(ox);
        final JTextField oy = new JTextField(20);
        content.add(oy);
        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                intx = Integer.parseInt(x.getText());
                inty = Integer.parseInt(y.getText());
                intbx = Integer.parseInt(bx.getText());
                intby = Integer.parseInt(by.getText());
                routeplanner.berekenRoute(intx, inty, intby, intbx, 0);
            }
        });
        startSimulatie.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                int teller = 0;
                intx = Integer.parseInt(x.getText());
                inty = Integer.parseInt(y.getText());
                intbx = Integer.parseInt(bx.getText());
                intby = Integer.parseInt(by.getText());
                String[] route = routeplanner.berekenRoute(intx, inty, intbx, intby, 0);
                int lengte = routeplanner.geefLengte();
                boolean botsing = boebotSimulator.voerOpdrachtUit(route, intbx, intby, lengte, intx, inty);
                while(botsing == true && teller < 3)
                {
                    route = routeplanner.ontwijk(boebotSimulator.geefXX(), boebotSimulator.geefXY(), boebotSimulator.geefX(), boebotSimulator.geefY(), boebotSimulator.geefRotation());
                    lengte = routeplanner.geefLengte();
                    botsing = boebotSimulator.voerOpdrachtUit(route, boebotSimulator.geefX(), boebotSimulator.geefY(), lengte, intx, inty);
                    route = routeplanner.berekenRoute(intx, inty, boebotSimulator.geefX(), boebotSimulator.geefY(), boebotSimulator.geefRotation());
                    lengte = routeplanner.geefLengte();
                    botsing = boebotSimulator.voerOpdrachtUit(route, boebotSimulator.geefX(), boebotSimulator.geefY(), lengte, intx, inty);
                    teller ++;
                }
                if (teller == 3)
                {
                    System.out.println("Boebot is de weg kwijt");
                }
            }
        });
        object.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                int intox = Integer.parseInt(ox.getText());
                int intoy = Integer.parseInt(oy.getText());
                System.out.println(boebotSimulator.plaatsObstakel(intox,intoy));
            }
        });
        popup.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                int locatie = (boebotSimulator.geefY() * 10) + 9 - boebotSimulator.geefX();
                boebotSimGUI = new BoebotSimGUI(boebotSimulator);
            }
        });
        begin.setBounds(20,0,200,20);
        eind.setBounds(150,0,200,20);
        addButton.setBounds(20,80,100,20);
        startSimulatie.setBounds(20,100,100,20);
        coordinaten.setBounds(20,60,200,20);
        object.setBounds(300,60,200,20);
        popup.setBounds(150,150,200,80);
        bx.setBounds(20,20,100,20);
        by.setBounds(20,40,100,20);
        letterx.setBounds(5,20,100,20);
        lettery.setBounds(5,40,100,20);
        ox.setBounds(300,20,100,20);
        oy.setBounds(300,40,100,20);
        x.setBounds(150,20,100,20);
        y.setBounds(150,40,100,20);
        setContentPane(content);
        setSize(700,300);
        setVisible(true);
    }
}
