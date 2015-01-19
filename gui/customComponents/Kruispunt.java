package customComponents;
/**
 * Write a description of class Kruispunt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
public class Kruispunt extends JLabel
{       
    BufferedImage kruispunt;
    public Kruispunt() 
    {
     kruispunt = null;
    }
    public void setBoebot(int rotation)
    {
        if(rotation == 90)
        {
        try{
            kruispunt = ImageIO.read(new File("pictures/boebot.png"));
        }catch (IOException e){
            
        }
    }
        if(rotation == 0)
        {
        try{
            kruispunt = ImageIO.read(new File("pictures/boven boebot.png"));
        }catch (IOException e){
            
        }
    }
        if(rotation == 180)
        {
        try{
            kruispunt = ImageIO.read(new File("pictures/onder boebot.png"));
        }catch (IOException e){
            
        }
    }
        if(rotation == 270)
        {
        try{
            kruispunt = ImageIO.read(new File("pictures/links boebot.png"));
        }catch (IOException e){
            
        }
    }
        this.repaint();
    }
    public void setGat(int rotation)
    {
          if(rotation == 90)
        {
        try{
            kruispunt = ImageIO.read(new File("pictures/gatrechts.png"));
        }catch (IOException e){
            
        }
    }
        if(rotation == 0)
        {
        try{
            kruispunt = ImageIO.read(new File("pictures/gatboven.png"));
        }catch (IOException e){
            
        }
    }
        if(rotation == 180)
        {
        try{
            kruispunt = ImageIO.read(new File("pictures/gatonder.png"));
        }catch (IOException e){
            
        }
    }
        if(rotation == 270)
        {
        try{
            kruispunt = ImageIO.read(new File("pictures/gatlinks.png"));
        }catch (IOException e){
            
        }
    }
        this.repaint();
    }
    public void setFinish()
    {
        try{
            kruispunt = ImageIO.read(new File("pictures/finish.png"));
        }catch (IOException e){
            
        }
        this.repaint();
    }
    protected void paintComponent(Graphics g){
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setStroke(new BasicStroke(3));
      Shape horLine = new Line2D.Double(0,getHeight()/2,getWidth(),getHeight()/2);
      Shape verLine = new Line2D.Double(getWidth()/2,0,getWidth()/2,getHeight());
      g2.draw(horLine);
      g2.draw(verLine);
      
      g2.drawImage(kruispunt, 0, 0, getWidth(), getHeight(), null);
    }
}