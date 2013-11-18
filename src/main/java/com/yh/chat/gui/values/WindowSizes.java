package com.yh.chat.gui.values;

public class WindowSizes {
	private static int xDim = 1100;
	private static int yDim = 700;
	
	public static int getX()
	{return xDim;}
	
	public static int getY()
	{return yDim;}
	
	public static void setX(int x){
		xDim = x;
	}
	public static void setY(int y){
		yDim = y;
	}
	public static int getXCenter(){
		return xDim/2;
	}
	public static int getYCenter(){
		return yDim/2;
	}
}
