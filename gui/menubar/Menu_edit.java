package menubar;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Menu_edit extends JMenu {
	public Menu_edit(){
		setText("Bewerken");
		addSeparator();
		add(new JMenuItem("Stap ongedaan maken"));
		add(new JMenuItem("Opnieuw"));
		addSeparator();
		add(new JMenuItem("Knippen"));
		add(new JMenuItem("Kopieren"));
		add(new JMenuItem("Plakken"));	
	}
}
