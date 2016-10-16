package view.handler;

import java.io.IOException;
import org.apache.log4j.Logger;
import com.jfoenix.controls.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main {
	private static final Logger logger = Logger.getLogger(Main.class);
	
    @FXML
    private JFXButton btn_Buy;
    
    @FXML
    private JFXButton btn_Sell;
    
    @FXML
    private JFXTabPane tab_pane;
    
    @FXML
    private JFXTabPane tab_process;
    
    static Stage _currentStage = null;
    public static void callMain(){
		logger.info("Start MainForm creation.");
		_currentStage = application.Main.callForm("../view/Main.fxml", null);
		_currentStage.setMaximized(true);
		_currentStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
				callQuit();
			}
		});
		_currentStage.show();
		logger.info("End MainForm creation.");
    }
    
    static void callQuit(){
    	logger.info("Exit from MainForm.");
    	Login.callLogin(false);
    	if (_currentStage != null){
    		_currentStage.close();
    		_currentStage = null;
    	}
    }
    
    @FXML
    private JFXButton btn_AddItems;
    
    @FXML
    private JFXButton btn_Bill;
    
    @FXML
    private JFXButton btn_Payment;
    
    @FXML
    void Buy_Click() throws IOException {
    	
    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource( "../BuyItem.fxml"));
		Scene sc = new Scene(root);
		Tab t = new Tab("Buy Item",root);
		tab_process.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);
		
		tab_process.getTabs().add(t);
		t.setClosable(true);
		
    }
    
    @FXML
    void Sell_Click() throws IOException {
    	//BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource( "../SellItem.fxml"));
		//Scene sc = new Scene(root,bp_xuly.getWidth(),bp_xuly.getHeight());
    	//bp_xuly.getChildren().setAll(root);
    }
    
    @FXML
    void AddItems_Click() throws IOException {
    	//BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource( "../.fxml"));
		//Scene sc = new Scene(root,bp_xuly.getWidth(),bp_xuly.getHeight());
    	//bp_xuly.getChildren().setAll(root);
    }
    
    @FXML
    void Bill_Click() throws IOException {
    	//BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource( "../Bills.fxml"));
		//Scene sc = new Scene(root,bp_xuly.getWidth(),bp_xuly.getHeight());
    	//bp_xuly.getChildren().setAll(root);
    }
    
    @FXML
    void Payment_Click() throws IOException {
    	//BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource( "../Payment.fxml"));
		//Scene sc = new Scene(root,bp_xuly.getWidth(),bp_xuly.getHeight());
    	//bp_xuly.getChildren().setAll(root);
    }
    
}
