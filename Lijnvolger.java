package boebot;
import stamp.core.*;

public class Lijnvolger
{
    boolean qtiDigValue;
    IrLijnvolger IrMidden, IrRechts, IrLinks, IrLinksVoor;

    public Lijnvolger()
    {
      //eerste is input (witte draad), tweede is output (rode draad)
      IrMidden = new IrLijnvolger(6, 5);
      IrRechts = new IrLijnvolger(4, 3);
      IrLinks = new IrLijnvolger(8, 7);
      IrLinksVoor = new IrLijnvolger(10,9);
    }


    public int readSensor()
    {
      int waarde = -1;
      if(IrMidden.readSensorLijnvolger() && IrRechts.readSensorLijnvolger() && IrLinks.readSensorLijnvolger() && IrLinksVoor.readSensorLijnvolger()){
          waarde = 5;
          }
      else if(IrMidden.readSensorLijnvolger() && IrRechts.readSensorLijnvolger() && IrLinks.readSensorLijnvolger()){
            waarde = 4;
      } else   if(IrMidden.readSensorLijnvolger()){
           waarde = 0;
           }

      else if(IrRechts.readSensorLijnvolger()){
           waarde = 1;
           }

      else if(IrLinks.readSensorLijnvolger()){
           waarde = 2;

      }
      else{
           waarde = 3;
           }
      return waarde;
     }
}