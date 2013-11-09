package core.init;

import java.io.File;
import java.io.Serializable;

import core.exceptions.NotAFolderException;
import core.objects.SharedFolder;

public class InitConversation implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6656076942650832481L;

	public InitConversation(SharedFolder folder)
	{
		File topLevel = folder.getTopLevel();
		if(topLevel.exists() && topLevel.isFile())
			throw new NotAFolderException();
		else if(!topLevel.exists())
		{
			topLevel.mkdirs();
		}
		
		
	}
}
