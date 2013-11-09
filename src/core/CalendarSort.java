package core;

import java.util.Calendar;

class CalendarSort{
	public static Calendar[] sortMessages (Calendar messages[][]){
		int i = 0;
		Calendar earliestDate;
		Calendar tenEarliest[] = new Calendar[10];
	
		int counter[] = new int[messages.length];
		//for (i = 0;i<numberOfUsers;i++){
		//	counter[i] = 0;
		//}
		
		for (i = 0; i<10; i++){
			earliestDate = messages[0][counter[0]];
			for (int user = 1; i<messages.length; i++){
				if (messages[user][counter[user]].compareTo(earliestDate) > 0){
					earliestDate = messages[user][counter[user]];
					counter[user]++;
				}
			}
			tenEarliest[i] = earliestDate;
		}
		return tenEarliest;
	}
}