package gui.chatpanel;

import gui.searchpanel.CreateChat;
import gui.searchpanel.Project;

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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import core.display.MessageSorter;
import core.objects.Message;
import core.objects.SharedFolder;

public class ChatPanel extends JPanel implements AdjustmentListener{
	JScrollBar vertical;
	JPanel innerPanel = new JPanel();
	private MessageSorter messageSorter;
	
	public ChatPanel(MessageSorter messageSorter){
		setLayout(null);
		this.messageSorter = messageSorter;
		ArrayList<Message> messages = messageSorter.getMessages(15);
		
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
		JScrollPane scroll = new JScrollPane(innerPanel);
		scroll.setBounds(5,5,765,390);
		innerPanel.setBounds(5,5,765,390);
		add(scroll);
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		for (int i=messages.size()-1; i>=0; i--){
			MessagePanel messagePanel = new MessagePanel(messages.get(i));
			messagePanel.setPreferredSize(new Dimension(570,80));
			messagePanel.setMaximumSize(new Dimension(570,80));
			messagePanel.setMinimumSize(new Dimension(570,80));
			messagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			innerPanel.add(messagePanel);
		}
		
		if (messages.size()<=0){
			JLabel label = new JLabel("no data");
			label.setBounds(100,300,100,20);
			innerPanel.add(label);
		}
		
		innerPanel.setPreferredSize(new Dimension(570,400));
		innerPanel.setMaximumSize(new Dimension(570,400));
		innerPanel.setMinimumSize(new Dimension(570,400));
		
		/*
		vertical = scroll.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );
		
		vertical.addAdjustmentListener(this);
		*/
		add(scroll);
	}
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		if (vertical.getValue() == vertical.getMinimum()){
			//TODO
		}
	}
	/*
	public static void addMessage(Message message){
		MessagePanel messagePanel = new MessagePanel(message);
		int ySize = messagePanel.getYSize();
		messagePanel.setPreferredSize(new Dimension(270,ySize));
		messagePanel.setMaximumSize(new Dimension(270,ySize));
		messagePanel.setMinimumSize(new Dimension(270,ySize));
		messagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		innerPanel.add(messagePanel);
		gui.main.Frame.frame.repaint();
	}*/
}
