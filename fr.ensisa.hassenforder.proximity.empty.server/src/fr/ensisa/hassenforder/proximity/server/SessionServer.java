package fr.ensisa.hassenforder.proximity.server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.User;

public class SessionServer {

	private Socket connection;
	private Document document;
	private UsersState state;
	
	public SessionServer (Document document, Socket connection,UsersState state) {
		this.document = document;
		this.connection = connection;
		this.state = state;
	}

	public boolean operate() {
		try {
			Writer writer = new Writer (connection.getOutputStream());
			Reader reader = new Reader (connection.getInputStream());
			reader.receive ();
			switch (reader.getType ())
			{
				case 0 : 
					return false; // socket closed
				case Protocol.CLIENT_REQUEST_ESTABLISH : 								/* requete USER */ 
					reader.receive();
					switch(reader.getType())
					{
					case Protocol.CONNECTION :							/* discriminant CONNECT */
						User user = reader.readUser(this.document);
						if(user == null)
						{
							writer.writeKo();
							break;
						}
						if(this.state.userIsConnected(user.getName()) && this.state.getUserPort(user.getName()) != this.connection.getPort())
							writer.writeKo();
						else
						{
							this.state.addUser(user.getName(), this.connection.getPort());
							writer.writeUser(user);
						}
						break;
					case Protocol.DISCONNECTION :				/* discriminant DISCONNECT */
						this.state.removeUser(this.connection.getPort());
						writer.writeOk();
						break;
					}
					break;
				case Protocol.CLIENT_REQUEST_SET :			/* requete SET */
					boolean bool = false;
					reader.receive();
					switch(reader.getType())
					{
					case Protocol.XY :							/* discriminant XY */
						bool = reader.readXY(this.document, this.state.getUserName(this.connection.getPort()));
						break;
					case Protocol.RADIUS :				/* discriminant RADIUS */
						bool = reader.setRadius(this.document, this.state.getUserName(this.connection.getPort()));
						break;
					case Protocol.PREFERENCE_LEVEL :				/* discriminant PREFERENCE LEVEL */
						bool = reader.setPreferenceLevel(this.document);
						break;
					case Protocol.MODE :				/* discrimiant MODE */
						bool = reader.setMode(this.document, this.state.getUserName(this.connection.getPort()));
						break;
					case Protocol.PREFERENCE_VISIBILITY :				/* discriminant PREFERENCE VISIBILITY */
						bool = reader.setPreferenceVisibility(this.document);
						break;
					}
					if(bool)
						writer.writeOk();
					else 
						writer.writeKo();
					break;
				case Protocol.CLIENT_REQUEST_FIND :			/* requete FIND */
					List<User> users = new ArrayList<User>();
					users = reader.readFind(this.document);
					if(users.isEmpty())
						writer.writeKo();
					else
						writer.writeUsersList(users);
					break;
				default:
					return false; // connection jammed
			}
			writer.send ();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
}
