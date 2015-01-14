package panel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import customComponents.ComWriter;

public class AfstandbedieningPanel extends JPanel {

	private JSlider slider;
	private JTextField current;
	private JPanel richtingPanel;
	private ComWriter bluetooth;
	
	public AfstandbedieningPanel(ComWriter comwriter){
		super();	
		this.bluetooth = comwriter;
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		richtingPanel = new JPanel(new GridLayout(3,3));
		richtingPanel.setBackground(Color.BLACK);		
		add(richtingPanel);
		String[]naam = {"omhoog","links","stopButton","rechts","omlaag"};
		ImageIcon[] plaatjes = getPlaatjes(naam);
		boolean[]array = {false,true,false,
						  true,true,true,
						  false,true,false};
		char[]characters = {'v','l','s','r','a'};		
		makeRichtingPanel(array, characters, plaatjes);

			
		
		JPanel sliderPanel = new JPanel();
		sliderPanel.setBackground(Color.GRAY);
		add(sliderPanel);
		
		slider = new JSlider(0,100,50);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(10);
		slider.setBackground(Color.GRAY);
		sliderPanel.add(slider);
		
		
		
		

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setBackground(Color.GRAY);
		add(buttonPanel);
		
		JLabel currentLabel = new JLabel("Huidige Snelheid: ");	
		buttonPanel.add(currentLabel);
		
		current = new JTextField(2);
		current.setText(""+slider.getValue());
		current.setEnabled(false);
		buttonPanel.add(current);
		
		
		
		JButton toevoegen = new JButton("zet snelheid");
		toevoegen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				verzendSnelheid(slider.getValue());				
			}
			
		});
		buttonPanel.add(toevoegen);	
		
		this.setFocusable(true);		
		this.addKeyListener(new KeyListener(){				
			public void keyTyped(KeyEvent e) {			
			}					
			public void keyReleased(KeyEvent e) {				
			}			
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_UP){
					//System.out.println("v");
					bluetooth.writeString('v');
				}
				else if(e.getKeyCode() == KeyEvent.VK_DOWN){
					//System.out.println("a");
					bluetooth.writeString('a');
				}
				else if(e.getKeyCode() == KeyEvent.VK_LEFT){
					//System.out.println("l");
					bluetooth.writeString('l');
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					//System.out.println("r");
					bluetooth.writeString('r');
				}
				else if(e.getKeyCode() == KeyEvent.VK_SPACE){
					//System.out.println("s");
					bluetooth.writeString('s');
				}
			}
		});
		
	}
	
	public void setCurrentSlider(int waarde){
		slider.setValue(waarde);
		current.setText(waarde+"");
	}
	
	public int getCurrentSlider(){
		return slider.getValue();
	}
	
	public ImageIcon[] getPlaatjes(String[]naam){
		ImageIcon[]plaatjes = new ImageIcon[naam.length];
		
		for(int n = 0; n < plaatjes.length; n++){
			plaatjes[n] = new ImageIcon("pictures/"+naam[n]+".png");
		}
		
		return plaatjes;
				
	}
	
	public JButton makeButton(ImageIcon plaatje,final char charac){
		JButton button = new JButton(plaatje);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setFocusable(false);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//bluetooth.writeString(""+charac);
				System.out.println(charac);
			}			
		});
	
		return button;
	}
	
	public JLabel makeLabel(){
		JLabel label = new JLabel();
		label.setBackground(getBackground());
		return label;
	}
	
	public void makeRichtingPanel(boolean[]array,char[]characters,ImageIcon[]plaatjes){
		int teller = 0;
		for(boolean check : array){
			if(check){
				JButton button = makeButton(plaatjes[teller], characters[teller]);
				
				richtingPanel.add(button);
				teller++;
			}
			else{
				JLabel label = makeLabel();
				richtingPanel.add(label);
			}
		}
		
	}
	
	public String verzendSnelheid(int snelheid){
		String st = ""+snelheid;
		current.setText(st);
		st="f"+snelheid;
		return st;
	}
}
