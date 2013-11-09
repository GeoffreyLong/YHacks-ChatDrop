package gui.searchpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
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
	List<String> list;
	JPanel projectPanel = new JPanel();
	CreateChat newChat = new CreateChat();
	
	public SearchPanel(){
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.black));
		//root = getRoot();
		list = new LinkedList<String>();
		//list = root.list();
		projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.Y_AXIS)); 
		newChat.setPreferredSize(new Dimension(270,50));
		newChat.setMaximumSize(new Dimension(270,50));
		newChat.setMinimumSize(new Dimension(270,50));
		newChat.setAlignmentX(Component.CENTER_ALIGNMENT);
		projectPanel.add(newChat);
		JScrollPane scroll = new JScrollPane(projectPanel);
		scroll.setBounds(5,60,280,580);
		projectPanel.setBounds(5,60,280,580);
		
		newChat.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		
		if (projects != (null)){
			for (String string : list){
				Project proj = new Project(string);
				
				proj.setPreferredSize(new Dimension(270,50));
				proj.setMaximumSize(new Dimension(270,50));
				proj.setMinimumSize(new Dimension(270,50));
				proj.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				proj.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						//TODO 
					}
				});
				projects.add(proj);
			}
			Collections.sort(projects);
		}
		
		searchField = new JTextField();
		searchField.setBounds(5,5,285,50);
		searchField.getDocument().addDocumentListener(this);
		add(searchField);
		add(scroll);
	}

	public void insertUpdate(DocumentEvent arg0) {
		String desired = searchField.getText();
		projectPanel.removeAll();
		projectPanel.add(newChat);
		if (projects != null){
			for (Project project : projects){
				if (project.getProjectName().substring(0,desired.length()).contains(desired)){
					projectPanel.add(project);
				}
			}
		}
		repaint();
	}
	public void changedUpdate(DocumentEvent arg0) {
	}
	public void removeUpdate(DocumentEvent arg0) {
	}
}
