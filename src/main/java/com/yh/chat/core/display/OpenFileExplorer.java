package com.yh.chat.core.display;

import java.io.File;
import java.io.IOException;

public class OpenFileExplorer {

	public static void openAtFile(File loc)
	{
		try
		{
			java.awt.Desktop.getDesktop().open(loc.getParentFile());
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String... args)
	{
		File file = new File(System.getProperty("user.dir"));
		openAtFile(file);
	}
}
