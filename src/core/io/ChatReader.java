package core.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.joda.time.DateTime;

import core.objects.Message;
import core.objects.MessageImpl;
import core.objects.SharedFolder;



public class ChatReader {
	private SharedFolder projectName;
	public Map<String,ArrayList<Message>> compileMessages() throws FileNotFoundException, IOException {
		File workingDir=projectName.getChatFolder();
		ArrayList<File> files = new ArrayList<File>();
		for(File f: workingDir.listFiles()){
			if(f.toString().contains(".json")){
				//fix so no collisions with users own json files.
				files.add(f);
			}
		}
		Map<String,ArrayList<Message>> compiled= new HashMap<String,ArrayList<Message>>();
		for(File f : files){
			BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream (f),StandardCharsets.UTF_8));
			JsonReader jr =  Json.createReader(read);
			JsonObject jObj = jr.readObject();
			String name = jObj.get("displayName").toString();
			JsonArray ja = (JsonArray)jObj.get("messages");
			ArrayList<Message> messages = new ArrayList<Message>();
			for(int i=0;i<ja.size();i++){
				JsonObject singleMessage = ja.getJsonObject(i);
				Message m = new MessageImpl(singleMessage.get("message").toString(),new DateTime(singleMessage.get("date").toString()));
				 messages.add(m);
			}
			compiled.put(name,messages);
			read.close();
		}
		return compiled;
	}
}
