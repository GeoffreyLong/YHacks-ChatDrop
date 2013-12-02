package com.yh.chat.gui.searchpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.dropbox.core.DbxException;
import com.yh.chat.core.display.MessageSorter;
import com.yh.chat.core.init.InitConversation;
import com.yh.chat.core.objects.Message;
import com.yh.chat.core.objects.SharedFolder;
import com.yh.chat.core.objects.SharedFolderImpl;
import com.yh.chat.gui.UI_Elements.StyledPanel;
import com.yh.chat.gui.main.Frame;
import com.yh.chat.gui.values.WindowSizes;

public class SearchPanel extends StyledPanel implements DocumentListener {
	final JTextField searchField;
	//File root;
	List<Project> projects;
	List<SharedFolder> files;
	JPanel projectPanel = new JPanel();
	CreateChat newChat = new CreateChat("Create a new Chat");
	
	public SearchPanel(List<SharedFolder> list){
		setLayout(null);
		
		this.files = list;
		projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.Y_AXIS)); 
		JScrollPane scroll = new JScrollPane(projectPanel);
		scroll.setBounds(5,60,WindowSizes.getX()/4-10,WindowSizes.getY()-125);
		projectPanel.setBounds(5,60,280,580);
		
		newChat.setPreferredSize(new Dimension(200,50));
		newChat.setMaximumSize(new Dimension(200,50));
		newChat.setMinimumSize(new Dimension(200,50));
		/*newChat.setIcon(new javax.swing.ImageIcon(getClass().getResource("../UI_Elements/CreateButton.png")));  
		newChat.setBorderPainted(false);  
		newChat.setFocusPainted(false);  
		newChat.setContentAreaFilled(false);  
		newChat.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("../UI_Elements/ButtonPressed.png")));*/
		newChat.setAlignmentX(Component.CENTER_ALIGNMENT);
		newChat.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				java.io.File file = newChat.getFile();
				if (!file.getName().equals("")){
					ArrayList list = new ArrayList<Message>();
					SharedFolder a = new SharedFolderImpl(file);
					MessageSorter messages = null;
					try {
						InitConversation.create(a);
						messages = new MessageSorter(a);
					} catch (DbxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Frame.initChat(messages, a);
				}
			}
		});
		addFiller();
		projectPanel.add(newChat);
		
		addProjects(list);
		
		searchField = new JTextField();
		searchField.setBounds(5,5,WindowSizes.getX()/4-10,50);
		searchField.getDocument().addDocumentListener(this);
		add(searchField);
		add(scroll);
		repaint();
	}

	public void addFiller(){
        Dimension minSize = new Dimension(200, 10);
        Dimension prefSize = new Dimension(200, 10);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 10);
        projectPanel.add(new Box.Filler(minSize, prefSize, maxSize));
	}
	
	public void insertUpdate(DocumentEvent arg0) {
		showProjects();
	}
	public void changedUpdate(DocumentEvent arg0) {
	}
	public void removeUpdate(DocumentEvent arg0) {
		showProjects();
	}
	public void showProjects(){
		String desired = searchField.getText();
		
		List<SharedFolder> newFiles = new LinkedList<SharedFolder>();
		
		projectPanel.removeAll();
		projectPanel.add(newChat);
		for (SharedFolder file : files){
			if (file.getTopLevel().getName().length()>desired.length()){
				if (file.getTopLevel().getName().substring(0,desired.length()).contains(desired)){
					newFiles.add(file);
				}
			}
		}
		addProjects(newFiles);
		repaint();
	}
	public void addProjects(List<SharedFolder> afiles){
		projects = new LinkedList<Project>();
		if (!afiles.isEmpty()){
			for (SharedFolder file : afiles){
				final Project proj = new Project(file);
				
				proj.setPreferredSize(new Dimension(200,50));
				proj.setMaximumSize(new Dimension(200,50));
				proj.setMinimumSize(new Dimension(200,50));
				proj.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				proj.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						MessageSorter messages = new MessageSorter(proj.getSharedFolder());
						Frame.initChat(messages, proj.getSharedFolder());
					}
				});
				projects.add(proj);
			}
			Collections.sort(projects);
			for (Project proj : projects){
				addFiller();
				projectPanel.add(proj);
			}
			validate();
			repaint();
		}
	}
}
