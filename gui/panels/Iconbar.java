package panels;

import java.awt.GridLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Iconbar extends JPanel {
	public JButton irremote;
	public JLabel currentroute;
	public JLabel currentGridSize;
	private JLabel messages;
	Timer timer;
	
	public Iconbar(){
		currentGridSize = new JLabel();
		currentroute = new JLabel();
		irremote = new JButton("Afstandsbediening");
		messages = new JLabel();
		timer = new Timer();
		
		this.setLayout(new GridLayout(0,4,10,10));
	
		this.add(irremote);
		this.add(currentroute);
		this.add(currentGridSize);
		this.add(messages);
	}
	public void setMessage(String message, int time){
		messages.setText(message);
		if (time != 0){
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					messages.setText("");
					
				}
			}, time);
		}
	}
}
