package core.objects;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import core.init.UserTemp;

public interface SharedFolder extends Serializable {
	public File getTopLevel();
	public ArrayList<UserTemp> getUsers();
	public File getChatFolder();
}
