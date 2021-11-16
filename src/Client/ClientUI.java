package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientUI {

	final public static int DEFAULT_PORT = 5555;
	static BMClient client;
	
	
	public ClientUI(String host, int port) 
	  {
	    try 
	    {
	      client= new BMClient(host, port);
	    } 
	    catch(IOException exception) 
	    {
	      System.out.println("Error: Can't setup connection!"
	                + " Terminating client.");
	      System.exit(1);
	    }
	  }
	
	
	 public void accept() 
	  {
	    try
	    {
	      BufferedReader fromConsole = 
	        new BufferedReader(new InputStreamReader(System.in));
	      String message;

	      while (true) 
	      {
	        message = fromConsole.readLine();
	        client.handleMessageFromClientUI(message);
	      }
	    } 
	    catch (Exception ex) 
	    {
	      System.out.println
	        ("Unexpected error while reading from console!");
	    }
	  }
	
	public static void main(String[] args) {
		
		String host = "";
	    int port = 0;  //The port number

	    try
	    {
	      host = args[0];
	    }
	    catch(ArrayIndexOutOfBoundsException e)
	    {
	      host = "localhost";
	    }
	    ClientUI chat= new ClientUI(host, DEFAULT_PORT);
	    client.handleMessageFromClientUI("123");
	}

}
