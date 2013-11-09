package gui.main;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewUserPanel extends JPanel implements ActionListener{
	private final JTextField url;
	private boolean readyBoolean = false;
	
	public NewUserPanel(String authorizeUrl){
		setLayout(null);
		setVisible(true);
		setBounds(0,0,900,600);
		String urlHalf = authorizeUrl.substring(0,authorizeUrl.length()/2);
		String urlSecond = authorizeUrl.substring(authorizeUrl.length()/2+1, authorizeUrl.length());
		
		//Possible hyperlink implementation
		/*
		final URI uri;
		try {
			uri = new URI(authorizeUrl);
			JButton button = new JButton();
	        button.setText("www.roseindia.net");
	        button.setBorderPainted(false);
	        button.setOpaque(false);
	        button.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                        if (Desktop.isDesktopSupported()) {
	                                Desktop desktop = Desktop.getDesktop();
	                                try {
	                                        desktop.browse(uri);
	                                } catch (Exception ex) {
	                                }
	                        } else {
	                        }
	                }
	        });
	        add(button);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        */
		
		JLabel welcomeLabel = new JLabel("<html>"
				+ "Welcome to ChatterBox"
				//logo here
				+ "<p>This application allows you to easily converse across all your "
				+ "shared folders. <br> Use this application to easily perform the following actions: "
				+ "<ul>"
				+ "<li> Talk to shared folder collaborators </li>"
				+ "<li> Easily append comments to your changes </li>"
				+ "<li> Keep track of activity across multiple projects </li>"
				+ "</ul>"
				+ "</p>"
				+ "<p>"
				+ "If you would like to initialize this service follow these instructions:"
				+ "</p>"
				+ "<ul>"
				+ "<li> Copy and Paste the link below into a browser <br></li>"
				+ "<li> Go to the link <br></li>"
				+ "<li> Click Allow (you might have to log in first) </li>"
				+ "<li> Copy the authorization code. </li>"
				+ "<li> Paste it to the text area and submit </li>"
				+ "</ul>"
				+ "</html>");
		welcomeLabel.setBounds(100,100,800,300);
		
		JTextField urlField = new JTextField(authorizeUrl);
		urlField.setBounds(50,450,800,20);
		urlField.setEditable(false);
		add(urlField);
		
		url = new JTextField();
		url.setBounds(150,500,400,30);
		
		
		JButton online = new JButton("Submit");
		online.setBounds(600,500,100,30);
		online.addActionListener(this);
		
		add(welcomeLabel);
		add(online);
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
		this.setFocusable(true);
		url.requestFocus();
		if(readyBoolean){
			return url.getText();
		}
		else{
			return null;
		}
	}
}
