package boebot;
import stamp.core.*;
public class VsDetectie{
  boolean vsDetect;
  private int pinRead;


 public VsDetectie( int pinR){
     pinRead = pinR;
 }

 public void setPinRead(int pin){
   pinRead = pin;
 }

 public boolean detect(){
    vsDetect = CPU.readPin(CPU.pins[pinRead]);
    return !vsDetect;
 }

}
