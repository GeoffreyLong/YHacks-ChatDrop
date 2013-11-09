package core.display;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import core.objects.Message;
import core.objects.SharedFolder;
import core.objects.User;

public class MessageSorter {
	
	List<List<Message>> allMessages;
	List<User> users;
	List<Integer> arrayPos;
	
	
	public MessageSorter(SharedFolder folder)
	{
		folder.instantiate();
		instantiate(folder.getMessages());
		folder.getUsers();
	}
	
	public void instantiate(List<List<Message>> messages)
	{
		this.allMessages = messages;
		arrayPos = new ArrayList<Integer>(allMessages.size());
		for(int i = 0; i < allMessages.size(); i++)
			arrayPos.add(i, allMessages.get(i).size()-1);
	}
	
	public ArrayList<Message> getMessages(int amount)
	{
		ArrayList<Message> messagesToReturn = new ArrayList<Message>(amount);
		int users = allMessages.size();
		
		for(int i = 0; i < amount ; i++)
		{
			int mostRecent = -1;
			Message toReturn = null;
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
