package com.yh.chat.gui.chatpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.yh.chat.core.display.MessageSorter;
import com.yh.chat.core.objects.SharedFolder;
import com.yh.chat.gui.main.Frame;

public class Chat implements ActionListener{
	SharedFolder sharedFolder;
	public Chat(SharedFolder sharedFolder){
		this.sharedFolder = sharedFolder;
	}
	public void actionPerformed(ActionEvent arg0) {
		Frame.updateChat(sharedFolder);
	}
	
}
