package gui.chatpanel;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessagePanel extends JPanel{
	private int size = 80;
	public MessagePanel(core.objects.Message message){
		setLayout(null);
		setVisible(true);
		JLabel userName = new JLabel(message.getUserName());
		JLabel date = new JLabel(message.getDate().toString("MMMM dd',' yyyy H':'m':'s"));
		String string = message.getMessageText();
		String fullString = "<html>";
		if (string.length()>80){
			String[] str = string.split(" ");
			int count = 0;
			int i=0;
			while(count<=80 && i<str.length){
				count += str[i].length();
				if (count < 80){
					fullString += str[i] + " ";
				}
				else{
					fullString += "<br>";
					count = 0;
					fullString += str[i] + " ";
					size+=15;
				}
				i++;
				System.out.println(fullString);
			}
			fullString += "</html>";
		}
		else{
			fullString = string;
		}
		
		JLabel text = new JLabel(fullString);
		userName.setBounds(5,5,200,15);
		date.setBounds(5,20,200, 15);
		text.setBounds(105, 5, 570, size);
		
		add(userName);
		add(date);
		add(text);
	}
	public int getYSize(){
		return size;
	}
}
