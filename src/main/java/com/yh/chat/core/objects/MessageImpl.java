package com.yh.chat.core.objects;

import org.joda.time.DateTime;

import com.yh.chat.core.display.DateFormat;

public class MessageImpl implements Message{
	private String message;
	private DateTime date;
	private String name;
	public MessageImpl(String theName, String s,DateTime c){
		name=theName;
		date = c;
		message=s;
	}
	public String getDateDisplay()
	{
		return DateFormat.pretty(date);
	}
	//TODO: Fix these
	public String getMessageText(){ return message;}
	public String getUserName(){return name;}
	public DateTime getDate(){return date;}

}
