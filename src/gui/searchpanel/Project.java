package gui.searchpanel;

import javax.swing.JPanel;

public class Project extends JPanel implements Comparable<Project> {
	private String projectName;
	private int notifications;
	private String[] users;
	
	Project(String projectName/*, int notifications*/){
		this.projectName = projectName;
		//this.notifications = notifications;
	}
	public void setUsers(String[] users){
		this.users = users;
	}
	public String getProjectName(){
		return projectName;
	}
	public int getNotificationNum(){
		return notifications;
	}
	public int compareTo(Project compares) {
		int compareQuantity = ((Project) compares).getNotificationNum(); 
		return compareQuantity - this.notifications;
	}
}
