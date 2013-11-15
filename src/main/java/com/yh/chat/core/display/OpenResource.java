package com.yh.chat.core.display;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class OpenResource {

	public static void open(File loc)
	{
		try
		{
			Desktop.getDesktop().open(loc.getParentFile());
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void open(URI loc)
	{
		try 
		{
			Desktop.getDesktop().browse(loc);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String... args)
	{
		File file = new File(System.getProperty("user.dir"));
		open(file);
	}
}
