package core.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;

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
		File writeTo = new File(""+userId+".json");
		BufferedWriter messageWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream (writeTo)));
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		
		
		messageWrite.close();
	}
}
