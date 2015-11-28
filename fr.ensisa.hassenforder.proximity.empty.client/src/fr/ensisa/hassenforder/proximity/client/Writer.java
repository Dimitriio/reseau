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

	public void writeSetMode(Mode mode) {
		writeInt(2);
		writeInt(4);
		writeInt(mode.ordinal());
	}
}
