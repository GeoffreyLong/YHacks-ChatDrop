package core.objects;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;


public interface SharedFolder extends Serializable {
	public File getTopLevel();
	public ArrayList<User> getUsers();
	public File getChatFolder();
}
