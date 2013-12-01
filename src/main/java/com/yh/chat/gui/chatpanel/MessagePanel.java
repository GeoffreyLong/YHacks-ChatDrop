package com.yh.chat.gui.chatpanel;

import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.dropbox.core.DbxException;
import com.yh.chat.core.CoreMain;
import com.yh.chat.core.display.DateFormat;
import com.yh.chat.core.methods.StringUtils;
import com.yh.chat.core.objects.Message;
import com.yh.chat.core.objects.SortableByDate;
import com.yh.chat.gui.UI_Elements.StyledPanel;

public class MessagePanel extends JPanel{

	private static final long serialVersionUID = 4576626365400866967L;
	private static String owner = null;
	private int height = 80;
	
	public MessagePanel(SortableByDate obj)
	{
		if(obj instanceof Message)
			instantiate((Message) obj);
		
	}
	
	public void instantiate(Message message)
	{
		if(owner == null)
		{
			try {
				owner = CoreMain.get().getOwner().getUserDisplayName();
			} catch (DbxException e) {
				e.printStackTrace();
			}
		}
		setLayout(null);
		setVisible(true);
		JLabel userName = new JLabel(message.getUserName());
		JLabel date = new JLabel(DateFormat.pretty(message.getDate()));
		String string = message.getMessageText();
		
		/*JTextPane text = new JTextPane();
		text.setContentType("text/html");
		text.setEditable(false);*/
		JLabel text = new JLabel();
		
		StringBuilder fullString = new StringBuilder("<html>");
		List<String> lineList = StringUtils.wrap(string, text.getFontMetrics(text.getFont()), 500);
		for(String line : lineList)
			fullString.append(line).append("<br>");
		if(lineList.size() > 0) 
			fullString.setLength(fullString.length()-4);
		int height = (lineList.size()+1)*15;
		
		fullString.append("</html>");
		
		text.setText(fullString.toString());
		userName.setBounds(5,5,240,15);
		date.setBounds(5,20,240, 15);
		date.setFont(date.getFont().deriveFont(0, 8f));
		text.setBounds(105, 5, 580, height);
		
		this.height = 40 > height ? 40 : height+10;
		
		add(userName);
		add(date);
		add(text);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		if(message.getUserName().equals(owner))
		{
			this.setOpaque(true);
			this.setBackground(Color.lightGray);
		}
	}
	public int getYSize(){
		return height;
	}
}
