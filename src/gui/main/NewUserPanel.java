package gui.main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewUserPanel extends JPanel implements ActionListener{
	private JTextField url;
	private boolean readyBoolean;
	
	public NewUserPanel(String authorizeUrl){
		setLayout(null);
		setVisible(true);
		setBounds(0,0,900,700);

		JLabel welcomeLabel = new JLabel("<html>"
				+ "Welcome to ChatterBox"
				//logo here
				+ "<p>This application allows you to easily converse across all your "
				+ "shared folders.  Use this application to easily perform the following actions: "
				+ "<ul>"
				+ "<li> Talk to shared folder collaborators </li>"
				+ "<li> Easily append comments to your changes </li>"
				+ "<li> Keep track of activity across multiple projects </li>"
				+ "<li> </li>"
				+ "</ul>"
				+ "</p>"
				+ "<p>"
				+ "If you would like to initialize this service follow these instructions:"
				+ "</p>"
				+ "<ul>"
				+ "<li> Go to: <a href="+ authorizeUrl + "</a></li>"
				+ "<li> Click Allow (you might have to log in first) </li>"
				+ "<li> Copy the authorization code. </li>"
				+ "<li> Paste it to the text area and submit </li>"
				+ "</ul>"
				+ "</html>");
		welcomeLabel.setBounds(100,100,800,500);
		
		url = new JTextField();
		url.setBounds(200,810,400,30);
		
		
		JButton online = new JButton("Submit");
		online.setBounds(700,810,100,30);
		online.addActionListener(this);
		
		add(welcomeLabel);
		add(url);
	}
	public Dimension getPreferredSize() {
        return new Dimension(900,700);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Submit")){
			readyBoolean = true;
		}
	}
	public String getURL(){
		if(readyBoolean = true){
			return url.getText();
		}
		else{
			return null;
		}
	}
}
