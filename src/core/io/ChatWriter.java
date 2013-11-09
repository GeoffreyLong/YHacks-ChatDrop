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
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

import org.joda.time.DateTime;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;

import core.CoreMain;
import core.objects.Message;
import core.objects.SharedFolder;

public class ChatWriter {
	private SharedFolder projectName;
	private DbxClient user;
	
	public ChatWriter(SharedFolder name, DbxClient client){
		projectName=name;
		user=client;
	}
	
	public ChatWriter(SharedFolder name, Message message)
	{
		this(name, CoreMain.get().getOwner().getDbxClient());
		this.writeTo(message.getMessageText());
	}
	public void writeTo(String message) throws DbxException, FileNotFoundException, IOException{
		File workingDir= projectName.getChatFolder();
		long userId = user.getAccountInfo().userId;
		File writeTo = new File(workingDir,""+userId+".json");
	
		BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream (writeTo),StandardCharsets.UTF_8));
		DateTime cal = new DateTime();
		JsonReader jRead = Json.createReader(read);
		JsonObject jobj = jRead.readObject();
		JsonArray ja=  jobj.getJsonArray("messages");
		//JsonObject header = Json.createObjectBuilder().add("displayName",jobj.get("displayName")).build();
		//JsonObject join = Json.createObjectBuilder().add("joinDate",jobj.get("joinDate")).build();
		JsonObject next =   Json.createObjectBuilder().add("message", message).add("date",cal.toString()).build();
		JsonArrayBuilder builder = Json.createArrayBuilder();
		
		for(int i=0;i<ja.size();i++){
			builder =builder.add(ja.get(i));
			
		}
		builder = builder.add(next);
		JsonArray working = builder.build();
		
		
		//ja.add(next);
		jRead.close();
		BufferedWriter messageWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream (writeTo),StandardCharsets.UTF_8));
		JsonWriter jWrite = Json.createWriter(messageWrite);
		//add("displayName",jobj.getJsonObject("displayName").toString()).add("joinDate",jobj.getJsonObject("joinDate").toString()).
		JsonObject jb = Json.createObjectBuilder().add("displayName",jobj.get("displayName")).add("joinDate",jobj.get("joinDate")).add("messages", working).build();
		//Authenticating twice, file deletion bug?
		jWrite.write(jb);
		jWrite.close();
		
		messageWrite.close();
	}
}
