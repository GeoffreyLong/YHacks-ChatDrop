package gui.searchpanel;

import java.awt.Color;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.objects.SharedFolder;

public class Project extends JButton implements Comparable<Project> {
	private String projectName;
	private int notifications;
	private String[] users;
	private SharedFolder sharedFolder;
	
	Project(SharedFolder file){
		setLayout(null);
		setBounds(5,5,260,45);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.sharedFolder = file;
		
		/*
		setIcon(new javax.swing.ImageIcon(getClass().getResource("../UI_Elements/Button.png")));  
		setBorderPainted(false);  
		setFocusPainted(false);  
		setContentAreaFilled(false);  
		setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("../UI_Elements/ButtonPressed.png")));
		*/
		this.projectName = file.getTopLevel().getName();
		
		setNotificationNum();
		this.notifications = getNotificationNum();
		JLabel notification = new JLabel(Integer.toString(this.notifications));
		notification.setBounds(3,3,20,20);
		JLabel projName = new JLabel(projectName);
		projName.setBounds(25,3,120,40);
		//add(notification);
		add(projName);
	}
	public void setUsers(String[] users){
		this.users = users;
	}
	private void setNotificationNum(){
		//todo
	}
	public SharedFolder getSharedFolder(){
		return this.sharedFolder;
	}
	public String getProjectName(){
		return projectName;
	}
	public int getNotificationNum(){
		return notifications;
	}
	public String[] getUsers(){
		return this.users;
	}
	public int compareTo(Project compares) {
		int compareQuantity = ((Project) compares).getNotificationNum(); 
		return compareQuantity - this.notifications;
	}
}
