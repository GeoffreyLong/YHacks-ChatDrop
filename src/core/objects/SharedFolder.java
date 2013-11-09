package core.objects;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;


public interface SharedFolder extends Serializable {
	public File getTopLevel();
	public void setTopLevel(File topLevel);
	public void setUsers(ArrayList<User> users);
	public ArrayList<User> getUsers();
	public File getChatFolder();
	public void setChatFolder(File chat);
}
