package gui.chatpanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultCaret;

import org.joda.time.DateTime;

import com.dropbox.core.DbxException;

import core.display.MessageSorter;
import core.objects.SharedFolder;

public class EntryPanel extends JPanel implements ActionListener{
	final JTextArea entryArea;
	private boolean isShifted = false;
	private SharedFolder sharedFolder;
	private MessageSorter messages;
	
	public EntryPanel(final SharedFolder sharedFolder, final MessageSorter messages){
		this.sharedFolder = sharedFolder;
		this.messages = messages;
		setBorder(BorderFactory.createLineBorder(Color.black));
		entryArea = new JTextArea();
		setBackground(new Color(0xBFCFEF));
		DefaultCaret caret = (DefaultCaret)entryArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		entryArea.setBounds(5,5,650,155);
		entryArea.setLineWrap(true);
		entryArea.setWrapStyleWord(true);
		setLayout(null);
		add(entryArea);
		setVisible(true);
		
		JButton send = new JButton ("Send");
		send.setBounds(670,100,100,50);
		send.addActionListener(this);
		add(send);
		
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
        InputMap inputMap = entryArea.getInputMap();
        inputMap.put(enter, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
            	pipeData();
            }
        });

        entryArea.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.SHIFT_DOWN_MASK, true), "Shenter");
        entryArea.getActionMap().put("Shenter", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
               // entryArea.append(text+"\n");
            }
        });
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Send")){
			pipeData();
		}
	}
	
	public void pipeData(){
		DateTime date = new DateTime();
    	core.objects.Message mess = new core.objects.MessageImpl(gui.main.Frame.name, entryArea.getText(), date);
    	try {
			new core.io.ChatWriter(sharedFolder, mess);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DbxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	entryArea.setText("");
    	gui.main.Frame.chatQuickUpdate(sharedFolder);
	}

	public void updateMessages(SharedFolder messages2) {
		this.sharedFolder = messages2;
	}
}
