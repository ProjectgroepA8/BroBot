package menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Menu_file extends JMenu {
	public JMenuItem nieuw, openen, opslaan, opslaanals, afsluiten;
	
	public Menu_file(){
		nieuw = new JMenuItem("Nieuw");
		openen = new JMenuItem("Openen");
		opslaan = new JMenuItem("Opslaan");
		
		opslaanals = new JMenuItem("Opslaan als");
		
		afsluiten = new JMenuItem("Afsluiten");
		afsluiten.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		});
			
	
		setText("Bestand");
		addSeparator();
		
		
		//keystrokes
		nieuw.setAccelerator(KeyStroke.getKeyStroke(
		          KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		openen.setAccelerator(KeyStroke.getKeyStroke(
		          KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		opslaan.setAccelerator(KeyStroke.getKeyStroke(
		          KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		afsluiten.setAccelerator(KeyStroke.getKeyStroke(
		          KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		
		add(nieuw);
		add(openen);
		addSeparator();
		add(opslaan);
		add(opslaanals);	
		addSeparator();
		add(afsluiten);
	}
	public void test(){};
}
