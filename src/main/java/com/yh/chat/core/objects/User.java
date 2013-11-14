package com.yh.chat.core.objects;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;

public interface User {
	public Long getUserId() throws DbxException;
	public String getUserDisplayName() throws DbxException;
	public DbxClient getDbxClient();
}
