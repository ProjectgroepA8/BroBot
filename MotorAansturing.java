package boebot;
import stamp.core.*;

public class MotorAansturing{
 private int snelheidL;
 private int snelheidR;
 private int driveSnelheid;
 private boolean richting;
 private PWM motorL, motorR;

 public MotorAansturing(){
    motorL = new PWM (CPU.pin13, 173, 2304);     // motoren op de rem zetten
    motorR = new PWM (CPU.pin12, 173, 2304);
 }
 public void setSnelheidL(int snelheid){
    snelheidL = snelheid;
 }
  public void setSnelheidR(int snelheid){
    snelheidR = snelheid;
 }
  public void setSnelheid(int snelheid){
    driveSnelheid = snelheid;
    setSnelheidR(snelheid);
    setSnelheidL(snelheid);
 }
 public void setRichting(boolean r){
    richting = r;
 }
  public int getSnelheid(){
    return snelheidL;
 }
 public int getDriveSnelheid(){
  return driveSnelheid;
 }

 public void start(){
    int maxSnelheid = snelheidL;
    for (int i=0; i<=maxSnelheid; i = i + 10)  {
             setSnelheid(i);
             rijden();
             CPU.delay (2000);
        }
  }
 public void stop(){
    int maxSnelheid = snelheidL;
    for (int i=maxSnelheid; i>0; i = i - 10)  {
             setSnelheid(i);
             rijden();
             CPU.delay (2000);
        }
    motorL.update(173, 2304);     // motoren op de rem zetten
    motorR.update(173, 2304);
  }
  public void noodStop(){
     motorL.update(173, 2304);     // motoren op de rem zetten
    motorR.update(173, 2304);
  }
 public void rijden()
    {
       int motorLWaarde =0;
       int motorRWaarde =0;
       if(richting){                     //vooruit
          motorLWaarde = 173-(23*snelheidL/100);
          motorRWaarde = 173+(23*snelheidR/100);
        }else if(!richting){                //achteruit
          motorLWaarde = 173+(23*snelheidL/100);
          motorRWaarde = 173-(23*snelheidR/100);
        }
        motorL.update(motorLWaarde, 2304);      //waardes toepassen
        motorR.update(motorRWaarde, 2304);
    }
      public  void turn(int graden)
 {
     int factor = 0;
     if(graden > 180 & graden <360){
         motorL.update(150, 2304);                  // Linksom is de kortste route
         motorR.update(150, 2304);
         graden -= 180;

     }else{
         motorL.update(196, 2304);               // rechtsom is de kortste route
         motorR.update(196, 2304);
     }
     int delay =((37* graden) / 90)*10;
     for (int i=0; i<10; i++)  {            // delay berekenen voor gegeven graades en toepasen
       CPU.delay (delay);

     }
     motorL.update(173, 2304);          // motoren afremmen en stil laten staan
     motorR.update(173, 2304);
}

}