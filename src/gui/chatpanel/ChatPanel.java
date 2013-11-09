package gui.chatpanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ChatPanel extends JPanel {
	public ChatPanel(){
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	public void panelInit(core.objects.Message[] messages){
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS)); 
		for (core.objects.Message message : messages){
			innerPanel.add(new MessagePanel(message));
		}
		JScrollPane scroll = new JScrollPane(innerPanel);
		scroll.setBounds(5,5,775, 480);
		add(scroll);
	}
}
