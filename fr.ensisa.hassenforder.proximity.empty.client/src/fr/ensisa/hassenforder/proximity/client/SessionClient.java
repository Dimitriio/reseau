package fr.ensisa.hassenforder.proximity.client;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.User;

public class SessionClient {

	private Socket connection;

	public SessionClient (Socket connection) {
		this.connection = connection;
	}

	public User connect (String name) {
		try {
			Writer writer = new Writer (connection.getOutputStream());
			Reader reader = new Reader (connection.getInputStream()); 
			writer.writeUserConnect(name);
			writer.send();
			reader.receive();
			if(reader.getType() == 1)
				return reader.readUser();
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public void disconnect () {
		try {
			Writer writer = new Writer (connection.getOutputStream());
			Reader reader = new Reader (connection.getInputStream()); 
			writer.writeUserDisconnect();
			writer.send();
			reader.receive();
			connection = null;
		} catch (IOException e) {
		}
	}

	public User getState(String name) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public List<User> findNear(String name) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public boolean changeMode (String name, Mode mode) {
		try {
			Writer writer = new Writer (connection.getOutputStream());
			Reader reader = new Reader (connection.getInputStream()); 
			writer.writeSetMode(mode);
			writer.send();
			reader.receive();
			if(reader.getType() == 3)
				return reader.readOk();
			return reader.readKo();
		} catch (IOException e) {
			return false;
		}
	}

	public boolean move(String name, int x, int y) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean changeRadius(String name, int radius) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean changePreferenceLevel(String name, String preference, int value) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean changePreferenceVisibility(String name, String preference, boolean value) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return false;
		} catch (IOException e) {
			return false;
		}
	}

}
