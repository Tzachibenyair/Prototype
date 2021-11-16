package Server;

public class OrderController {
	
//	private OrderMessage msg;
//	
//	
//	public OrderController(OrderMessage msg) {
//		this.msg = msg;
//	}
	
	private static DBController controller;
	
	public OrderController(DBController dbcontoller) {
		this.controller = dbcontoller;
		
	}
	//change from static to no-static ,because if we use this method we cant create new instance and then use this method 
	//we need to use the name of the class and than use this metho , so we cant initialize the dbcontroller in the constructor because we dont have new instance
	public Message processOrderMessageFromClient(OrderMessage message) {
		RequestType request = message.getRequset();
		switch(request) {
		case UPDATE_ORDER:
			controller.updateDetailsInOrder(message);
			break;
		case GET_ORDER:
			return controller.getOrderDataFromDB(message.getOrder().getOrderNumber());
		default:
			return new ErrorMessage(message.getSenderID(),"an know function");
			
		}
		return null;
		
	}
}
