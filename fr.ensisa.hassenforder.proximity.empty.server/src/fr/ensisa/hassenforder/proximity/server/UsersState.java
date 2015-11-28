package fr.ensisa.hassenforder.proximity.server;

import java.util.LinkedList;

public class UsersState {
	private LinkedList<String> users;
	private LinkedList<Integer> ports;

	public UsersState()
	{
		this.users = new LinkedList<String>();
		this.ports = new LinkedList<Integer>();
	}
	
	public void addUser( String name, int port) {
		this.users.addLast(name);
		this.ports.addLast(port);
	}

	public boolean userIsConnected(String name) {
		if(!users.isEmpty())
			return users.contains(name);
		return false;
	}
	
	public int getUserPort(String name)
	{
		return this.ports.get(this.users.indexOf(name));
	}

	public void removeUser(int port)
	{
		this.users.remove();
		this.ports.remove(ports);
	}

	public String getUserName(int port) {
		return this.users.get(this.ports.indexOf(port));
	}
}
