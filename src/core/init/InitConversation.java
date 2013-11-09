package core.init;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.json.JsonWriter;

import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;

import com.fasterxml.jackson.core.JsonFactory;

import core.CoreMain;
import core.exceptions.NotAFolderException;
import core.objects.SharedFolder;
import core.objects.User;

public class InitConversation implements Serializable
{
	/** InitConversation
	 * 
	 */
	private static final long serialVersionUID = 6656076942650832481L;

	public InitConversation(SharedFolder folder)
	{
		File chat;
		if((chat = folder.getChatFolder()) != null)
			if(chat.isDirectory())
				return;
		
		File topLevel = folder.getTopLevel();
		if(topLevel.exists() && topLevel.isFile())
			throw new NotAFolderException("Base");
		else if(!topLevel.exists())
			topLevel.mkdirs();
		
		chat = new File(topLevel, ".chat");
		if(chat.exists() && chat.isFile())
			throw new NotAFolderException("Chat");
		else if(!topLevel.exists())
			topLevel.mkdir();
		
		CoreMain main = CoreMain.get();
		
		User owner = main.getOwner();
		
		Long id = owner.getUserId();
		
		File logFile = new File(chat, id.toString() + ".json");
		
		if(logFile.exists())
			return;
		else
		{
			try {
				DateTime date = new DateTime();
				
				JsonObjectBuilder top = Json.createObjectBuilder();
				top.add("displayName", owner.getUserDisplayName());
				top.add("joinDate", date.toString());
				JsonArrayBuilder messageArray = Json.createArrayBuilder();
				top.add("messages", messageArray);

				BufferedWriter write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile), StandardCharsets.UTF_8));
				JsonWriter jsonWrite = Json.createWriter(write);
				
				jsonWrite.writeObject(top.build());				
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}
}
