package gui.searchpanel;

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

public class CreateChat extends JButton{
	public CreateChat(){
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		
		//TODO add a + button like a nice green plus in a little subpanel like fb or whatever
		JLabel create = new JLabel("Create A New Chat");
		create.setBounds(25,3,180,40);
		add(create);
	}
	
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
        int returnVal = chooser.showOpenDialog(gui.main.Frame.frame);
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
        
	    return file;
	}
}
