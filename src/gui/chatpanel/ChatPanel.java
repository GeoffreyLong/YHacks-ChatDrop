package gui.chatpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import core.display.MessageSorter;
import core.objects.Message;

public class ChatPanel extends JPanel implements AdjustmentListener{
	JScrollBar vertical;
	public static JPanel innerPanel;
	private MessageSorter messageSorter;
	
	public ChatPanel(MessageSorter messageSorter){
		this.messageSorter = messageSorter;
		ArrayList<Message> messages = messageSorter.getMessages(15);
		
		removeAll();
		innerPanel = new JPanel();
		innerPanel.removeAll();
		setBorder(BorderFactory.createLineBorder(Color.black));
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
		
		if (messages.size()<=0){
			JLabel label = new JLabel("no data");
			label.setBounds(100,300,100,20);
			innerPanel.add(label);
		}
		
		JScrollPane scroll = new JScrollPane(innerPanel);
		scroll.setBounds(5,5,775, 480);
		
		vertical = scroll.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );
		
		vertical.addAdjustmentListener(this);
		
		add(scroll);
	}
	
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		if (vertical.getValue() == vertical.getMinimum()){
			//TODO
		}
	}
	
	public static void addMessage(Message message){
		MessagePanel messagePanel = new MessagePanel(message);
		int ySize = messagePanel.getYSize();
		messagePanel.setPreferredSize(new Dimension(270,ySize));
		messagePanel.setMaximumSize(new Dimension(270,ySize));
		messagePanel.setMinimumSize(new Dimension(270,ySize));
		messagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		innerPanel.add(messagePanel);
		gui.main.Frame.frame.repaint();
	}
}
