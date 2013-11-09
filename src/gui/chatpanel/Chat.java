package gui.chatpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.display.MessageSorter;
import core.objects.SharedFolder;

public class Chat implements ActionListener{
	MessageSorter messages;
	SharedFolder sharedFolder;
	public Chat(MessageSorter messages, SharedFolder sharedFolder){
		this.messages = messages;
		this.sharedFolder = sharedFolder;
	}
	public void actionPerformed(ActionEvent arg0) {
		gui.chatpanel.ChatPanel chatPanel = new gui.chatpanel.ChatPanel(messages);
		gui.chatpanel.EntryPanel entryPanel = new gui.chatpanel.EntryPanel(sharedFolder, messages);
		chatPanel.setBounds(310,65,780,430);
		entryPanel.setBounds(310,500,780,165);
		gui.main.Frame.updateChat(chatPanel, entryPanel);
	}
	
}
