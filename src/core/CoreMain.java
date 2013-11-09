package core;

import java.io.File;

import com.dropbox.core.DbxClient;

import core.objects.User;

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
		return owner;
	}
	
	
}
