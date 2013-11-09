package gui.searchpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

public class SearchPanel extends JPanel implements DocumentListener {
	final JTextField searchField;
	//File root;
	List<Project> projects;
	List<File> files;
	JPanel projectPanel = new JPanel();
	CreateChat newChat = new CreateChat();
	
	public SearchPanel(List<File> files){
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(new Color(0xBFCFEF));
		
		this.files = files;
		projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.Y_AXIS)); 
		JScrollPane scroll = new JScrollPane(projectPanel);
		scroll.setBounds(5,60,290,590);
		projectPanel.setBounds(5,60,280,580);
		
		newChat.setPreferredSize(new Dimension(270,50));
		newChat.setMaximumSize(new Dimension(270,50));
		newChat.setMinimumSize(new Dimension(270,50));
		newChat.setIcon(new javax.swing.ImageIcon(getClass().getResource("../UI_Elements/CreateButton.png")));  
		newChat.setBorderPainted(false);  
		newChat.setFocusPainted(false);  
		newChat.setContentAreaFilled(false);  
		newChat.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("../UI_Elements/ButtonPressed.png")));
		newChat.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		projectPanel.add(newChat);
		
		newChat.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		
		addProjects(files);
		
		searchField = new JTextField();
		searchField.setBounds(5,5,290,50);
		searchField.getDocument().addDocumentListener(this);
		add(searchField);
		add(scroll);
		repaint();
	}

	public void addFiller(){
        Dimension minSize = new Dimension(270, 10);
        Dimension prefSize = new Dimension(270, 10);
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
		
		List<File> newFiles = new LinkedList<File>();
		
		projectPanel.removeAll();
		projectPanel.add(newChat);
		for (File file : files){
			if (file.getName().length()>desired.length()){
				if (file.getName().substring(0,desired.length()).contains(desired)){
					newFiles.add(file);
				}
			}
		}
		addProjects(newFiles);
		repaint();
	}
	public void addProjects(List<File> afiles){
		projects = new LinkedList<Project>();
		if (!afiles.isEmpty()){
			for (File file : afiles){
				Project proj = new Project(file);
				
				proj.setPreferredSize(new Dimension(270,50));
				proj.setMaximumSize(new Dimension(270,50));
				proj.setMinimumSize(new Dimension(270,50));
				proj.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				proj.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						//call to method which will receive the messages
						//get back the arraylist of messages
						//core.objects.Message[] messages = getallmessages
						//gui.main.Frame.initChat(messages);
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
