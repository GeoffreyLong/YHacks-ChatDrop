package com.yh.chat.core.display;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.LocalDateTime;

public class DateFormat {

	public static String pretty(DateTime date) {
		DateTime currD = new DateTime();
		LocalDateTime curr = new LocalDateTime(currD);
		LocalDateTime toPrint = new LocalDateTime(date);

		if(curr.getYear() != toPrint.getYear())
			return toPrint.toString("E M dd',' yyyy h':'mm a");
		else if(curr.getDayOfYear() == toPrint.getDayOfYear() - 1)
			return toPrint.toString("'Yesterday, 'h':'mm a");
		else if(curr.getDayOfYear() == toPrint.getDayOfYear())
		{
			Interval timeElapsed = new Interval(date, currD);
			Duration a = timeElapsed.toDuration();
			if(a.getStandardHours() >= 1)
				return toPrint.toString("h':'mm a");
			else if(a.getStandardMinutes() >= 3)
				return (new StringBuilder()).append(a.getStandardMinutes()).append(" Minutes ago").toString();
			else if(a.getStandardMinutes() == 2)
				return "Two minutes ago";
			else if(a.getStandardMinutes() == 1)
				return "One minute ago";
			else if(a.getStandardSeconds() > 10)
				return "Less than a minute ago";
			else
				return "A few seconds ago";
		}
		else
			return toPrint.toString("E M dd',' h':'mm a");
	}

}
