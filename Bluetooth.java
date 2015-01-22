package boebot;
import stamp.core.*;
public class Bluetooth
{
  final static int SERIAL_RX_PIN  = CPU.pin2;
  final static int SERIAL_TX_PIN  = CPU.pin0;

  static Uart rxUart = new Uart(Uart.dirReceive, SERIAL_RX_PIN, Uart.dontInvert, Uart.speed9600, Uart.stop1);
  static Uart txUart = new Uart(Uart.dirTransmit, SERIAL_TX_PIN,Uart.dontInvert, Uart.speed9600, Uart.stop1);

  char tempChar;
  char[] route = new char[40];
  public char romoteControl = ' ';
  int speed;

  public int checkBt(){
        if (rxUart.byteAvailable()){
                tempChar = (char)rxUart.receiveByte();
                if (tempChar == '?'){
                        int i = 0;
                        clearRoute();
                        while (true){
                                if (rxUart.byteAvailable()){
                                        tempChar = (char)rxUart.receiveByte();
                                        if(tempChar == '?'){
                                                return 2;
                                        }
                                        route[i] = tempChar;
                                        i ++;
                                }
                        }
                }else if (tempChar == '!'){
                        int i = 0;
                        clearRoute();
                        while (true){
                                if (rxUart.byteAvailable()){
                                        tempChar = (char)rxUart.receiveByte();
                                        if(tempChar == '!'){
                                                route[i] = tempChar;
                                                return 3;
                                        }
                                        route[i] = tempChar;
                                        i ++;
                                }
                        }
        }else if ((tempChar == 'a') || (tempChar =='v') || (tempChar == 'l') || (tempChar == 'r') || (tempChar == 's')){
                        romoteControl = tempChar;
                        return 1;
        }else if ((tempChar == 'p') || (tempChar == 'h')){
          romoteControl = tempChar;
            return 5;

        } else if (tempChar == 'f'){
                       while (true){
                                if (rxUart.byteAvailable()){
                                      speed = Integer.parseInt(new String(new char[]{(char)rxUart.receiveByte()}));
                                      return 4;
                                }
                }           }
      }
      return 0;
}

  public char[] getRoute()
  {
    return route;
  }
  public int getSpeed(){
    if(speed >= 0 && speed <= 9){
      return (((speed + 1) * 10));
    }
    return 0;
  }
  public void sendChar(char c){
   txUart.sendByte(c);
  }

  public int[] getCoordinates()
  {
    String[] dataString = new String[40];
    int[] dataInt = new int[7];
    String coordinate = "";
    int StringArrayCount = 0;
    int intArrayCount = 0;

    for (int i = 0; route.length > i; i ++)
    {

      if (route[i] != ',' && route[i] != '!')
      {
        coordinate += new String(new char[]{route[i]});
      }

      if (route[i] == ',')
      {

        dataString[StringArrayCount] = coordinate;
        coordinate = "";
        StringArrayCount ++;
      }

      if (route[i] == '!')
      {
          i = route.length;
                  StringArrayCount = 0;
      }
    }

    for (int i = 0; dataString.length > i; i ++)
    {
        if (dataString[i] != null)
        {

            dataInt[intArrayCount] = Integer.parseInt(dataString[i]);
            intArrayCount ++;
        }

        if (dataString[i] == null)
        {
                        intArrayCount = 0;
                        return dataInt;
        }
    }
    return dataInt;
  }

  public char getChar(){
   return romoteControl;
  }
  public int getRemoteControl()
  {
    int value = 4;

    switch (romoteControl)
    {
      case 'v':
        value = 1;
        break;
      case 'a':
        value = 7;
        break;
      case 's':
        value = 4;
        break;
      case 'r':
        value = 5;
        break;
      case 'l':
        value = 3;
        break;
      default:
        value = 4;
        break;
    }

    return value;
  }

  public void clearRoute()
  {
    for(int i = 0; i < 40; i++)
    {
      route[i] = ' ';
    }
  }
}