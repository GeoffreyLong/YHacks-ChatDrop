package gui.searchpanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Project extends JButton implements Comparable<Project> {
	private String projectName;
	private int notifications;
	private String[] users;
	
	Project(String projectName/*, int notifications*/){
		setLayout(null);
		setBounds(5,5,275,45);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.projectName = projectName;
		setNotificationNum();
		this.notifications = getNotificationNum();
		JLabel notification = new JLabel(Integer.toString(this.notifications));
		notification.setBounds(3,3,20,20);
		JLabel projName = new JLabel(projectName);
		projName.setBounds(25,3,120,40);
		add(notification);
		add(projName);
	}
	public void setUsers(String[] users){
		this.users = users;
	}
	private void setNotificationNum(){
		//todo
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
