package core.server;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxDelta;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;

import core.init.InitiateConnection;
import core.objects.OwnerImpl;
import core.objects.SharedFolder;

public class ChatListener {
	private SharedFolder directory;
	private OwnerImpl owner;
	
	public ChatListener(SharedFolder wkd, OwnerImpl own){
		directory =wkd;
		owner=own;
	}
	
	public  void checkUpdates() throws MalformedURLException, IOException, DbxException,InterruptedException{
		int i=0;
		String cursor=null;
		DbxClient c = InitiateConnection.authenticate();
		owner = new OwnerImpl(c);
		DbxDelta<DbxEntry> dd= c.getDelta(cursor);
		cursor=dd.cursor;
		System.out.println(cursor);
		
		//dd= c.getDelta(cursor);
	//	cursor=dd.cursor;
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
