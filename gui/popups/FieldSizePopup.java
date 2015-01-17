package popups;

import java.awt.GridLayout;
import java.text.NumberFormat;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.text.NumberFormatter;

public class FieldSizePopup extends JPopupMenu {
	int[] fieldsize;
	public FieldSizePopup(){
	    NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(40);
	    formatter.setCommitsOnValidEdit(true);
		
		JFormattedTextField field1 = new JFormattedTextField(formatter);
		JFormattedTextField field2 = new JFormattedTextField(formatter);

		JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Hoogte:"));
        panel.add(field1);
        panel.add(new JLabel("Breedte:"));
        panel.add(field2);
        int result = JOptionPane.showConfirmDialog(null, panel, "Veld grootte",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        	if(field1.getValue() == null || field2.getValue() == null){
        		JOptionPane.showMessageDialog(null, "Ongeldige grootte");
        	}else{

        	}
        } else {
        }
	}
}
