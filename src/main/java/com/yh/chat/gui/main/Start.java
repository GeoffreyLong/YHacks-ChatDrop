package com.yh.chat.gui.main;

import com.yh.chat.core.search.Search;

public class Start {
	public static void main(String[] args){
		Frame frame;
		try{
			frame = new Frame();
			Search obj = new Search();
			frame.initEmptyChat();
			frame.initSearch(obj.getCurrentConversations());
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("GUI could not instantiate");
		}
	}
	public void startSearch(){
		
	}
	public void startChat(){
		
	}
}
