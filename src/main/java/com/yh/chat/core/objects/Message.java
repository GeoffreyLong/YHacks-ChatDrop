package com.yh.chat.core.objects;

import org.joda.time.DateTime;

public interface Message extends SortableByDate {
	public String getUserName();
	public DateTime getDate();
	public String getDateDisplay();
	public String getMessageText();
}
