package com.yh.chat.gui.chatpanel;

import com.yh.chat.gui.UI_Elements.Layout;
import com.yh.chat.gui.UI_Elements.StyledPanel;
import com.yh.chat.gui.searchpanel.CreateChat;
import com.yh.chat.gui.searchpanel.Project;
import com.yh.chat.gui.values.WindowSizes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.yh.chat.core.display.MessageSorter;
import com.yh.chat.core.objects.Message;
import com.yh.chat.core.objects.SharedFolder;
import com.yh.chat.core.objects.SortableByDate;

public class ChatPanel extends StyledPanel implements AdjustmentListener{
	JScrollBar vertical;
	JPanel innerPanel = new JPanel();
	private MessageSorter messageSorter;
	private int messageAmount = 10;
	private static boolean allowScrollUp = true;
	JLabel noDataLabel;
	private Rectangle panelBounds;
	
	public void update()
	{
		if(vertical != null && vertical.getValue() + vertical.getHeight() == vertical.getMaximum())
			scrollToBottom();
		innerPanel.removeAll();
		List<SortableByDate> messages = messageSorter.getMessages(messageAmount);
		
		noDataLabel = new JLabel("no data");
		noDataLabel.setBounds(100,300,100,20);
		innerPanel.add(noDataLabel);
		
		if (messages.size()<=0 && innerPanel.getComponents().length<=1){
			noDataLabel.setVisible(true);
		}
		else
		{
			noDataLabel.setVisible(false);
			for (SortableByDate message : messages){
				MessagePanel messagePanel = new MessagePanel(message);
				int ySize = messagePanel.getYSize();
				Dimension dim = new Dimension(Layout.getChatPanel().width-20, ySize);
				messagePanel.setPreferredSize(dim);
				messagePanel.setMaximumSize(dim);
				messagePanel.setMinimumSize(dim);
				messagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
				innerPanel.add(messagePanel);
			}
		}
		
		validate();
	}
	
	private void initLayout(){
		panelBounds = Layout.getChatPanel();
		
		
	}
	
	public void update(MessageSorter messageSorter)
	{
		this.messageSorter = messageSorter;
		update();
	}
	
	public class RunOuter implements Runnable
	{
		int val;
		public RunOuter(int val)
		{
			this.val = val;
		}
		
		public void run()
		{
			EventQueue.invokeLater(new RunLater(val));
		}
	}
	
	public class RunLater implements Runnable
	{
		int val;
		public RunLater(int val)
		{
			this.val = val;
		}
		public void run() {
           	vertical.setValue(vertical.getMaximum() - val);
           	allowScrollUp = true;
        }

	}
	
	public class AdjListener implements AdjustmentListener
	{
		public void adjustmentValueChanged(AdjustmentEvent e) 
		{
	    	if(e.getValue() == 0 && allowScrollUp)
	    	{
	    		allowScrollUp = false;
	    		int max = vertical.getMaximum();
	    		messageAmount +=10;
	    		scrollToVal(max);
	    		update();
	    	}
		}
		
	}
	
	public ChatPanel(MessageSorter messageSorter){
		innerPanel.removeAll();
		setLayout(null);
		
		update(messageSorter);
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
		JScrollPane scroll = new JScrollPane(innerPanel);
		scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scroll.setBounds(5,5,Layout.getChatPanel().width-10,2*Layout.getChatPanel().height/3);
		add(scroll);
		vertical = scroll.getVerticalScrollBar();
		vertical.setValue(1);
		vertical.addAdjustmentListener(new AdjListener());
		vertical.setUnitIncrement(16);
		
		scrollToBottom();
		validate();
		repaint();
	}
	public void scrollToVal(int val)
	{
        EventQueue.invokeLater(new RunOuter(val));
	}
	
	public void scrollToBottom()
	{
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                    	vertical.setValue(vertical.getMaximum());
                    }
                });
            }
        });
		
	}

	public void adjustmentValueChanged(AdjustmentEvent e) {
		// TODO Auto-generated method stub
		
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
