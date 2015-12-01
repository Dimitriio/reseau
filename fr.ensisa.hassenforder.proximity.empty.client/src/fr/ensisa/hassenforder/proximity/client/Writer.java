package fr.ensisa.hassenforder.proximity.client;

import java.io.OutputStream;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	public void writeUserConnect(String name)
	{
		writeInt(Protocol.CLIENT_REQUEST_ESTABLISH);
		writeInt(Protocol.CONNECTION);
		writeString(name);
	}

	public void writeUserDisconnect()
	{
		writeInt(Protocol.CLIENT_REQUEST_ESTABLISH);
		writeInt(Protocol.DISCONNECTION);
	}
	
	public void writeSetXY(int x, int y) 
	{
		writeInt(Protocol.CLIENT_REQUEST_SET);
		writeInt(Protocol.XY);
		writeInt(x);
		writeInt(y);		
	}

	public void writeSetRadius(int radius) 
	{
		writeInt(Protocol.CLIENT_REQUEST_SET);
		writeInt(Protocol.RADIUS);
		writeInt(radius);
	}	
	
	public void writeSetPreferenceLevel(String name, String preference_name, int value)
	{
		writeInt(Protocol.CLIENT_REQUEST_SET);
		writeInt(Protocol.PREFERENCE_LEVEL);
		writeString(name);
		writeString(preference_name);
		writeInt(value);
	}
	
	public void writeSetMode(Mode mode)
	{
		writeInt(Protocol.CLIENT_REQUEST_SET);
		writeInt(Protocol.MODE);
		writeInt(mode.ordinal());
	}

	public void writeSetPreferenceVisibility(String name, String preference_name, boolean value) 
	{
		writeInt(Protocol.CLIENT_REQUEST_SET);
		writeInt(Protocol.PREFERENCE_VISIBILITY);
		writeString(name);
		writeString(preference_name);
		writeBoolean(value);
	}

	public void writeFind(String name) 
	{
		writeInt(Protocol.CLIENT_REQUEST_FIND);
		writeString(name);
	}

	

	
}
