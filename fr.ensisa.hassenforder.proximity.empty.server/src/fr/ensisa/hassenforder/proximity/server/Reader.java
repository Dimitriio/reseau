package fr.ensisa.hassenforder.proximity.server;


import java.io.InputStream;
import java.util.List;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.User;

public class Reader extends BasicAbstractReader {

	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0 :
			break;
		
		}
	}
	
	public User readUser(Document document)
	{
		return document.doConnect(readString());
	}

	public boolean setMode(Document document, String name) 
	{
		return document.doChangeMode(name , Mode.values()[readInt()]);
	}

	public boolean setRadius(Document document, String name) 
	{
		return document.doChangeRadius(name, readInt());
	}

	public boolean readXY(Document document, String name)
	{
		int x = readInt();
		int y = readInt();
		return document.doMove(name, x, y);
	}

	public boolean setPreferenceLevel(Document document) 
	{
		String name = readString();
		String preference_name = readString();
		int value = readInt();
		return document.doChangePreferenceLevel(name, preference_name, value);
	}

	public boolean setPreferenceVisibility(Document document) 
	{
		String name = readString();
		String preference_name = readString();
		boolean value = readBoolean();
		return document.doChangePreferenceVisibility(name, preference_name, value);
	}

	public List<User> readFind(Document document)
	{
		String name = readString();
		return document.doFind(name);
	}
}
