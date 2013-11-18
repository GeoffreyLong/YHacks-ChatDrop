package com.yh.chat.gui.main;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	private final String urlName;
	
	private class MouseAdapterOpenUrl
	{
		
	}
	
	public NewUserPanel(String authorizeUrl){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();;
		setVisible(true);
		setBounds(0,0,WindowSizes.getX(),WindowSizes.getY());
		
		JLabel welcomeLabel = new JLabel("<html>"
				+ "<div style='text-align:center'>"
				+ "<big style='text-align:center'>Welcome to ChatterBox!</big></div><br>"
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
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 4;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(welcomeLabel, c);
		
		urlName = authorizeUrl;
		
		JButton urlField = new JButton("Authorization Link");
		urlField.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				try {
					OpenResource.open(new URI(urlName));
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(10,5,10,0);
		c.fill = GridBagConstraints.NONE;
		add(urlField, c);
		
		url = new JTextField();
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 6;
		c.ipadx = 390;
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.WEST;
		add(url, c);
		
		
		JButton online = new JButton("Submit");
		online.addActionListener(this);
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.NONE;
		c.ipadx = 40;
		c.anchor = GridBagConstraints.EAST;
		add(online, c);
		
		add(welcomeLabel);
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
