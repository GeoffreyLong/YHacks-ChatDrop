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
import com.yh.chat.core.objects.SortableByDate;
import com.yh.chat.core.objects.User;

public class MessageSorter {
	
	List<SortableByDate> allMessages;
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
	

	private class DateComparitor implements Comparator<SortableByDate>
	{
		public int compare(SortableByDate o1, SortableByDate o2) 
		{
			LocalDateTime date1 = new LocalDateTime(o1.getDate());
			LocalDateTime date2 = new LocalDateTime(o2.getDate());
			return date1.compareTo(date2);
		}
	}
	
	
	
	public void instantiate(List<SortableByDate> messages)
	{
		List<SortableByDate> mes = new ArrayList<SortableByDate>(messages);
		Collections.sort(mes, new DateComparitor());
		allMessages = mes;
	}
	
	public List<SortableByDate> getMessages(int amount)
	{
		if(amount > allMessages.size()) return allMessages;
		else
		{
			return allMessages.subList(allMessages.size() - amount,allMessages.size());
		}
	}
	
	
	public static void main(String... args)
	{
		DateTime a = new DateTime("2013-11-13T20:17:27.285-05:00");
		DateTime b = new DateTime("2013-11-13T20:17:22.397-05:00");
		DateTime c = new DateTime("2013-11-13T22:22:00.061-05:00");
		
		Message aM = new MessageImpl("joel", "Should be mid.", a);
		Message bM = new MessageImpl("notJoel", "Should be first.", b);
		Message cM = new MessageImpl("bob", "Should be last", c);
		
		List<SortableByDate> mes = new ArrayList<SortableByDate>();
		
		mes.add(aM);
		mes.add(bM);
		mes.add(cM);
		
		MessageSorter ms = new MessageSorter();
		
		ms.instantiate(mes);
		for(SortableByDate sort : ms.getMessages(20))
		{
			if(sort instanceof Message)
			{
				System.out.println(((Message) sort).getMessageText());
			}
		}
	}
	
	
}
