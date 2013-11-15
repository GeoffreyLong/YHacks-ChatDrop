package com.yh.chat.core.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxDelta;
import com.dropbox.core.DbxDeltaC;
import com.dropbox.core.DbxDeltaC.Entry;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.util.Collector;
import com.yh.chat.core.init.InitiateConnection;
import com.yh.chat.core.objects.FileChange;
import com.yh.chat.core.objects.OwnerImpl;
import com.yh.chat.core.objects.SharedFolder;

public class ChatListener {
	private SharedFolder directory;
	private OwnerImpl owner;
	
	public ChatListener(SharedFolder wkd, OwnerImpl own){
		directory =wkd;
		owner=own;
	}
	
	public DbxDelta<DbxEntry> getDbx(DbxClient c, String cursor) throws DbxException
	{
		try
		{
			return c.getDelta(cursor);
		}
		catch(DbxException e)
		{
			return c.getDelta(null);
		}
	}
	
	public void checkUpdates() throws MalformedURLException, IOException, DbxException,InterruptedException{
		
		DbxClient c = InitiateConnection.authenticate();
		owner = new OwnerImpl(c);
		
		String cursor = null;
		File chat = new File(".chatSettings/cursor");
		
		if(chat.exists())
		{
        	BufferedReader br = new BufferedReader(new FileReader(chat));
        	cursor = br.readLine();
        	br.close();
		}
		
		DbxDelta<DbxEntry> dd = getDbx(c, cursor);
		
		while(dd.hasMore)
		{
			c.getDeltaC(cursor, new Collector<DbxDeltaC.Entry<DbxEntry>,List<FileChange>>(){

				@Override
				public List<FileChange> finish() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void add(Entry<DbxEntry> element) {
					
				}});
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(".chatSettings/cursor"));
        bw.write(dd.cursor);
        bw.close();
        
		
		
		
		cursor=dd.cursor;
		System.out.println(cursor);
		
		URL url = new URL("https://api-notify.dropbox.com/1/longpoll_delta");
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();

		connection.setRequestMethod("GET");
		
		connection.setDoInput(true);
	    connection.setDoOutput(true);
		
		connection.addRequestProperty("cursor", cursor);
		connection.addRequestProperty("timeout", ""+120);
		
		//System.out.println(connection.getRequestProperty("cursor"));
		
		//connection.connect();
		
		String s = ""+connection.getResponseMessage();
		System.out.println(s);

	/*	while(true){
		
		
		
		DbxDelta<DbxEntry> dd= c.getDelta(cursor);
		//for (DbxEntry d : dd){
			
			if (i>=2 && !dd.entries.isEmpty() ){
				Object s = dd.entries.get(0);
				
					System.out.println(s.toString());
					
				
				break;
			}
			cursor=dd.cursor;
			
		//}
			
		//DataInputStream ir = new DataInputStream(connection.getInputStream());
		//System.out.println(ir.readUTF());
		i ++;
		}*/
		 
	}
	public static void main(String[] args)throws MalformedURLException, IOException, DbxException, InterruptedException{
		ChatListener cl = new ChatListener(null,null);
		cl.checkUpdates();
	}
}
