package com.yh.chat.core.objects;

import org.joda.time.DateTime;

public class FileChange implements SortableByDate {
	
	DateTime date;
	FileOp op;
	
	public enum FileOp
	{
		Create,
		Delete,
		Modify
	}
	
	public FileChange()
	{
		
	}

	public DateTime getDate() 
	{
		return date;
	}
	public FileOp getOp()
	{
		return op;
	}

}