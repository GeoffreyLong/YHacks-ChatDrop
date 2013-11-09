package core.objects;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;

public class UserImpl implements User {
	private DbxClient owner;
	public UserImpl(DbxClient dropboxClient){
		owner=dropboxClient;
	}
	
	public Long getUserId() throws DbxException{
		return owner.getAccountInfo().userId;
	}
	public String getUserDisplayName() throws DbxException{
		return owner.getAccountInfo().displayName;
	}
}
