package gui.chatpanel;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessagePanel extends JPanel{
	private int size;
	public MessagePanel(core.objects.Message message){
		setLayout(null);
		JLabel userName = new JLabel(message.getUserName());
		JLabel date = new JLabel(message.getDate().toString());
		JLabel text = new JLabel(message.getMessageText());
		userName.setBounds(5,5,100,20);
		date.setBounds(5, 30, 100, 20);
		int messageSize = getMessageSize(message.getMessageText());
		text.setBounds(105, 5, 500, messageSize);
	}
	
	//need to use an algorithm to get the height from the amount of text
	private int getMessageSize(String text){
		size = 100;
		
		return size;
	}
	
	public int getYSize(){
		return size;
	}
}
