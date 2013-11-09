package core.objects;

import com.dropbox.core.DbxException;

public interface User {
	public Long getUserId() throws DbxException;
	public String getUserDisplayName() throws DbxException;
}
