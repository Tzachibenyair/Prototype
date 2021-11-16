package Server;

public class Order {
	
	private String restaurantName,PhoneNumber,TypeOfOrder,orderAddress,orderTime;
	private final int orderNumber;
	private String orderState;
	
	public Order(int orderNumber,String restaurantName,String orderTime,String PhoneNumber,String TypeOfOrder,String orderAddress ) {
		this.restaurantName = restaurantName;
		this.PhoneNumber = PhoneNumber;
		this.TypeOfOrder = TypeOfOrder;
		this.orderAddress = orderAddress;
		this.orderNumber  = orderNumber;
		this.orderTime = orderTime;
		this.orderState = "COMPLETE";
	}
	
	public Order(int orderNumber) {
		this(orderNumber, "", "", "", "", "");
		this.orderState = "INCOMPLETE";
	}
	
	public boolean setOrderToComplete() {
		if(restaurantName.equals("") || PhoneNumber.equals("") || TypeOfOrder.equals("") || orderAddress.equals("") || orderTime.equals("")) {
			return false;
		}
		this.orderState = "COMPLETE";
		return true;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public String getTypeOfOrder() {
		return TypeOfOrder;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setRestaurantName(String restaurantName) {
		if(restaurantName.equals("")) {
			throw new IllegalArgumentException("Setting restaurantName to empty value");
		}
		this.restaurantName = restaurantName;
	}

	public void setPhoneNumber(String phoneNumber) {
		if(phoneNumber.equals("")) {
			throw new IllegalArgumentException("Setting phoneNumber to empty value");
		}
		PhoneNumber = phoneNumber;
	}

	public void setTypeOfOrder(String typeOfOrder) {
		if(typeOfOrder.equals("")) {
			throw new IllegalArgumentException("Setting typeOfOrder to empty value");
		}
		TypeOfOrder = typeOfOrder;
	}

	public void setOrderAddress(String orderAddress) {
		if(orderAddress.equals("")) {
			throw new IllegalArgumentException("Setting orderAddress to empty value");
		}
		this.orderAddress = orderAddress;
	}

	public void setOrderTime(String orderTime) {
		if(orderTime.equals("")) {
			throw new IllegalArgumentException("Setting orderTime to empty value");
		}
		this.orderTime = orderTime;
	}
}
