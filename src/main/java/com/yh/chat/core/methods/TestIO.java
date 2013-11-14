package com.yh.chat.core.methods;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.dropbox.core.DbxException;

import com.yh.chat.core.init.InitConversation;
import com.yh.chat.core.init.InitiateConnection;
import com.yh.chat.core.io.ChatReader;
import com.yh.chat.core.io.ChatWriter;
import com.yh.chat.core.objects.SharedFolder;
import com.yh.chat.core.objects.SharedFolderImpl;

public class TestIO {
	public static void main(String[] args) throws DbxException, IOException, FileNotFoundException{
		File f = new File("HelloWorld");
		SharedFolder s = new SharedFolderImpl(f);
		InitConversation.create(s);
		ChatReader cr = new ChatReader(s);
		ChatWriter cw = new ChatWriter(s,InitiateConnection.authenticate());
		cw.writeTo("Hello World");
		cr.compileMessages();
	}
}
