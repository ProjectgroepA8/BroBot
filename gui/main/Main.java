package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import panels.MainMenu;
import windows.MainFrame;

public class Main {

	public static void main(String[] args) {
		 try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	
	    
		new MainFrame(new MainMenu());
		
		}

}
