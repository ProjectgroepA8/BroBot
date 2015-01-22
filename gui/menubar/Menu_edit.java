package menubar;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Menu_edit extends JMenu {
	public JMenuItem ongedaan;
	
	public Menu_edit(){
		ongedaan = new JMenuItem("Stap ongedaan maken");
		ongedaan.setAccelerator(KeyStroke.getKeyStroke(
		          KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		setText("Bewerken");
		add(ongedaan);
		add(new JMenuItem("Opnieuw"));
		addSeparator();
	}
}
