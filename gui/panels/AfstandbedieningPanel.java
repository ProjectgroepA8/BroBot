package panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import customComponents.ComWriter;

public class AfstandbedieningPanel extends JPanel {

	private JSlider slider;
	private JLabel current;
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
		add(sliderPanel);
		
		slider = new JSlider(0,100,50);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(10);
		sliderPanel.add(slider);
		
		current = new JLabel();
		current.setText(getCurrentSlider()+"");
		

		JPanel buttonPanel = new JPanel(new GridLayout(1,4));
		add(buttonPanel);
		
		JButton closeButton = new JButton("Close");
		
		buttonPanel.add(closeButton);
		
		JButton toevoegen = new JButton("zet snelheid");
		toevoegen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(getCurrentSlider());
				
				
			}
			
		});
		buttonPanel.add(toevoegen);		
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
			plaatjes[n] = new ImageIcon("src/pictures/"+naam[n]+".png");
		}
		
		return plaatjes;
				
	}
	
	public JButton makeButton(ImageIcon plaatje,final char charac){
		JButton button = new JButton(plaatje);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				bluetooth.writeString(""+charac);
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
}
	
	
	
	

