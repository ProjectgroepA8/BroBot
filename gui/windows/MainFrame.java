package windows;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame
{
	public MainFrame(JPanel jp){
		setup();
		setPane(jp);
	}
	public MainFrame(){
		setup();
	}
	
	public void setPane(JPanel jp){
		repaint();
		this.setContentPane(jp);
		this.pack();
	}
	
	private void setup(){
		setTitle("Brobot besturingspaneel");
        setMinimumSize(new Dimension(650,400));
        this.setBounds(0, 0, 650, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(true);
	}
}








