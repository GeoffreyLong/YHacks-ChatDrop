package gui.chatpanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class EntryPanel extends JPanel{
	final JTextArea entryArea;
	public EntryPanel(){
		setBorder(BorderFactory.createLineBorder(Color.black));
		entryArea = new JTextArea();
		DefaultCaret caret = (DefaultCaret)entryArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		entryArea.setBounds(5,5,775,155);
		entryArea.setLineWrap(true);
		entryArea.setWrapStyleWord(true);
		setLayout(null);
		add(entryArea);
		setVisible(true);
	}
}
