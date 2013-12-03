package com.yh.chat.gui.UI_Elements;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class StyledPanel extends JPanel{
	public StyledPanel(){
		Color background = new Color(0x444444);
		setBackground(background);
		//setBorder(BorderFactory.createBevelBorder(0, new Color(0x458B00), new Color(0x236900)));
		setBorder(BorderFactory.createLineBorder(new Color(0x458B00),2));
	}
}
