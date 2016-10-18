package view.handler;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import org.apache.log4j.Logger;
import com.jfoenix.controls.JFXTabPane;
import application.AppSession;
import helper.AppUtil;
import helper.MessageBox;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main {
	private static final Logger logger = Logger.getLogger(Main.class);
    
	static FXMLLoader _formLoader = null;
    static Stage _currentStage = null;
    public static void callMain(){
    	if (_currentStage == null){
			logger.info("Start MainForm creation.");
			_formLoader = AppUtil.callFXMLLoader("../view/Main.fxml");
			if (_formLoader != null){
				_currentStage = AppUtil.callForm(_formLoader, null);
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
    
    public static void callMsg(String msg){
    	if (_formLoader != null){
    		((Main)_formLoader.getController()).notifyMsg(msg);
    	}
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
        notifyMsg("Welcome to our application and Thanks for using.");
        
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
    
    public void notifyMsg(String msg){
    	_msg.setText(msg);
    }

    @FXML
    void about() {
    	About.callAbout();
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
    	Contact.callContact();
    }

    @FXML
    void createInvoice() {
    	Tab t = callTab("Sell Item", "../view/SellItem.fxml");
    	_tabpane.getTabs().add(t);
    	_tabpane.getSelectionModel().select(t);
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
    	Guide.callGuide();
    }

    @FXML
    void openLog() throws IOException {
    	new ProcessBuilder("Notepad.exe", "log4j-application.log").start();
    }

    @FXML
    void debt() {

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
    	Tab t = callTab("Payment", "../view/Payment.fxml");
    	_tabpane.getTabs().add(t);
    	_tabpane.getSelectionModel().select(t);
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
    void dbbackup() {
    	logger.info("Start backup database!");
    	notifyMsg("Backup database...");
    	Process p = helper.DatabaseManager.Backupdbtosql();
    	try {
			p.waitFor();
			notifyMsg("Backup success!");
			logger.warn("Backup success!");
		} catch (InterruptedException e) {
			notifyMsg("Backup interrupted.");
			logger.warn("Backup interrupted.");
		}
    	logger.info("End backup database!");
    }
    
    @FXML
    void dbrestore() throws URISyntaxException {
    	logger.info("Start restore database!");
    	notifyMsg("Select a backup for restoring!");
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Database");
    	CodeSource codeSource = application.Main.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        String jarDir = jarFile.getParentFile().getPath();
    	fileChooser.setInitialDirectory(new File(jarDir));
    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("SQL Files", "*.sql"));
    	File selectedFile = fileChooser.showOpenDialog(_currentStage);
    	if (selectedFile != null) {
    		notifyMsg("Start restore...");
    		logger.info("Start restore by " + selectedFile.getPath());
    		Process p = helper.DatabaseManager.Restoredbfromsql(selectedFile.getPath());
    		try {
				p.waitFor();
				notifyMsg("Restore success!");
	    		logger.info("End restore database!");
			} catch (InterruptedException e) {
				notifyMsg("Restore interrupted!");
	    		logger.info("Restore interrupted!");
			}
    	}else{
    		notifyMsg("Cancel restore.");
    		logger.info("Cancel restore database!");
    	}
    }
}
