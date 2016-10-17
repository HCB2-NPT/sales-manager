package view.handler;

import java.io.IOException;
import org.apache.log4j.Logger;
import com.jfoenix.controls.JFXTabPane;
import application.AppSession;
import application.AppUtil;
import config.AppConfig;
import helper.MessageBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main {
	private static final Logger logger = Logger.getLogger(Main.class);
    
    static Stage _currentStage = null;
    public static void callMain(){
    	if (_currentStage == null){
			logger.info("Start MainForm creation.");
			_currentStage = AppUtil.callForm("../view/Main.fxml", null);
			if (_currentStage != null){
				_currentStage.setMinWidth(900);
				_currentStage.setMinHeight(600);
				_currentStage.setMaximized(true);
				_currentStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent event) {
						callQuit(false);
					}
				});
				_currentStage.show();
		    }
			logger.info("End MainForm creation.");
    	}
    }
    
    static void callQuit(boolean logout){
    	if (logout){
    		logger.info("Logout from MainForm.");
	    	Login.callLogin(false);
	    	if (_currentStage != null){
	    		_currentStage.close();
	    		_currentStage = null;
	    	}
    	}else{
    		logger.info("Exit from MainForm.");
    		Platform.exit();
    		System.exit(0);
    	}
    }
    
    static Tab callTab(String name, String fxmlPath){
    	Tab t = null;
    	Pane p = null;
    	if (name.equals("Home"))
    		name = "Home_";
    	if ((p = AppUtil.callPane(fxmlPath)) != null){
    		t = new Tab();
    		t.setText(name);
    		t.setContent(p);
    		logger.info("Create a tab " + name);
    	}else{
    		logger.error("Can not create a tab with " + fxmlPath);
    	}
    	return t;
    }
    
    @FXML
    private JFXTabPane _tabMain;
    
    @FXML
    private JFXTabPane _tabpane;
    
    @FXML
    private Label _user;

    @FXML
    private Label _msg;
    
    @FXML
    void initialize() {
        assert _tabpane != null : "fx:id=\"_tabpane\" was not injected: check your FXML file 'Main.fxml'.";
        assert _user != null : "fx:id=\"_user\" was not injected: check your FXML file 'Main.fxml'.";
        assert _msg != null : "fx:id=\"_msg\" was not injected: check your FXML file 'Main.fxml'.";
        _user.setText(String.format("Current User: %1$s (Id-%2$s)", AppSession._currentUser.getName(), AppSession._currentUser.getAccountId()));
        _msg.setText("Welcome to our application and Thanks for using.");
        
        if (AppSession._currentUser.getPermission().getName().equals("Personel")){
        	_tabMain.getTabs().remove(1); //remove 1
        	_tabMain.getTabs().remove(1); //remove 2
        	_tabMain.getTabs().remove(1); //remove 3
        } else if (AppSession._currentUser.getPermission().getName().equals("Manager")) {
        	_tabMain.getTabs().remove(3); //remove 3
        } else if (AppSession._currentUser.getPermission().getName().equals("Administrator")) {
        	_tabMain.getTabs().remove(0); //remove 0
        	_tabMain.getTabs().remove(0); //remove 1
        	_tabMain.getTabs().remove(0); //remove 2
        }
    }

    @FXML
    void about() {
    	
    }

    @FXML
    void acc() {

    }

    @FXML
    void acc_admin() {

    }
    
    @FXML
    void cat() {
    	Tab t = callTab("Category", "../view/Category.fxml");
    	_tabpane.getTabs().add(t);
    	_tabpane.getSelectionModel().select(t);
    }

    @FXML
    void changePass() {

    }

    @FXML
    void clearTabs() {
    	Tab temp[] = new Tab[_tabpane.getTabs().size()];
    	temp = _tabpane.getTabs().toArray(temp);
    	for (Tab t : temp) {
    		if (!t.getText().equals("Home"))
    			_tabpane.getTabs().remove(t);
    	}
    }
    
    @FXML
    void closeTab() {
    	for (Tab t : _tabpane.getTabs()) {
    		System.out.println(t.getText());
			if (t.isSelected() && !t.getText().equals("Home")){
				_tabpane.getTabs().remove(t);
				break;
			}
		}
    }

    @FXML
    void contact() {

    }

    @FXML
    void createInvoice() {

    }

    @FXML
    void cus() {

    }

    @FXML
    void dram() {
    	Tab t = callTab("Dram", "../view/Dram.fxml");
    	_tabpane.getTabs().add(t);
    	_tabpane.getSelectionModel().select(t);
    }

    @FXML
    void help() {

    }

    @FXML
    void inWarehouse() {
    	Tab t = callTab("Import Item", "../view/BuyItem.fxml");
    	_tabpane.getTabs().add(t);
    	_tabpane.getSelectionModel().select(t);
    }

    @FXML
    void item() {

    }

    @FXML
    void logout() {
    	callQuit(true);
    }

    @FXML
    void myinfo() {

    }

    @FXML
    void openLog() throws IOException {
    	new ProcessBuilder("Notepad.exe", "log4j-application.log").start();
    }

    @FXML
    void pay() {

    }

    @FXML
    void perm() {
    	Tab t = callTab("Permission", "../view/Permission.fxml");
    	_tabpane.getTabs().add(t);
    	_tabpane.getSelectionModel().select(t);
    }

    @FXML
    void pro() {
    	Tab t = callTab("Provider", "../view/Provider.fxml");
    	_tabpane.getTabs().add(t);
    	_tabpane.getSelectionModel().select(t);
    }

    @FXML
    void rpIn() {
    	MessageBox.Show("Coming Soon!", "Alert");
    }

    @FXML
    void rpIncome() {
    	MessageBox.Show("Coming Soon!", "Alert");
    }

    @FXML
    void rpOut() {
    	MessageBox.Show("Coming Soon!", "Alert");
    }

    @FXML
    void viewImport() {

    }

    @FXML
    void viewInvoice() {
    	Tab t = callTab("WareHouse", "../view/Bills.fxml");
    	_tabpane.getTabs().add(t);
    	_tabpane.getSelectionModel().select(t);
    }

    @FXML
    void viewWarehouse() {
    	Tab t = callTab("WareHouse", "../view/WareHouse.fxml");
    	_tabpane.getTabs().add(t);
    	_tabpane.getSelectionModel().select(t);
    }
    
    @FXML
    void dbbackup() throws IOException {
    	helper.DatabaseManager.Backupdbtosql();
    }
    
    @FXML
    void dbrecover() throws IOException {
    	helper.DatabaseManager.Restoredbfromsql("backup.sql");
    }
}
