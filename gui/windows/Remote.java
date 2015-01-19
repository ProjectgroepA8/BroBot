package windows;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Remote extends JFrame {
	public Remote(JPanel jp){
		setup();
		setPane(jp);
	}
	public Remote(){
		setup();
	}
	
	public void setPane(JPanel jp){
		repaint();
		this.setContentPane(jp);
		this.pack();
		dispose();
	}
	
	private void setup(){
		setTitle("Afstandsbediening");
        setMinimumSize(new Dimension(257,400));
        this.setPreferredSize(new Dimension(257,400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
