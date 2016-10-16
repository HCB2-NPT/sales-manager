package view.handler;

import java.io.IOException;

import com.jfoenix.controls.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main {
    @FXML
    private JFXButton btn_Buy;
    
    @FXML
    private JFXButton btn_Sell;
    
    @FXML
    private BorderPane bp_xuly;
    
    @FXML
    private JFXButton btn_AddItems;
    
    @FXML
    private JFXButton btn_Bill;
    
    @FXML
    private JFXButton btn_Payment;
    
    @FXML
    void Buy_Click() throws IOException {
    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource( "../BuyItem.fxml"));
		Scene sc = new Scene(root,bp_xuly.getWidth(),bp_xuly.getHeight());
    	bp_xuly.getChildren().setAll(root);
    }
    
    @FXML
    void Sell_Click() throws IOException {
    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource( "../SellItem.fxml"));
		Scene sc = new Scene(root,bp_xuly.getWidth(),bp_xuly.getHeight());
    	bp_xuly.getChildren().setAll(root);
    }
    
    @FXML
    void AddItems_Click() throws IOException {
    	//BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource( "../.fxml"));
		//Scene sc = new Scene(root,bp_xuly.getWidth(),bp_xuly.getHeight());
    	//bp_xuly.getChildren().setAll(root);
    }
    
    @FXML
    void Bill_Click() throws IOException {
    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource( "../Bills.fxml"));
		Scene sc = new Scene(root,bp_xuly.getWidth(),bp_xuly.getHeight());
    	bp_xuly.getChildren().setAll(root);
    }
    
    @FXML
    void Payment_Click() throws IOException {
    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource( "../Payment.fxml"));
		Scene sc = new Scene(root,bp_xuly.getWidth(),bp_xuly.getHeight());
    	bp_xuly.getChildren().setAll(root);
    }
}
