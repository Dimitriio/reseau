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
		writeString(user.getName());
		writeInt(user.getX());
		writeInt(user.getY());
		writeInt(user.getRadius());
		writeInt(user.getMode().ordinal());
		writeInt(user.getPreferences().size());
		for(int i = 0; i < user.getPreferences().size(); i++ )
		{
			writeString(user.getPreferenceByPosition(i).getName());
			writeInt(user.getPreferenceByPosition(i).getLevel());
			writeBoolean(user.getPreferenceByPosition(i).isVisibility());
		}
	}
}

