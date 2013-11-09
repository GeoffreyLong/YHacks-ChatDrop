package gui.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frame {
	public static JFrame frame = new JFrame();
	public Frame(){
		frame.setVisible(true);
		frame.setBounds(25, 25, 1100, 700);
		frame.setLayout(null);
		WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close Application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                	//Main.closeConnection();
                	System.exit(0);
                }
            }
        };
        frame.addWindowListener(exitListener);
	}
	public void initSearch(List<File> list){
		gui.searchpanel.SearchPanel searchPanel = new gui.searchpanel.SearchPanel(list);
		searchPanel.setBounds(5,5,295,660);
		frame.add(searchPanel);
		frame.validate();
		frame.repaint();
	}
	public void initEmptyChat(){
		gui.chatpanel.EmptyChat empty = new gui.chatpanel.EmptyChat();
		empty.setBounds(305,5,785,490);
		frame.add(empty);
		frame.validate();
		frame.repaint();
	}
	public void initChat(){
		gui.chatpanel.ChatPanel chatPanel = new gui.chatpanel.ChatPanel(null);
		gui.chatpanel.EntryPanel entryPanel = new gui.chatpanel.EntryPanel();
		chatPanel.setBounds(305,5,785,490);
		entryPanel.setBounds(305,500,785,165);
		frame.add(chatPanel);
		frame.add(entryPanel);
		frame.validate();
		frame.repaint();
	}
	public void newUser(){
		NewUserPanel newUser = new NewUserPanel();
		frame.add(newUser);
		frame.validate();
		frame.repaint();
	}
}
