package com.yh.chat.core.objects;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public interface SharedFolder extends Serializable {
	
	public File getTopLevel();
	public void setTopLevel(File topLevel);
	public List<User> getUsers();
	public void setUsers(List<User> users);
	public List<SortableByDate> getMessages();
	public void setMessages(List<SortableByDate> messages);
	public File getChatFolder();
	public void setChatFolder(File chat);
	public void instantiate();

}
