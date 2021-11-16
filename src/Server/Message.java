package Server;

import ocsf.server.ConnectionToClient;

public abstract class Message {
	
	private final ConnectionToClient client; // change to string, so we get the toString of client
	//typeOfMsg decides which controller will take care of the message
	//e.g. for "ORDER_MESSAGE" we will call OrderController to do the job
	private final TypeOfMessage typeOfMsg; 
	
	public Message(ConnectionToClient client,TypeOfMessage type) {
		this.client = client;
		typeOfMsg = type;
	}

	public ConnectionToClient getSenderID() {
		return client;
	}

	public TypeOfMessage getTypeOfMsg() {
		return typeOfMsg;
	}

}
