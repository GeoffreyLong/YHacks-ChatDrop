package com.yh.chat.gui.searchpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ChatButton extends JButton implements MouseListener{
	private Color color;
	
	public ChatButton(String buttonName){
		super(buttonName);
		
		color = new Color(0x123456);
		
		setBorder(BorderFactory.createLineBorder(color, 4));
		setBackground(Color.WHITE);
		setForeground(color);
		setFont(new Font("Serif", Font.BOLD, 14));
		
		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		setBackground(color);
		setForeground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
	}

	public void mouseExited(MouseEvent arg0) {
		setBackground(Color.WHITE);
		setForeground(color);
		setBorder(BorderFactory.createLineBorder(color, 4));
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
