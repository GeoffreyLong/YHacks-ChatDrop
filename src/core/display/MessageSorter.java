package core.display;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.LocalDate;

import core.objects.Message;
import core.objects.SharedFolder;
import core.objects.User;

public class MessageSorter {
	
	List<Message> allMessages;
	List<User> users;
	
	
	public MessageSorter(SharedFolder folder)
	{
		folder.instantiate();
		instantiate(folder.getMessages());
		users = folder.getUsers();
	}
	

	private class MessageComparitor implements Comparator<Message>
	{
		public int compare(Message o1, Message o2) {
			
			LocalDate date1 = new LocalDate(o1.getDate());
			LocalDate date2 = new LocalDate(o2.getDate());
			return date1.compareTo(date2);
		}

		
	}
	
	public void instantiate(List<List<Message>> messages)
	{
		List<Message> allMessagesSorted = new ArrayList<Message>();
		for(List<Message> list : messages)
		{
			allMessagesSorted.addAll(list);
		}
		Collections.sort(allMessagesSorted, new MessageComparitor());
		allMessages = allMessagesSorted;
	}
	
	public List<Message> getMessages(int amount)
	{
		return allMessages.subList(0, amount > allMessages.size()? allMessages.size() : amount );

		
	}
	
	
}
