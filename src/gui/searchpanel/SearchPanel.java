package gui.searchpanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	public SearchPanel(){
		//root = getRoot();
		list = new LinkedList<String>();
		//list = root.list();
		projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.Y_AXIS)); 
		JScrollPane scroll = new JScrollPane(projectPanel);
		scroll.setBounds(5,60,500,50);
		
		for (String string : list){
			projects.add(new Project(string));
		}
		
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.black));
		searchField = new JTextField();
		searchField.setBounds(5,5,285,50);
		searchField.getDocument().addDocumentListener(this);
		add(searchField);
		add(scroll);
	}

	public void insertUpdate(DocumentEvent arg0) {
		String desired = searchField.getText();
		projectPanel.removeAll();
		for (Project project : projects){
			if (project.getProjectName().substring(0,desired.length()).contains(desired)){
				projectPanel.add(project);
			}
		}
		repaint();
	}
	public void changedUpdate(DocumentEvent arg0) {
	}
	public void removeUpdate(DocumentEvent arg0) {
	}
}
