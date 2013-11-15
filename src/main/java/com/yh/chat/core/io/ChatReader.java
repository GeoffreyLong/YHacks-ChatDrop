package com.yh.chat.core.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

import com.yh.chat.core.objects.Message;
import com.yh.chat.core.objects.MessageImpl;
import com.yh.chat.core.objects.SharedFolder;
import com.yh.chat.core.objects.SortableByDate;
import com.yh.chat.core.objects.User;
import com.yh.chat.core.objects.UserImpl;



public class ChatReader {
	private SharedFolder projectName;
	public ChatReader(SharedFolder projName){
		projectName=projName;
	}
	List<User> users = null;
	
	public List<User> getUsers()
	{
		return users;
	}
	
	public List<SortableByDate> compileMessages() throws FileNotFoundException, IOException {
		
		File workingDir=projectName.getChatFolder();
		ArrayList<File> files = new ArrayList<File>();
		for(File f: workingDir.listFiles()){
			if(f.toString().contains(".json"))
			{
				files.add(f);
			}
		}
		users = new ArrayList<User>(files.size());
		List<SortableByDate> simpleMessageList = new ArrayList<SortableByDate>();
		
		for(File f : files){
			BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream (f),StandardCharsets.UTF_8));
			JsonReader jr =  Json.createReader(read);
			JsonObject jObj = jr.readObject();
			String name = jObj.getString("displayName");
			
			users.add(new UserImpl(Long.parseLong(f.getName().replace(".json", "")),name));
			
			JsonArray ja = (JsonArray)jObj.get("messages");
			
			for(int i=0;i<ja.size();i++){
				JsonObject singleMessage = ja.getJsonObject(i);
				
				String s = singleMessage.getString("date");
				String mString = singleMessage.getString("message");
				Message m = new MessageImpl(name, mString, new DateTime(s));
				simpleMessageList.add(m);
			}
			read.close();
		}
		return simpleMessageList;
	}
}
