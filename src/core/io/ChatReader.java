package core.io;

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

import core.objects.Message;
import core.objects.MessageImpl;
import core.objects.SharedFolder;
import core.objects.User;
import core.objects.UserImpl;



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
	
	public List<List<Message>> compileMessages() throws FileNotFoundException, IOException {
		
		File workingDir=projectName.getChatFolder();
		ArrayList<File> files = new ArrayList<File>();
		for(File f: workingDir.listFiles()){
			if(f.toString().contains(".json"))
			{
				files.add(f);
			}
		}
		users = new ArrayList<User>(files.size());
		List<List<Message>> compiled= new ArrayList<List<Message>>();
		for(File f : files){
			BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream (f),StandardCharsets.UTF_8));
			JsonReader jr =  Json.createReader(read);
			JsonObject jObj = jr.readObject();
			String name = jObj.get("displayName").toString();
			
			users.add(new UserImpl(Long.parseLong(f.getName().replace(".json", "")),name));
			
			JsonArray ja = (JsonArray)jObj.get("messages");
			ArrayList<Message> messages = new ArrayList<Message>();
			for(int i=0;i<ja.size();i++){
				JsonObject singleMessage = ja.getJsonObject(i);
				
				String s = singleMessage.getString("date");//.toString().replaceAll("\"", "");
				System.out.println(s);
				Message m = new MessageImpl(name,singleMessage.getString("message"),new DateTime(s));
				 messages.add(m);
			}
			compiled.add(messages);
			read.close();
		}
		return compiled;
	}
}
