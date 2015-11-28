package fr.ensisa.hassenforder.proximity.server;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;
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
				case 1 : 								/* requete USER */ 
					reader.receive();
					switch(reader.getType())
					{
					case 1 :							/* discriminant CONNECT */
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
					case 2 :				/* discriminant DISCONNECT */
						this.state.removeUser(this.connection.getPort());
						writer.writeOk();
						break;
					}
					break;
				case 2 :			/* requete SET */
					boolean bool = false;
					reader.receive();
					switch(reader.getType())
					{
					case 1 :							/* discriminant XY */
						
						break;
					case 2 :				/* discriminant RADIUS */
						bool = reader.setRadius(this.document, this.state.getUserName(this.connection.getPort()));
						break;
					case 3 :				/* discriminant PREFERENCE */
						
						break;
					case 4 :				/* discrimiant MODE */
						bool = reader.setMode(this.document, this.state.getUserName(this.connection.getPort()));
						break;
					}
					if(bool)
						writer.writeOk();
					else 
						writer.writeKo();
				case 3 :
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
