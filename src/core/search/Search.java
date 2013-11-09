package core.search;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dropbox.core.DbxException;

import core.CoreMain;
import core.init.InitConversation;
import core.objects.SharedFolder;
import core.objects.SharedFolderImpl;

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
			if(sd.equals(".git")) continue;
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
		List<File> allFolders = getAllSubdirectoriesOfRoot();
		List<SharedFolder> chatFolders = new ArrayList<SharedFolder>();
		for(File file : allFolders)
		{
			if(file.getName().equals(".chat"))
			{
				chatFolders.add(new SharedFolderImpl(file.getParentFile()));
			}
		}
		for(SharedFolder chat : chatFolders)
		{
			try {
				InitConversation.create(chat);
			} catch (DbxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return chatFolders;
	}

	public static void main(String[] args) throws IOException
	{
		Search sMain = new Search();
		List<SharedFolder> subs = sMain.getCurrentConversations();
		for(SharedFolder f : subs)
		{
			
		}
	}
}
