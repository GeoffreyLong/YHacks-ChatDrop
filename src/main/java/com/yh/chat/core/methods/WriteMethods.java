package com.yh.chat.core.methods;

import org.joda.time.DateTime;

public class WriteMethods {

	
	
	
	public static void main(String[] args)
	{
		
		DateTime a = new DateTime();
		System.out.println(a);
		DateTime b = new DateTime(a.toString());
		System.out.println(b);
	}
}
