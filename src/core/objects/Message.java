package core.objects;

import org.joda.time.DateTime;

public interface Message {
	public String getUserName();
	public DateTime getDate();
	public String getMessageText();
}
