package core.search;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
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
	DirectoryFilter filter = new DirectoryFilter();
	
	public ArrayList<File> getAllSubdirectories(File file)
	{
		ArrayList<File> subs = new ArrayList<File>();
		String[] subDirectories = file.list(filter);
		
		if(subDirectories == null) return null;
		
		for(String sd : subDirectories)
		{
			File temp = new File(file, sd);
			subs.add(temp);
			ArrayList<File> currSubs = getAllSubdirectories(temp);
			if(currSubs != null)
			{
				subs.addAll(currSubs);
			}
		}
		return subs;
	}
	
	public List<File> getAllSubdirectoriesOfRoot()
	{
		ArrayList<File> directories = new ArrayList<File>();
		CoreMain main = CoreMain.get();
		File root = main.getRootDirectory();
		directories.addAll(getAllSubdirectories(root));	
		return directories;
	}
	
	public List<SharedFolder> getCurrentConversations()
	{
		CoreMain main = CoreMain.get();
		File root = main.getRootDirectory();
		
		root.list(filter);
		
		
		
		
		
		return null;
	}
	
	public static void main(String[] args)
	{
		Search sMain = new Search();
		List<File> subs = sMain.getAllSubdirectoriesOfRoot();
		for(File f : subs)
		{
			System.out.println(f.getAbsolutePath());
		}
	}
}
