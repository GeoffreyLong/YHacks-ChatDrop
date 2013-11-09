package core.objects;

import java.io.File;
import java.util.ArrayList;
import core.init.UserTemp;

public interface SharedFolder {
	public File getTopLevel();
	public ArrayList<UserTemp> getUsers();
}
