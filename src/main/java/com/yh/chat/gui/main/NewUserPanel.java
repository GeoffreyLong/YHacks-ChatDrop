package com.yh.chat.gui.main;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.yh.chat.core.display.OpenResource;
import com.yh.chat.gui.values.WindowSizes;

public class NewUserPanel extends JPanel implements ActionListener{
	private final JTextField url;
	private boolean readyBoolean = false;
	
	private class MouseAdapterOpenUrl
	{
		
	}
	
	public NewUserPanel(String authorizeUrl){
		setLayout(null);
		setVisible(true);
		setBounds(0,0,WindowSizes.getX(),WindowSizes.getY());
		
		JLabel welcomeLabel = new JLabel("<html>"
				+ "<big>Welcome to ChatterBox!</big><br>"
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
				+ "<li> Click the link below.<br></li>"
				+ "<li> Click Allow (you might have to log in first). </li>"
				+ "<li> Copy the authorization code. </li>"
				+ "<li> Paste it to the text area and submit. </li>"
				+ "</ul>"
				+ "</html>");
		welcomeLabel.setBounds(100,100,800,300);
		
		JButton urlField = new JButton(authorizeUrl);
		urlField.setBounds(50,450,800,20);
		urlField.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				try {
					OpenResource.open(new URI(((JButton) e.getSource()).getText()));
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
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
        return new Dimension(WindowSizes.getX(),WindowSizes.getY());
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Submit")){
			readyBoolean = true;
		}
	}
	public String getURL(){
		if(readyBoolean){
			return url.getText();
		}
		else{
			return null;
		}
	}
}
