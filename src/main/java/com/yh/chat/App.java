package com.yh.chat;

import com.yh.chat.core.search.Search;
import com.yh.chat.gui.main.Frame;

/**
 * Chat app.
 *
 */
public class App 
{
	public static void main(String[] args){
		Frame frame;
		try{
			frame = new Frame();
			Search obj = new Search();
			Frame.initEmptyChat();
			Frame.initSearch(obj.getCurrentConversations());
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("GUI could not instantiate");
		}
	}
}
