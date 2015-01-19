package menubar;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Menu_help extends JMenu{
	public Menu_help(){
		setToolTipText("Help menu");
		setText("Help");
		
		
		
		add(new JMenuItem("Over brobotRouteplanner"));
	}
}
