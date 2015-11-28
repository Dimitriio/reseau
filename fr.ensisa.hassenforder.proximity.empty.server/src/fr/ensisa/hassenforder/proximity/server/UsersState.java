package fr.ensisa.hassenforder.proximity.server;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.ensisa.hassenforder.proximity.model.User;

public class UsersState {
	private Map<String,Integer > usersConnected;

	public UsersState()
	{
		this.usersConnected = new HashMap<String,Integer >();
	}
	
	public void addUser( String name, int port) {
		this.usersConnected.put(name, port);
	}

	public boolean userIsConnected(String name) {
		if(!usersConnected.isEmpty())
			return usersConnected.containsKey(name);
		return false;
	}
	
	public int getUserPort(String name)
	{
		return usersConnected.get(name);
	}
}
