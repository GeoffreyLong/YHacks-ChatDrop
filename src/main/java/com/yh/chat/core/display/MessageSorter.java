package com.yh.chat.core.display;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import com.yh.chat.core.objects.Message;
import com.yh.chat.core.objects.MessageImpl;
import com.yh.chat.core.objects.SharedFolder;
import com.yh.chat.core.objects.User;

public class MessageSorter {
	
	List<Message> allMessages;
	List<User> users;
	
	
	public MessageSorter(SharedFolder folder)
	{
		folder.instantiate();
		instantiate(folder.getMessages());
		users = folder.getUsers();
	}
	
	public MessageSorter()
	{
		
	}
	

	private class MessageComparitor implements Comparator<Message>
	{
		public int compare(Message o1, Message o2) 
		{
			LocalDateTime date1 = new LocalDateTime(o1.getDate());
			LocalDateTime date2 = new LocalDateTime(o2.getDate());
			return date1.compareTo(date2);
		}
	}
	
	
	
	public void instantiate(List<Message> messages)
	{
		List<Message> mes = new ArrayList<Message>(messages);
		Collections.sort(mes, new MessageComparitor());
		allMessages = mes;
	}
	
	public List<Message> getMessages(int amount)
	{
		return allMessages.subList(0, amount > allMessages.size()? allMessages.size() : amount );
	}
	
	
	public static void main(String... args)
	{
		DateTime a = new DateTime("2013-11-13T20:17:27.285-05:00");
		DateTime b = new DateTime("2013-11-13T20:17:22.397-05:00");
		DateTime c = new DateTime("2013-11-13T22:22:00.061-05:00");
		
		Message aM = new MessageImpl("joel", "Should be mid.", a);
		Message bM = new MessageImpl("notJoel", "Should be first.", b);
		Message cM = new MessageImpl("bob", "Should be last", c);
		
		ArrayList<Message> mes = new ArrayList<Message>();
		
		mes.add(aM);
		mes.add(bM);
		mes.add(cM);
		
		MessageSorter ms = new MessageSorter();
		
		ms.instantiate(mes);
		for(Message sort : ms.getMessages(20))
		{
			System.out.println(sort.getMessageText());
		}
	}
	
	
}
