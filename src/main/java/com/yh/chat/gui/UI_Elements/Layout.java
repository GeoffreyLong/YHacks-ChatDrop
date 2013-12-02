package com.yh.chat.gui.UI_Elements;

import java.awt.Rectangle;

import com.yh.chat.gui.values.WindowSizes;

public class Layout {
	private static int frameWidth;
	private static int frameHeight;
	private static int menuLeftWidth;
	private static int menuRightWidth;
	private static int entryPanelHeight;
	private static int chatPanelHeight;
	private static int welcomePanelHeight;
	private static int messagePanelHeight;
	
	private static Rectangle welcomePanel;
	
	public Layout(int frameWidth, int frameHeight){
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		
		init();
	}
	private void init(){
		menuLeftWidth = frameWidth/4;
		menuRightWidth = 3*frameWidth/4;
		welcomePanelHeight = frameHeight/13;
		chatPanelHeight = 12*frameHeight/13;
		entryPanelHeight = chatPanelHeight/3;
		messagePanelHeight = 2*chatPanelHeight/3;
	}
	
	public static int getFrameWidth(){
		return frameWidth;
	}
	public static int getFrameHeight(){
		return frameHeight;
	}
	public static int getMenuLeftWidth(){
		return menuLeftWidth;
	}
	public static int getMenuRightWidth(){
		return menuRightWidth;
	}
	public static int getChatPanelHeight(){
		return chatPanelHeight;
	}
	public static int getWelcomePanelHeight(){
		return welcomePanelHeight;
	}
	public static int getEntryPanelHeight(){
		return entryPanelHeight;
	}
	public static int getMessagePanelHeight(){
		return messagePanelHeight;
	}
	
	
	public static Rectangle getWelcome(){
		int x = menuLeftWidth + 5;
		int y = 5;
		int width = menuRightWidth - 10;
		int height = welcomePanelHeight;
		
		Rectangle rect = new Rectangle(x, y, width, height);
		return rect;
	}
	public static Rectangle getEmptyChat(){
		int x = menuLeftWidth+menuRightWidth/2 - 175;
		int y = frameHeight/2 - 50;
		int width = 330;
		int height = 100;
		
		Rectangle rect = new Rectangle(x, y, width, height);
		return rect;
	}
	public static Rectangle getEntryPanel(){
		int x = menuLeftWidth+10;
		int y = welcomePanelHeight + messagePanelHeight + 10;
		int width = menuRightWidth - 20;
		int height = entryPanelHeight - 20;
		
		Rectangle rect = new Rectangle(x, y, width, height);
		return rect;
	}
	public static Rectangle getSearchPanel(){
		int x = 5;
		int y = 5;
		int width = menuLeftWidth-5;
		int height = frameHeight - 10;
		
		Rectangle rect = new Rectangle(x, y, width, height);
		return rect;
	}
	public static Rectangle getChatPanel(){
		int x = menuLeftWidth + 5;
		int y = welcomePanelHeight + 10;
		int width = menuRightWidth - 10;
		int height = chatPanelHeight - 15;
		
		Rectangle rect = new Rectangle(x, y, width, height);
		return rect;
	}
}
