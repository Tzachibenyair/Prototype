// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package Client;

import ocsf.client.*;
import java.io.*;

import Server.Order;
import Server.OrderMessage;
import Server.RequestType;


/**
 * This class overrides some of the methods defined in the abstract superclass
 * in order to give more functionality to the client.
 */
public class BMClient extends AbstractClient {

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host The server to connect to.
	 * @param port The port number to connect on.
	 */

	public BMClient(String host, int port) throws IOException {
		super(host, port); // Call the superclass constructor
		openConnection();
	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	public void handleMessageFromServer(Object msg) {
		OrderMessage orderMsg = (OrderMessage)msg;
		Order requsetOrder = orderMsg.getOrder();
		System.out.println(requsetOrder.getOrderAddress());
		System.out.println(requsetOrder.getOrderNumber());
		System.out.println(requsetOrder.getPhoneNumber());
	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message The message from the UI.
	 */
	public void handleMessageFromClientUI(String message) {
		
		int orderNum = Integer.valueOf(message);
		OrderMessage order = new OrderMessage(null,RequestType.GET_ORDER,new Order(orderNum));
		try {
			sendToServer(order);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("error sending message to server");
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}
}
