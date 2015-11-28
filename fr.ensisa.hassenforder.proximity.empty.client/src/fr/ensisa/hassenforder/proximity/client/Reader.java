package fr.ensisa.hassenforder.proximity.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Preference;
import fr.ensisa.hassenforder.proximity.model.User;
import fr.ensisa.hassenforder.proximity.model.Mode;

public class Reader extends BasicAbstractReader {

	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0: 
			break;
		}
	}
	
	public User readUser() {
		String name = readString();
		int x = readInt();
		int y = readInt();
		int radius = readInt();
		Mode mode = Mode.values()[readInt()];
		int nb = readInt();
		User user = new User(name, x, y, radius, mode);
		for(int i = 0; i < nb ;i++ )
		{
			name = readString();
			int level = readInt();
			boolean visibility = readBoolean();
			user.addPreference(new Preference(name, level, visibility));
		}
		return user;
	}
	
	public boolean readKo()
	{
		return readBoolean();
	}
}
