package Server;

import ocsf.server.ConnectionToClient;

public class ErrorMessage extends Message{

	private final String msg; 
	private final String resonForError;
	
	public ErrorMessage(ConnectionToClient client,String resonForError,String msg) {
		super(client, TypeOfMessage.ERROR);
		this.msg = msg;
		this.resonForError = resonForError;
	}
	
	public ErrorMessage(ConnectionToClient client,String requestType) {
		this(client,requestType,"");
	}

	public String getMsg() {
		return msg;
	}

	public String getRequestType() {
		return resonForError;
	}
	
	

}
