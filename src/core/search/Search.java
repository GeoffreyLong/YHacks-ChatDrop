package core.search;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.List;

import core.CoreMain;
import core.objects.SharedFolder;

public class Search {
	
	private class DirectoryFilter implements FilenameFilter
	{
		public boolean accept(File dir, String name) {
			return (new File(dir, name)).isDirectory();
		}
		
	}
	
	public List<SharedFolder> getCurrentConversations()
	{
		CoreMain main = CoreMain.get();
		File root = main.getRootDirectory();
		
		root.list(new DirectoryFilter());
		
		
		
		
		
		return null;
	}
}
