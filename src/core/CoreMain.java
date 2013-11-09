package core;

import java.io.File;

import com.dropbox.core.DbxClient;

import core.init.InitiateConnection;
import core.objects.User;
import core.objects.UserImpl;

public class CoreMain {
	User owner;
	File rootDirectory;
	DbxClient user;
	
	private static ThreadLocal<CoreMain> core = new ThreadLocal<CoreMain>()
	{
		@Override
		protected CoreMain initialValue()
		{
			return new CoreMain();
		}
	};
	
	public void initialize()
	{
		rootDirectory = new File(System.getProperty("user.dir"));
		try{
		user = InitiateConnection.authenticate();
		}
		catch (Exception e) {}
	}
	
	public static CoreMain get()
	{
		CoreMain main = core.get();
		main.initialize();
		return main;
	}
	
	public File getRootDirectory()
	{
		return rootDirectory;
	}
	
	public User getOwner()
	{
		 owner=new UserImpl(user);
		 return owner;
	}
	
	
}
