package gui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NewUserPanel extends JPanel implements ActionListener{
	public NewUserPanel(){
		setLayout(null);
		setVisible(true);
		setBounds(0,0,1100,700);
		
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
				+ "If you would like to initialize this service click either of the buttons below"
				+ "</p>"
				+ "</html>");
		welcomeLabel.setBounds(100,100,800,500);
		
		JButton online = new JButton("<html>"
				+ "Sync all files"
				+ "<br> (recommended) </html>");
		online.setBounds(200,500,200,100);
		online.addActionListener(this);
		
		JButton local = new JButton("Sync only local folders");
		local.setBounds(500, 500, 200, 100);
		local.addActionListener(this);
		
		add(welcomeLabel);
		add(online);
		add(local);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<html>"
				+ "Sync all files"
				+ "<br> (recommended) </html>")){
			//TODO
		}
		if(e.getActionCommand().equals("<html>"
				+ "Sync only local folders"
				+ "</html>")){
			//TODO
		}
	}
}
