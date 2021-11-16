package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBController {

	private static Connection conn;
	private String dbName, user, password;
	private boolean connected = false;

	public DBController(String dbName, String user, String password) {
		this.dbName = dbName;
		this.password = password;
		this.user = user;
	}

	public void connectToDBServer() {
		if (connected)
			return;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Driver definition succeed");
		} catch (Exception ex) {
			/* handle the error */
			System.out.println("Driver definition failed");
		}
		try {

			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/" + dbName + "?serverTimezone=IST", user,password);
			System.out.println("SQL connection succeed");
		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		connected = true;
	}

	public Message getOrderDataFromDB(int orderNumber) {
		PreparedStatement ps;
		Message result;
		
		if(!connected) {
			connectToDBServer();
		}
		
		try {
			
			ps = conn.prepareStatement("SELECT * FROM prototype.order WHERE OrderNumber = ?");
			ps.setInt(1, orderNumber);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				System.out.println("didnt find order when orderNumber ="+ orderNumber);
				result = new ErrorMessage(null,"order","no order found with orderNumber = "+ orderNumber);
				return result;
			}
			result = new OrderMessage(null,RequestType.GET_ORDER,rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
			rs.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ErrorMessage(null,"order","Server Error");
		}
	}
	
	public void updateDetailsInOrder(OrderMessage msg) {
		if(!connected) {
			connectToDBServer();
		}
		Order order = msg.getOrder();
		Order currentOrderValue = null;
		try {
			if(order.getOrderState().equals("INCOMPLETE")) {
				//get current order that's saved in DB
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM prototype.order WHERE OrderNumber = ?");
				ps.setInt(1, order.getOrderNumber());
				ResultSet rs = ps.executeQuery();
				if(!rs.next()) {
					System.out.println("didnt find order when orderNumber ="+ order.getOrderNumber());
					//TODO CHECK ERROR AND DO RESPONSE
				}
				currentOrderValue = new Order(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				if(order.getOrderAddress().equals("")) order.setOrderAddress(currentOrderValue.getOrderAddress());
				if(order.getOrderTime().equals("")) order.setOrderTime(currentOrderValue.getOrderTime());
				if(order.getTypeOfOrder().equals("")) order.setTypeOfOrder(currentOrderValue.getTypeOfOrder());
				if(order.getPhoneNumber().equals("")) order.setPhoneNumber(currentOrderValue.getPhoneNumber());
				if(order.getRestaurantName().equals("")) order.setRestaurantName(currentOrderValue.getRestaurantName());
			}
			PreparedStatement ps = conn.prepareStatement("UPDATE prototype.order SET Restaurant = ? ,OrderTime = ? ,PhoneNumber = ? , TypeOfOrder = ?,OrderAddress = ? WHERE OrderNumber = ?");
			ps.setString(1, order.getRestaurantName());
			ps.setString(2, order.getOrderTime());
			ps.setString(3, order.getPhoneNumber());
			ps.setString(4, order.getTypeOfOrder());
			ps.setString(5, order.getOrderAddress());
			ps.setInt(6, order.getOrderNumber());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void disconnectFromDB() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connected = false;
	}
	
	
	
	

}
