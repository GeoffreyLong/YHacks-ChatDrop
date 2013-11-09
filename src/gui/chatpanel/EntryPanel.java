package gui.chatpanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultCaret;

public class EntryPanel extends JPanel{
	final JTextArea entryArea;
	private boolean isShifted = false;
	
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
		
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
        InputMap inputMap = entryArea.getInputMap();
        inputMap.put(enter, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Enter released");
            }
        });

        entryArea.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.SHIFT_DOWN_MASK, true), "Shenter");
        entryArea.getActionMap().put("Shenter", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Shift+Enter released");
            }
        });
	}
}
