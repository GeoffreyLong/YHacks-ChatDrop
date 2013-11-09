package core.methods;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.dropbox.core.DbxException;

import core.init.InitConversation;
import core.init.InitiateConnection;
import core.io.ChatReader;
import core.io.ChatWriter;
import core.objects.SharedFolder;
import core.objects.SharedFolderImpl;

public class TestIO {
	public static void main(String[] args) throws DbxException, IOException, FileNotFoundException{
		File f = new File("HelloWorld");
		SharedFolder s = new SharedFolderImpl(f);
		InitConversation ic = new InitConversation(s);
		ChatReader cr = new ChatReader(s);
		ChatWriter cw = new ChatWriter(s,InitiateConnection.authenticate());
		cw.writeTo("Hello World");
		cr.compileMessages();
	}
}
