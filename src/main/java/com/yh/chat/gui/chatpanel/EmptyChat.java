package com.yh.chat.gui.chatpanel;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.yh.chat.gui.UI_Elements.StyledPanel;

public class EmptyChat extends StyledPanel{
	public EmptyChat(){
		setLayout(new GridBagLayout());
		setBounds(150,200,200,100);
		this.setOpaque(true);
		JLabel welcomeLabel = new JLabel("<html>"
				+ "<div style='text-align:center'>"
				+ "Welcome to Chatterbox, <br> Choose a chat to begin"
				+ "</div></html>",SwingConstants.CENTER);
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setOpaque(false);
		add(welcomeLabel);
	}
}
