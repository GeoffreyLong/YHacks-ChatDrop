package com.yh.chat.gui.main;

import com.yh.chat.gui.chatpanel.Chat;
import com.yh.chat.gui.chatpanel.ChatPanel;
import com.yh.chat.gui.chatpanel.EmptyChat;
import com.yh.chat.gui.chatpanel.EntryPanel;
import com.yh.chat.gui.searchpanel.SearchPanel;
import com.yh.chat.gui.values.WindowSizes;

import java.awt.Color;
import java.awt.Toolkit;
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
	private static ChatPanel chatPanel;
	private static Chat chat;
	
	public enum FrameOpts
	{
		Empty,
		Init;
	}
	
	public Frame(FrameOpts opt){
		frame.setVisible(true);
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight());  
		WindowSizes.setX(xSize);
		WindowSizes.setY(ySize);
		frame.setBounds(0,0,xSize,ySize);  
		frame.getContentPane().setBackground(new Color(0x123456));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLayout(null);
		switch(opt)
		{
			case Init:
		    	name = "";
				try {
					CoreMain main = CoreMain.get();
					main.initialize();
					name = main.getOwner().getUserDisplayName();
				} catch (DbxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;
			case Empty:
			default:
				break;
		}
		
		WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(null, "Are You You Want to Close Application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
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
		//searchPanel.setBounds(5,5,300,660);
		searchPanel.setBounds(5,5, WindowSizes.getX()/4, WindowSizes.getY()-60);
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
	
	public static void initChat(MessageSorter messages, SharedFolder sharedFolder){
		frame.getContentPane().removeAll();
		if (chatPanel!=null){
			chatPanel.removeAll();
		}
		welcomePanel();
		empty.setVisible(false);
		entryPanel = new EntryPanel(sharedFolder, messages);
		entryPanel.setBounds(WindowSizes.getX()/4+15, WindowSizes.getY()/13+20 + 2*(12*WindowSizes.getY()/13-65)/3,3*WindowSizes.getX()/4-30, 200);
		createChat(sharedFolder);
		Timer timer = new Timer(5000, chat = new Chat(sharedFolder));
		timer.start();
	}
	public static void updateChat(SharedFolder sharedFolder)
	{

		entryPanel.updateMessages(sharedFolder);
		MessageSorter mSort = new MessageSorter(sharedFolder);
		chatPanel.update(mSort);		
	}
	
	private static void createChat(SharedFolder sharedFolder)
	{
		frame.getContentPane().removeAll();
		Search obj = new Search();
		initSearch(obj.getCurrentConversations());
		welcomePanel();
		entryPanel.updateMessages(sharedFolder);
		MessageSorter messages = new MessageSorter(sharedFolder);
		chatPanel = new ChatPanel(messages);
		chatPanel.setBounds(WindowSizes.getX()/4+10,WindowSizes.getY()/13+10,3*WindowSizes.getX()/4-20, 12*WindowSizes.getY()/13 - 65);
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
		String a = newUser.getURL();
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
		
		//Search obj = new Search();
		//initSearch(obj.getCurrentConversations());
		//initEmptyChat();
		
		return newUser.getURL();
	}
	public static void welcomePanel(){
		WelcomePanel welcome = new WelcomePanel();
		welcome.setBounds(WindowSizes.getX()/4+10,5, 3*WindowSizes.getX()/4-20,WindowSizes.getY()/13);
		frame.add(welcome);
		frame.validate();
		frame.repaint();
	}
}
