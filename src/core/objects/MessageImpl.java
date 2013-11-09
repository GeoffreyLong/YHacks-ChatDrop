package core.objects;

import org.joda.time.DateTime;

public class MessageImpl implements Message{
	private String message;
	private DateTime date;
	private String name;
	public MessageImpl(String s,Calendar c){
		date = c;
		message=s;
	}
	//Fix these
	public String getMessageText(){ return message;}
	public String getUserName(){return name;}
	public DateTime getDate(){return date;}

}
