package com.yh.chat.gui.chatpanel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EmptyChat extends JPanel{
	public EmptyChat(){
		setLayout(null);
		setBounds(150,200,200,200);
		this.setOpaque(false);
		JLabel welcomeLabel = new JLabel("<html>"
				+ "<div style='text-align:center'>"
				+ "Welcome to Chatterbox, <br> Choose a chat to begin"
				+ "</div></html>",SwingConstants.CENTER);
		welcomeLabel.setOpaque(true);
		welcomeLabel.setBounds(0,0,200,100);
		add(welcomeLabel);
	}
}
