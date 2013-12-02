package com.yh.chat.gui.searchpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.dropbox.core.DbxEntry.File;
import com.yh.chat.gui.main.Frame;

public class CreateChat extends ChatButton{
	public CreateChat(String buttonName){
		super(buttonName);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		
		//TODO add a + button like a nice green plus in a little subpanel like fb or whatever
		/*JLabel create = new JLabel("Create A New Chat");
		create.setBounds(25,3,180,40);
		add(create);*/
	}
	
	//TODO create more stringent file getting attributes
	//Are there any conditions to watch out for?  
	//ie files we don't want users selecting?
	public java.io.File getFile(){
		Boolean old = UIManager.getBoolean("FileChooser.readOnly");  
		UIManager.put("FileChooser.readOnly", Boolean.TRUE);  
		
		String currentPath = "";
	    JFileChooser chooser = new JFileChooser();
	    UIManager.put("FileChooser.readOnly", old); 
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	java.io.File rootFile = new java.io.File(System.getProperty("user.dir"));
    	chooser.setCurrentDirectory(rootFile);
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        int returnVal = chooser.showOpenDialog(Frame.frame);
        if (returnVal == JFileChooser.APPROVE_OPTION){
        	java.io.File fileName = chooser.getSelectedFile();
        	java.io.File path = chooser.getCurrentDirectory();
        	currentPath=path.getPath()+"/"+fileName.getName();
        	if ((fileName == null) || (fileName.getName().equals("")) 
        			|| !currentPath.contains(System.getProperty("user.dir"))){
        		JOptionPane.showMessageDialog( this, "Invalid File Name",
        				"Invalid File Name", JOptionPane.ERROR_MESSAGE );
        		currentPath=null;
        	}
        	else{
        		currentPath=path.getPath()+"/"+fileName.getName();
        	} 
        }
        java.io.File file = null;
        if (currentPath!=null){
		    file = new java.io.File(currentPath);
        }
        
        if(!file.exists()) file.mkdirs();
	    return file;
	}
}
