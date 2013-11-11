package gui.chatpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.display.MessageSorter;
import core.objects.SharedFolder;

public class Chat implements ActionListener{
	SharedFolder sharedFolder;
	public Chat(SharedFolder sharedFolder){
		this.sharedFolder = sharedFolder;
	}
	public void actionPerformed(ActionEvent arg0) {
		gui.main.Frame.updateChat(sharedFolder);
	}
	
}
