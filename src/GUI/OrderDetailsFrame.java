package GUI;

import Client1.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OrderDetailsFrame{
	
	private Order order;

    @FXML
    private TextField orderNUmTxt;

    @FXML
    private TextField typeTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    private TextField timeTxt;

    @FXML
    private TextField resTxt;

    @FXML
    private TextField addressTxt;
    
    
	public void loadStudent(Order o) {
		this.order = o;
		this.orderNUmTxt.setText(String.valueOf(order.getOrderNumber()));
		this.typeTxt.setText(order.getTypeOfOrder());
		this.phoneTxt.setText(order.getPhoneNumber());
		this.resTxt.setText(order.getRestaurantName());
		this.addressTxt.setText(order.getOrderAddress());
	}

}
