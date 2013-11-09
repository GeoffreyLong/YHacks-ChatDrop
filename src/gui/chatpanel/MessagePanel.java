package gui.chatpanel;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessagePanel extends JPanel{
	private int size;
	public MessagePanel(core.objects.Message message){
		setLayout(null);
		setVisible(true);
		JLabel userName = new JLabel(message.getUserName());
		JLabel date = new JLabel(message.getDate().toString("MMMM dd',' yyyy H':'m':'s"));
		JLabel text = new JLabel(message.getMessageText());
		userName.setBounds(5,5,200,15);
		date.setBounds(5,20,200, 15);
		text.setBounds(105, 5, 570, 80);
		
		add(userName);
		add(date);
		add(text);
	}
	/*
	//need to use an algorithm to get the height from the amount of text
	private int getMessageSize(String text){
		size = 100;
		
		return size;
	}
	
	public int getYSize(){
		return size;
	}*/
}
