package com.yh.chat.core.init;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.yh.chat.gui.main.Frame;

public class InitiateConnection {
	 public static DbxClient authenticate() throws IOException, DbxException {
	        
	        final String APP_KEY = "d7ognanydpqcw4d";
	        final String APP_SECRET = "z1vz2m4z59iar7y";
	        DbxClient client;
	        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
	        DbxRequestConfig config = new DbxRequestConfig("YHacksChatDrop/1.0", Locale.getDefault().toString());
	        if(!(new File(".chatSettings/authCode.txt")).exists()){
	        
	        	DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
	        	String authorizeUrl = webAuth.start();
	        	//Make GUI for authorization
	        	
	        	Frame frame = new Frame(Frame.FrameOpts.Empty);
	        	String code = frame.newUser(authorizeUrl);
	        	
	        	DbxAuthFinish authFinish = webAuth.finish(code);
	        	File authCodeFolder= new File(".chatSettings");
	        	boolean worked = authCodeFolder.mkdir();
	        	File authCode = new File(authCodeFolder, "authCode.txt");
	        	BufferedWriter bw = new BufferedWriter(new FileWriter(authCode));
	        	bw.write(authFinish.accessToken);
	        	bw.close();
	        	client = new DbxClient(config, authFinish.accessToken);
	        }
	        else {
	        	System.out.println("Already authenticated");
	        	BufferedReader br = new BufferedReader(new FileReader(new File(".chatSettings/authCode.txt")));
	        	String s = br.readLine();
	        	client = new DbxClient(config, s);
	        	br.close();
	        
	        }
	        return client;
	        
	 }
	/* public static void main(String[] args) throws Exception{
		 authenticate();
	}*/
	 
}
