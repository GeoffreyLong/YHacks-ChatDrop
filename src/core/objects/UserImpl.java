package core.objects;

import com.dropbox.core.DbxException;

public class UserImpl implements User {
	Long userID;
	String userDisplayName;
	
	public UserImpl(Long id, String display)
	{
		this.userID = id;
		this.userDisplayName = display;
	}
	
	
	public Long getUserId() throws DbxException {
		return userID;
	}

	public String getUserDisplayName() throws DbxException {
		return userDisplayName;
	}

}
