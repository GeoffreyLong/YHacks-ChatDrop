package core.display;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.joda.time.DateTime;

import core.objects.Message;
import core.objects.MessageImpl;

public class MessageSorter {
	
	List<List<Message>> allMessages;
	List<Integer> arrayPos;
	
	
	public MessageSorter(List<List<Message>> messages)
	{
		this.allMessages = messages;
		arrayPos = new ArrayList<Integer>(allMessages.size());
		for(int i = 0; i < allMessages.size(); i++)
			arrayPos.set(i, allMessages.get(i).size()-1);
	}
	
	public ArrayList<Message> getMessages(int amount)
	{
		ArrayList<Message> messagesToReturn = new ArrayList<Message>(amount);
		int users = allMessages.size();
		
		for(int i = 0; i < amount ; i++)
		{
			int mostRecent = -1;
			Message toReturn = new MessageImpl();
			for(int j = 0; j < users; j++)
			{
				if(arrayPos.get(j) != -1)
				{
					if(mostRecent == -1)
					{
						mostRecent = j;
						toReturn = allMessages.get(j).get(arrayPos.get(j));
					}
					else
					{
						Message toTest = allMessages.get(j).get(arrayPos.get(j));
						if(toReturn.getDate().isAfter(toTest.getDate()))
						{
							toReturn = toTest;
							mostRecent = j;
						}
					}
				}
			}
			if(mostRecent == -1) 
				return messagesToReturn;
			else
			{
				messagesToReturn.add(toReturn);
				arrayPos.set(mostRecent, arrayPos.get(mostRecent) - 1);
			}
		}
		
		return messagesToReturn;
		
	}
	
	
}
