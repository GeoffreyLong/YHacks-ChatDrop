package gui.searchpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreateChat extends JButton{
	public CreateChat(){
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		
		//TODO add a + button like a nice green plus in a little subpanel like fb or whatever
		JLabel create = new JLabel("Create A New Chat");
		create.setBounds(25,3,180,40);
		add(create);
	}
}
