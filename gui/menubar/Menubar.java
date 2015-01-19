package menubar;

import java.awt.FlowLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.border.EmptyBorder;

public class Menubar extends JMenuBar{
	public Menu_file menu_file;
	public Menu_edit menu_edit;
	public Menu_help menu_help;
	public Menubar(){
    	menu_file = new Menu_file();
    	menu_edit = new Menu_edit();
    	menu_help = new Menu_help();
		
		setBorder(new EmptyBorder(0, 15, 0, 15));
		setLayout(new FlowLayout(80));
		add(menu_file);
		add(menu_edit);
		add(menu_help);
	}
}
