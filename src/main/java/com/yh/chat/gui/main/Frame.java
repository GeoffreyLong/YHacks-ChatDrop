package com.yh.chat.gui.main;

import com.yh.chat.gui.chatpanel.Chat;
import com.yh.chat.gui.chatpanel.ChatPanel;
import com.yh.chat.gui.chatpanel.EmptyChat;
import com.yh.chat.gui.chatpanel.EntryPanel;
import com.yh.chat.gui.searchpanel.SearchPanel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.dropbox.core.DbxException;
import com.yh.chat.core.CoreMain;
import com.yh.chat.core.display.MessageSorter;
import com.yh.chat.core.objects.Message;
import com.yh.chat.core.objects.SharedFolder;
import com.yh.chat.core.search.Search;


public class Frame {
	public static JFrame frame = new JFrame();
	static EmptyChat empty;
	public static String name;
	private static EntryPanel entryPanel;
	
	public Frame(){
		frame.setVisible(true);
		frame.setBounds(25, 25, 1100, 700);
		frame.getContentPane().setBackground(new Color(0x123456));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLayout(null);
		
    	name = "";
		try {
			name = CoreMain.get().getOwner().getUserDisplayName();
		} catch (DbxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close Application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                	//Main.closeConnection();
                	System.exit(0);
                }
                else{
                	frame.setVisible(true);
                }
            }
        };
        frame.addWindowListener(exitListener);
	}
	public static void initSearch(List<SharedFolder> list){
		SearchPanel searchPanel = new SearchPanel(list);
		searchPanel.setBounds(5,5,300,660);
		frame.add(searchPanel);
		frame.validate();
		frame.repaint();
	}
	public static void initEmptyChat(){
		welcomePanel();
		empty = new EmptyChat();
		empty.setBounds(500,300,330,100);
		frame.add(empty);
		frame.validate();
		frame.repaint();
	}
	public static void chatQuickUpdate(SharedFolder sharedFolder){
		entryPanel.updateMessages(sharedFolder);
		updateChat(sharedFolder);
	}
	public static void initChat(MessageSorter messages, SharedFolder sharedFolder){
		welcomePanel();
		empty.setVisible(false);
		entryPanel = new EntryPanel(sharedFolder, messages);
		entryPanel.setBounds(310,500,780,165);
		chatQuickUpdate(sharedFolder);
		Timer timer = new Timer(5000, new Chat(sharedFolder));
		timer.start();
	}
	public static void updateChat(SharedFolder sharedFolder){
		frame.getContentPane().removeAll();
		Search obj = new Search();
		initSearch(obj.getCurrentConversations());
		welcomePanel();
		entryPanel.updateMessages(sharedFolder);
		MessageSorter messages = new MessageSorter(sharedFolder);
		ChatPanel chatPanel = new ChatPanel(messages);
		chatPanel.setBounds(310,65,780,430);
		frame.add(entryPanel);
		frame.add(chatPanel);
		frame.validate();
		frame.repaint();
	}
	public String newUser(String authorizeUrl){
		NewUserPanel newUser = new NewUserPanel(authorizeUrl);
		frame.setLayout(null);
		frame.add(newUser);
		frame.validate();
		frame.repaint();
		
		/*
		 * Although a blocking while statement like this 
		 * is usually frowned upon, here I think it may be acceptable
		 * as it provides a blocking method, postponing processing until 
		 * it has finished
		 */
		while(newUser.getURL() == null);
		frame.getContentPane().removeAll();
		frame.validate();
		frame.repaint();
		
		Search obj = new Search();
		initSearch(obj.getCurrentConversations());
		initEmptyChat();
		
		return newUser.getURL();
	}
	public static void welcomePanel(){
		WelcomePanel welcome = new WelcomePanel();
		welcome.setBounds(310,5,780,55);
		frame.add(welcome);
		frame.validate();
		frame.repaint();
	}
}
