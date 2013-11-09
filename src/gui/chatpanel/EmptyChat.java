package gui.chatpanel;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EmptyChat extends JPanel{
	public EmptyChat(){
		setLayout(null);
		setBounds(0,0,600,600);
		JLabel welcomeLabel = new JLabel("Welcome to Chatterbox, Choose a chat to begin");
		welcomeLabel.setBounds(100,100,300,300);
		add(welcomeLabel);
	}
}
