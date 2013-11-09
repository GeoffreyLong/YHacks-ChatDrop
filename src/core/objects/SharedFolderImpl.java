package core.objects;

import java.io.File;
import java.util.ArrayList;

import core.exceptions.NotAFolderException;

public class SharedFolderImpl implements SharedFolder{
	private String projectName;
	private File chatDirectory;
	private File topLevel;
	
	public SharedFolderImpl(File path){
		projectName=path.toString();
		if(path.isFile()){ throw new NotAFolderException("");}
		else {
			topLevel=path;
			chatDirectory= new File(path, ".chat");
		}
		
		
	}
	public File getTopLevel(){
		return topLevel;
	}
	public ArrayList<User> getUsers(){
		return null;
	}
	public File getChatFolder(){
		return chatDirectory;
	}
	public void setTopLevel(File topLevel) {
		// TODO Auto-generated method stub
		
	}
	public void setUsers(ArrayList<User> users) {
		// TODO Auto-generated method stub
		
	}
	public void setChatFolder(File chat) {
		// TODO Auto-generated method stub
		
	}
}
