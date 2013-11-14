package com.yh.chat.core.init;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import org.joda.time.DateTime;

import com.dropbox.core.DbxException;

import com.yh.chat.core.CoreMain;
import com.yh.chat.core.exceptions.NotAFolderException;
import com.yh.chat.core.objects.SharedFolder;
import com.yh.chat.core.objects.User;

public class InitConversation implements Serializable
{
	/** InitConversation
	 * 
	 */
	private static final long serialVersionUID = 6656076942650832481L;

	public static void create(SharedFolder folder) throws DbxException
	{
		File chat;
		//if((chat = folder.getChatFolder()) != null)
			//if(chat.isDirectory() )
			//	return;
		
		File topLevel = folder.getTopLevel();
		if(topLevel.exists() && topLevel.isFile())
			throw new NotAFolderException("Base");
		else if(!topLevel.exists())
			topLevel.mkdirs();
		
		chat = new File(topLevel, ".chat");
		if(chat.exists() && chat.isFile())
			throw new NotAFolderException("Chat");
		else if(!chat.exists())
			chat.mkdir();
		folder.setChatFolder(chat);
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
				jsonWrite.close();
				write.close();
				
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}
}
