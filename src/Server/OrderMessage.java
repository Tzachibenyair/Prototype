package Server;

import ocsf.server.ConnectionToClient;

public class OrderMessage extends Message{

	private final Order order;
	//RequestType decides what action is needed for this specific message
	//e.g. for "ORDER_UPDATE" we will update existing order in DB
	private final RequestType requset;
	
	public OrderMessage(ConnectionToClient client,RequestType requset,int orderNumber,String restaurantName,String orderTime,String PhoneNumber,String TypeOfOrder,String orderAddress ) {
		super(client, TypeOfMessage.ORDER);
		this.order = new Order(orderNumber, restaurantName, orderTime, PhoneNumber, TypeOfOrder, orderAddress);
		this.requset = requset;
	}
	
	public OrderMessage(ConnectionToClient client,RequestType requset, Order order) {
		super(client, TypeOfMessage.ORDER);
		this.order = order;
		this.requset = requset;
	}

	
	public Order getOrder() {
		return order;
	}
	
	public RequestType getRequset() {
		return requset;
	}
	

}
