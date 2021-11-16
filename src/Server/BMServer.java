package Server;
//This file contains material supporting section 3.7 of the textbook:

// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.

 */
public class BMServer extends AbstractServer {

	// Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 */

	public BMServer(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	// Instance methods ************************************************

	/**
	   * This method handles any messages received from the client.
	   *
	   * @param msg The message received from the client.
	   * @param client The connection from which the message originated.
	   */
	  @SuppressWarnings("unchecked")
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {	
//		    System.out.println("Message received: " + msg + " from " + client);
//		    this.sendToAllClients(msg);
//		    client.sendToClient(msg);
		  DBController db = new DBController("prototype","root","Tzachi1234!");
		  Message recievedMessage = (Message)msg;
		  TypeOfMessage messageType = recievedMessage.getTypeOfMsg();
		  switch(messageType) {
		  case ORDER:
			  OrderMessage orderMsg = null;
			  OrderController orderController = new OrderController(db);
			  orderMsg = (OrderMessage) orderController.processOrderMessageFromClient((OrderMessage)msg); //getting message about the process from controller
			  try {
			    	if(orderMsg != null)
			    		client.sendToClient(orderMsg);
				} catch (IOException e) {}
			  break;
		  default:
			  break;
		  }
		    

	  }

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0] The port number to listen on. Defaults to 5555 if no argument
	 *                is entered.
	 */
	public static void main(String[] args) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}

		BMServer sv = new BMServer(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}

	// End of EchoServer class

}
