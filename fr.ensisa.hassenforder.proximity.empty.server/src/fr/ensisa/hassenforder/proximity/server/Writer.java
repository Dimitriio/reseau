package fr.ensisa.hassenforder.proximity.server;

import java.io.OutputStream;
import java.util.List;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.User;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}
	
	public void writeUser(User user) {
		writeInt(Protocol.SERVER_ANSWER_USER);
		writeString(user.getName());
		writeInt(user.getX());
		writeInt(user.getY());
		writeInt(user.getRadius());
		writeInt(user.getMode().ordinal());
		writeInt(user.getPreferences().size());
		for(int i = 0; i < user.getPreferences().size(); i++ )
			writePreference(user, i);
	}
	
	public void writePreference(User user, int position)
	{
		writeString(user.getPreferenceByPosition(position).getName());
		writeInt(user.getPreferenceByPosition(position).getLevel());
		writeBoolean(user.getPreferenceByPosition(position).isVisibility());
	}

	public void writeKo()
	{
		writeInt(Protocol.SERVER_ANSWER_KO);
		writeBoolean(false);
	}

	public void writeOk()
	{
		writeInt(Protocol.SERVER_ANSWER_OK);
		writeBoolean(true);
	}

	public void writeUsersList(List<User> users) 
	{
		writeInt(Protocol.SERVER_ANSWER_USERS_LIST);
		writeInt(users.size());
		for(int i = 0; i< users.size(); i++)
		{
			writeString(users.get(i).getName());
			writeInt(users.get(i).getX());
			writeInt(users.get(i).getY());
			writeInt(users.get(i).getRadius());
			writeInt(users.get(i).getMode().ordinal());
			writeInt(users.get(i).getPreferences().size());
			for(int j = 0; j < users.get(i).getPreferences().size(); j++ )
				writePreference(users.get(i), j);
		}
	}
}

