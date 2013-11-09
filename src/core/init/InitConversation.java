package core.init;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;

import core.CoreMain;
import core.exceptions.NotAFolderException;
import core.objects.SharedFolder;
import core.objects.User;

public class InitConversation implements Serializable
{
	/**
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
				logFile.createNewFile();
				BufferedWriter write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream (logFile)));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}
}
