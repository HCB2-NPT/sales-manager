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
    private BorderPane bp_xuly;
    
    @FXML
    void Buy_Click() throws IOException {
    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource( "../BuyItem.fxml"));
		Scene sc = new Scene(root,bp_xuly.getWidth(),bp_xuly.getHeight());
    	bp_xuly.getChildren().setAll(root);
		
		
		
    }
}
