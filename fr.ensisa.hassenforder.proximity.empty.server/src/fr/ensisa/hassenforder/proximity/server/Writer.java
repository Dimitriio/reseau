package fr.ensisa.hassenforder.proximity.server;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Preference;
import fr.ensisa.hassenforder.proximity.model.User;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}
	
	public void writeUser(User user) {
		writeInt(1);
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
		writeInt(2);
		writeBoolean(false);
	}

	public void writeOk()
	{
		writeInt(3);
		writeBoolean(true);
	}
}

