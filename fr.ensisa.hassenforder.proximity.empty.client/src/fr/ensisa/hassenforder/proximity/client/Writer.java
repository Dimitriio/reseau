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
		writeInt(1);
		writeInt(1);
		writeString(name);
	}

	public void writeUserDisconnect()
	{
		writeInt(1);
		writeInt(2);
	}
	
	public void writeSetXY(int x, int y) 
	{
		writeInt(2);
		writeInt(1);
		writeInt(x);
		writeInt(y);		
	}

	public void writeSetRadius(int radius) 
	{
		writeInt(2);
		writeInt(2);
		writeInt(radius);
	}	
	
	public void writeSetPreferenceLevel(String name, String preference_name, int value)
	{
		writeInt(2);
		writeInt(3);
		writeString(name);
		writeString(preference_name);
		writeInt(value);
	}
	
	public void writeSetMode(Mode mode)
	{
		writeInt(2);
		writeInt(4);
		writeInt(mode.ordinal());
	}

	public void writeSetPreferenceVisibility(String name, String preference_name, boolean value) 
	{
		writeInt(2);
		writeInt(5);
		writeString(name);
		writeString(preference_name);
		writeBoolean(value);
	}

	

	
}
