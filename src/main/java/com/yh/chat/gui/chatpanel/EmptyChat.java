package com.yh.chat.gui.chatpanel;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EmptyChat extends JPanel{
	public EmptyChat(){
		setLayout(null);
		setBounds(150,200,200,200);
		JLabel welcomeLabel = new JLabel("<html>"
				+ "Welcome to Chatterbox, <br> Choose a chat to begin"
				+ "</html>");
		welcomeLabel.setBounds(60,5,200,100);
		add(welcomeLabel);
	}
}
