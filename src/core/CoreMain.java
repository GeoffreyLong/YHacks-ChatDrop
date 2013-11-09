package core;

import java.io.File;

import core.init.UserTemp;

public class CoreMain {
	UserTemp owner;
	File rootDirectory;
	
	private static ThreadLocal<CoreMain> core = new ThreadLocal<CoreMain>()
	{
		@Override
		protected CoreMain initialValue()
		{
			return new CoreMain();
		}
	};
	
	public CoreMain get()
	{
		return core.get();
	}
	
	public File getRootDirectory()
	{
		return rootDirectory;
	}
	
	
}
