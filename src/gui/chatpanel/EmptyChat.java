package gui.chatpanel;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EmptyChat extends JPanel{
	public EmptyChat(){
		setLayout(null);
		setBounds(0,0,600,600);
		JLabel welcomeLabel = new JLabel("<html>"
				+ "Welcome to Chatterbox, <br> Choose a chat to begin"
				+ "</html>");
		welcomeLabel.setBounds(100,100,500,500);
		add(welcomeLabel);
	}
}
