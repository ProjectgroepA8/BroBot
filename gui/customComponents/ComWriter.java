package customComponents;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.TooManyListenersException;

// Com1Writer.java 1.1 12/02/22  voor BlueJ omgeving
// @author Jan Woolderink
// @author Johan Talboom
// @version 24/12/13
//

public class ComWriter
{
    private CommPortIdentifier portId;
    private static SerialPort serialPort;
    private OutputStream outputStream;
    private InputStream in;
    
    public ComWriter() 
    {
        initCom();
    }
    
    private void initCom() 
    {
        try 
        {
            portId = CommPortIdentifier.getPortIdentifier("/dev/rfcomm0");
        } catch (NoSuchPortException e) { e.printStackTrace(); }
     
        try 
        {
            serialPort = (SerialPort) portId.open("Boebot", 2000);
        } catch (PortInUseException e) { e.printStackTrace(); }
     
        try 
        {
            outputStream = serialPort.getOutputStream();
            in = serialPort.getInputStream();
        } catch (IOException e) { e.printStackTrace(); }
        
                
        try 
        {
            serialPort.setSerialPortParams(9600,
                                           SerialPort.DATABITS_8,
                                           SerialPort.STOPBITS_1,
                                           SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {}
     
        try {
			serialPort.addEventListener(new SerialPortEventListener() {
				@Override
				public void serialEvent(SerialPortEvent arg0) {
					System.out.println("tset");
					
				}
			});
		} catch (TooManyListenersException e) {
			e.printStackTrace();
		}
        System.out.println("COM6: ");
        System.out.println(serialPort.getBaudRate());
        System.out.println(serialPort.getDataBits());
        System.out.println(serialPort.getStopBits());
    }
    private void setBaudRate(int baudRate)
    {
        int dataBits = serialPort.getDataBits();
        int stopBits = serialPort.getStopBits();
        int parity = serialPort.getParity();
        try 
        {
            serialPort.setSerialPortParams(baudRate,dataBits, stopBits, parity);
        } catch (UnsupportedCommOperationException e) 
            {System.out.println("Onbekende baudrate");}
    } 
    public InputStream getInput(){
    	return in;
    }

    
    public void writeString(String tekst) 
    {

        try 
        {
            outputStream.write(tekst.getBytes());
        } catch (IOException e) {}        
    }
	//route verzenden naar de boebot
	public void sendRoute(ArrayList<Character> route){
		String routestring = "?";
		for(Character step:route){
			routestring = routestring + step;
		}
		System.out.println(routestring);
		routestring = routestring + "?";
		writeString(routestring);
	}
	public void sendSpeed(int snelheid){
		String st = ""+snelheid;
		st="f"+(int)((snelheid-1)/10);
		writeString(st);
	}
	
	
}
