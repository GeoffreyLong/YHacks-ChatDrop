package core.objects;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public interface SharedFolder extends Serializable {
	
	public File getTopLevel();
	public void setTopLevel(File topLevel);
	public List<User> getUsers();
	public void setUsers(List<User> users);
	public List<List<Message>> getMessages();
	public void setMessages(List<List<Message>> messages);
	public File getChatFolder();
	public void setChatFolder(File chat);
	public void instantiate();

}
