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
	
	public static CoreMain get()
	{
		return core.get();
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
