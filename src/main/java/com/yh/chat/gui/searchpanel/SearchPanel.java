package com.yh.chat.gui.searchpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
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
import com.yh.chat.gui.UI_Elements.CustomCaret;
import com.yh.chat.gui.UI_Elements.Layout;
import com.yh.chat.gui.UI_Elements.StyledPanel;
import com.yh.chat.gui.main.Frame;
import com.yh.chat.gui.values.WindowSizes;

public class SearchPanel extends StyledPanel implements DocumentListener {
	final JTextField searchField;
	//File root;
	List<Project> projects;
	List<SharedFolder> files;
	JPanel projectPanel = new JPanel();
	JPanel searchFieldPanel = new JPanel();
	CreateChat newChat = new CreateChat("Create a new Chat");
	Rectangle panelBounds;
	JScrollPane scroll;
	private int buttonWidth;
	
	public SearchPanel(List<SharedFolder> list){
		setLayout(null);
		
		searchField = new JTextField();
		searchField.setForeground(new Color(0x123456));
		searchField.setBackground(new Color(0xDDDDDD));
		searchField.setFont(new Font("Serif", Font.BOLD, 24));
		searchField.getDocument().addDocumentListener(this);
		searchField.setCaret(new CustomCaret());
		
		this.files = list;
		projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.Y_AXIS)); 
		scroll = new JScrollPane(projectPanel);
		
		initLayout();
		
		initNewChat();
		
		addProjects(list);
		
		repaint();
	}

	private void initComponents(){
		
	}
	
	private void initLayout(){
		panelBounds = Layout.getSearchPanel();
		int innerWidth = panelBounds.width-11;
		
		searchFieldPanel.setBounds(5, 5, innerWidth, panelBounds.height/15);
		searchFieldPanel.setBorder(BorderFactory.createSoftBevelBorder(0));
		searchFieldPanel.setBackground(new Color(0xDDDDDD));
		searchFieldPanel.setVisible(true);
		searchFieldPanel.setLayout(null);
		
		searchField.setBounds(45, 3, innerWidth-50, panelBounds.height/15-7);
		searchField.setBorder(BorderFactory.createLineBorder(new Color(0x444444), 2));
		searchField.setBackground(Color.WHITE);
		searchFieldPanel.add(searchField);
		
		//TODO move the initial cursor over a few pixels
		//TODO add magnifying glass image thing
		JPanel imagePanel = new JPanel();
		imagePanel.setBorder(BorderFactory.createLineBorder(new Color(0x444444), 2));
		imagePanel.setBounds(5,3,45,panelBounds.height/15-7);
		imagePanel.setBackground(Color.WHITE);
		searchFieldPanel.add(imagePanel);
		
		scroll.setBounds(5, panelBounds.height/15 + 10, 
				innerWidth, 14*panelBounds.height/15 - 15);
		scroll.setBorder(BorderFactory.createSoftBevelBorder(0));
		
		projectPanel.setBounds(5, panelBounds.height/15 + 10, 
				innerWidth, 14*panelBounds.height/15 - 15);
		projectPanel.setBackground(new Color(0xDDDDDD));
		
		buttonWidth = (int)(innerWidth*.9);
		
		add(searchFieldPanel);
		add(scroll);
	}
	
	private void initNewChat(){
		newChat.setPreferredSize(new Dimension(buttonWidth,50));
		newChat.setMaximumSize(new Dimension(buttonWidth,50));
		newChat.setMinimumSize(new Dimension(buttonWidth,50));
		
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
	}
	
	public void addFiller(){
        Dimension minSize = new Dimension(buttonWidth, 10);
        Dimension prefSize = new Dimension(buttonWidth, 10);
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
		addFiller();
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
				
				proj.setPreferredSize(new Dimension(buttonWidth,50));
				proj.setMaximumSize(new Dimension(buttonWidth,50));
				proj.setMinimumSize(new Dimension(buttonWidth,50));
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
