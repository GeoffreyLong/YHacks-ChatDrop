package gui.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frame {
	static JFrame frame = new JFrame();
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
        initSearch();
        initChat();
	}
	public void initSearch(){
		gui.searchpanel.SearchPanel searchPanel = new gui.searchpanel.SearchPanel();
		frame.add(searchPanel);
	}
	public void initChat(){
		gui.chatpanel.ChatPanel chatPanel = new gui.chatpanel.ChatPanel();
	}
}
