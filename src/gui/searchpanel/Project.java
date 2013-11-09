package gui.searchpanel;

import javax.swing.JPanel;

public class Project extends JPanel{
	private String projectName;
	private int notifications;
	private String[] users;
	
	Project(String projectName, int notifications){
		this.projectName = projectName;
		this.notifications = notifications;
	}
	public void getUsers(String[] users){
		this.users = users;
	}
}
