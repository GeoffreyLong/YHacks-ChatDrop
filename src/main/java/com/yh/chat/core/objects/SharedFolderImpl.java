package com.yh.chat.core.objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.yh.chat.core.exceptions.NotAFolderException;
import com.yh.chat.core.io.ChatReader;

public class SharedFolderImpl implements SharedFolder{
	private String projectName;
	private File chatDirectory;
	private File topLevel;
	private List<SortableByDate> messages;
	private List<User> users;
	
	public SharedFolderImpl(File path){
		projectName=path.getName();
		if(path.isFile()){ throw new NotAFolderException("");}
		else {
			topLevel = path;
			chatDirectory = new File(path, ".chat");
		}	
	}
	
	public void instantiate()
	{
		ChatReader read = new ChatReader(this);
		try {
			this.messages = read.compileMessages();
			this.users = read.getUsers();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public File getTopLevel(){
		return topLevel;
	}
	public List<User> getUsers(){
		return this.users;
	}
	public File getChatFolder(){
		return chatDirectory;
	}
	public void setTopLevel(File topLevel) {
		// TODO Auto-generated method stub
		
	}
	public void setChatFolder(File chat) {
		// TODO Auto-generated method stub
		
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<SortableByDate> getMessages() {
		return this.messages;
	}
	public void setMessages(List<SortableByDate> messages) {
		this.messages = messages;
		
	}
}
