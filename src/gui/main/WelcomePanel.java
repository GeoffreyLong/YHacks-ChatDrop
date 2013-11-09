package gui.main;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel{
	public WelcomePanel(){
		setLayout(null);
		setBounds(0,0,780,60);
		JLabel welcome = new JLabel("<html>"
				+ "Welcome to ChatterBox"
				+ "</html>");
		welcome.setBounds(310,10,300,30);
		add(welcome);
	}
}
