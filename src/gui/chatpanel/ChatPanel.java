package gui.chatpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ChatPanel extends JPanel {
	public ChatPanel(core.objects.Message[] messages){
		if (messages == null){
			JLabel welcomeLabel = new JLabel("Welcome to Chatterbox, Choose a chat to begin");
			welcomeLabel.setBounds(100,100,300,300);
			add(welcomeLabel);
		}
		else{
			removeAll();
			setBorder(BorderFactory.createLineBorder(Color.black));
			JPanel innerPanel = new JPanel();
			innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS)); 
			for (core.objects.Message message : messages){
				MessagePanel messagePanel = new MessagePanel(message);
				int ySize = messagePanel.getYSize();
				messagePanel.setPreferredSize(new Dimension(270,ySize));
				messagePanel.setMaximumSize(new Dimension(270,ySize));
				messagePanel.setMinimumSize(new Dimension(270,ySize));
				messagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
				innerPanel.add(messagePanel);
			}
			JScrollPane scroll = new JScrollPane(innerPanel);
			scroll.setBounds(5,5,775, 480);
			add(scroll);
		}
	}
}
