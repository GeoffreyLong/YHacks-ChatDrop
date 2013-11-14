package com.yh.chat.core.objects;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;

public class OwnerImpl implements User {
	private DbxClient owner;
	public OwnerImpl(DbxClient dropboxClient){
		owner=dropboxClient;
	}
	
	public Long getUserId() throws DbxException{
		return owner.getAccountInfo().userId;
	}
	public String getUserDisplayName() throws DbxException{
		return owner.getAccountInfo().displayName;
	}
	public DbxClient getDbxClient(){
		return owner;
	}
}
