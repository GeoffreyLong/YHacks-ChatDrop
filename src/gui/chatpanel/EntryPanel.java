package gui.chatpanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class EntryPanel extends JPanel{
	final JTextArea entryArea;
	public EntryPanel(){
		setBorder(BorderFactory.createLineBorder(Color.black));
		entryArea = new JTextArea();
		entryArea.setBounds(5,5,775,155);
		setLayout(null);
		add(entryArea);
		setVisible(true);
	}
}
