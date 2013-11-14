package com.yh.chat.gui.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel{
	private BufferedImage image;
	public WelcomePanel(){
		setLayout(null);
		setBounds(0,0,780,60);
		JLabel welcome = new JLabel("<html>"
				+ "Welcome to ChatterBox"
				+ "</html>");
		welcome.setBounds(310,10,300,30);
		add(welcome);

		try {                
			image = ImageIO.read(new File("../gui.UI_Elements/Logo-01.png"));
		} 
		catch (Exception e) {
		}
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }
}
