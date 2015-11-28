package fr.ensisa.hassenforder.proximity.server;


import java.io.InputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;
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
	
	public User readUser(Document document) {
		return document.doConnect(readString());
	}

	public boolean setMode(Document document, String name) {
		return document.doChangeMode(name ,Mode.values()[readInt()]);
	}
}
