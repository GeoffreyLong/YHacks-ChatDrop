package core.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;

import org.joda.time.DateTime;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;

import core.objects.SharedFolder;

public class ChatWriter {
	private SharedFolder projectName;
	private DbxClient user;
	
	public ChatWriter(SharedFolder name, DbxClient client){
		projectName=name;
		user=client;
	}
	public void writeTo(String message) throws DbxException, FileNotFoundException, IOException{
		File workingDir= projectName.getChatFolder();
		long userId = user.getAccountInfo().userId;
		File writeTo = new File(workingDir,""+userId+".json");
		BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream (writeTo),StandardCharsets.UTF_8));
		DateTime cal = new DateTime();
		JsonReader jRead = Json.createReader(read);
		JsonObject jobj = jRead.readObject();
		JsonArray ja= (JsonArray) jobj.get("messages");
		//Store cal as cal, not as string.
		JsonValue next =   Json.createObjectBuilder().add("message", message).add("date",cal.toString()).build();
		//is the cast ok?
		ja.add(next);
		jRead.close();
		BufferedWriter messageWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream (writeTo),StandardCharsets.UTF_8));
		JsonWriter jWrite = Json.createWriter(messageWrite);
		jobj.put("messages", ja);
		jWrite.write(jobj);
		jWrite.close();
		
		messageWrite.close();
	}
}
