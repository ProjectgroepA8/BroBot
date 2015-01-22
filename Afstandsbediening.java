package boebot;
import stamp.core.*;
public class Afstandsbediening
{
int[] databits = new int[12];
MotorAansturing motor;
boolean status = false;
int pin;
int lastspeed;
public Afstandsbediening(MotorAansturing motor, int pin)
{
    this.pin = pin;
    this.motor = motor;
    motor.setSnelheid(50);
    motor.setRichting(true);


}

     public int detect()
  {
    int startPuls = CPU.pulseIn(2000, CPU.pins[pin], false);
    if(startPuls < 200)
    {
      return -1;
    }

    for(int i=0; i<12; i++)
    {
      databits[i] = CPU.pulseIn(300, CPU.pins[pin], false);
    }

    int resultaat = 0;
    for(int i=0; i<7; i++)
    {
      if(databits[i] < 0)
      {
        return -1;
      }

      if(databits[i] > 100)
      {
        resultaat |= 1<<i;
      }
    }
    return resultaat;
  }

  public void verwerkSignaal(int getal)
  {
  if(getal != -1){
       switch(getal)
        {
          case 0:
                 motor.setSnelheidL(30);
                 motor.setSnelheidR(0);
                 motor.setRichting(true);
                 motor.rijden();
                 break;
          case 1:
                  motor.setSnelheid(motor.getDriveSnelheid());
                  motor.setRichting(true);
                  motor.rijden();
                  break;
          case 2:
                  motor.setSnelheidL(0);
                  motor.setSnelheidR(30);
                  motor.setRichting(true);
                  motor.rijden();
                  break;
          case 3:
                  motor.noodStop();
                  motor.turn(270);
                  break;

          case 4:
                  motor.noodStop();
                  break;
          case 5:
                  motor.noodStop();
                  motor.turn(90);
                  break;
          case 6:
                  motor.setSnelheidL(30);
                  motor.setSnelheidR(0);
                  motor.setRichting(false);
                  motor.rijden();
                  break;
          case 7:
                  motor.setSnelheid(motor.getDriveSnelheid());
                  motor.setRichting(false);
                  motor.rijden();
                  break;
          case 8:
                  motor.setSnelheidL(0);
                  motor.setSnelheidR(30);
                  motor.setRichting(false);
                  motor.rijden();
                  break;
          case 88:
                  motor.setSnelheid(motor.getDriveSnelheid() + 10);
                  motor.rijden();
                  break;
          case 89:
                  motor.setSnelheid(motor.getDriveSnelheid() - 10);
                  motor.rijden();
                  break;

          default:
                 break;

        }
     }
  }

  public boolean getStatus()
  {
    return status;
  }

  public void setStatus(boolean status)
  {
    this.status = status;
  }
  }