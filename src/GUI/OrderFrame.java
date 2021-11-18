package GUI;


import javafx.fxml.FXML;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

public class OrderFrame {

    @FXML
    private TextField orderNumTxt;

    @FXML
    private Button showBtn;

    @FXML
    private ComboBox cmbType;

    @FXML
    private TextField addressTxt;

    @FXML
    private Button updateBtn;
    
    @FXML
    private TextField upOrderNUmTxt;
    
    ObservableList<String> list;
    
    
 // creating list of Faculties
 	private void setFacultyComboBox() {
 		ArrayList<String> al = new ArrayList<String>();
 		al.add("Order-in");
 		al.add("Take-out");

 		list = FXCollections.observableArrayList(al);
 		cmbType.setItems(list);
 	}

    @FXML
    void Show(ActionEvent event) {

    }

    @FXML
    void Update(ActionEvent event) {

    }

}

