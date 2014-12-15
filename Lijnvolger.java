package boebot;
import stamp.core.*;

public class Lijnvolger
{
    boolean qtiDigValue;
    IrLijnvolger IrMidden;
    IrLijnvolger IrRechts;
    IrLijnvolger IrLinks;

    public Lijnvolger()
    {
      IrMidden = new IrLijnvolger(6, 5);
      IrRechts = new IrLijnvolger(4, 3);
      IrLinks = new IrLijnvolger(8, 7);
    }


    public int readSensor()
    {
      int waarde = -1;
          if(IrMidden.readSensorLijnvolger()){
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
