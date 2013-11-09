package gui.searchpanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreateChat extends JPanel{
	public CreateChat(){
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		setBounds(5,5,275,45);
		//TODO add a + button like a nice green plus in a little subpanel like fb or whatever
		JLabel create = new JLabel("Create A New Chat");
		create.setBounds(25,3,120,40);
		add(create);
	}
}
