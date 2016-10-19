package view.handler;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import application.AppSession;
import config.AppConfig;
import dao.hibernate_adapters.AccountAdapter;
import helper.AppUtil;
import helper.FileIniCreater;
import helper.MessageBox;
import helper.Security;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Login {
	private static final Logger logger = Logger.getLogger(Login.class);
	
	@FXML
    private JFXCheckBox cbRemPass;
	
	@FXML
    private JFXTextField tfusername;

    @FXML
    private JFXPasswordField tfpassword;
    
    @FXML
    private GridPane container;
    
    @FXML
    void initialize() {
        assert tfusername != null : "fx:id=\"tfusername\" was not injected: check your FXML file 'Login.fxml'.";
        assert tfpassword != null : "fx:id=\"tfpassword\" was not injected: check your FXML file 'Login.fxml'.";
        assert cbRemPass != null : "fx:id=\"cbRemPass\" was not injected: check your FXML file 'Login.fxml'.";
        
        if (FileIniCreater.load()){
        	tfusername.setText(FileIniCreater.getValue("Username"));
        	tfpassword.setText(Security.Decrypt(FileIniCreater.getValue("Password")));
        }
    }
    
    static FXMLLoader _formLoader = null;
    static Stage _currentStage = null;
    public static void callLogin(boolean autoLogin){
		if (autoLogin){
			callSignin(AppConfig.THROGH_LOGIN_USERNAME, AppConfig.THROGH_LOGIN_PASSWORD, false);
		} else if (_currentStage == null) {
			logger.info("Start LoginForm creation.");
			_formLoader = AppUtil.callFXMLLoader("../view/Login.fxml");
			if (_formLoader != null){
				_currentStage = AppUtil.callForm(_formLoader, null);
				if (_currentStage != null){
					_currentStage.setResizable(false);
					_currentStage.centerOnScreen();
					_currentStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						public void handle(WindowEvent event) {
							callQuit();
						}
					});
					_currentStage.show();
				}
			}
			logger.info("End LoginForm creation.");
		}
    }
    
    @FXML
    void passEnter(KeyEvent event) {
    	if (event.getCode() == KeyCode.ENTER)  {
            signin();
        }
    }
    
	@FXML
    void quit() {
		callQuit();
    }
	
	static void callQuit(){
		logger.info("Exit from Login.");
		Platform.exit();
		System.exit(0);
	}

    @FXML
    public void signin() {
    	boolean rememberPass = cbRemPass.isSelected();
    	if (callSignin(tfusername.getText(), tfpassword.getText(), true)){
    		if (rememberPass){
    			FileIniCreater.setValue("Username", AppSession._currentUser.getUsername());
    			FileIniCreater.setValue("Password", AppSession._currentUser.getPassword());
    		}else{
    			FileIniCreater.setValue("Username", "");
    			FileIniCreater.setValue("Password", "");
    		}
    	}else{
    		timesLoginFail++;
    		if (timesLoginFail <= AppConfig.MAX_TIMES_LOGIN_FAIL)
    			MessageBox.Show("Username/Password is wrong!", String.format("Warning (%1$s)", timesLoginFail));
    		else{
    			MessageBox.Show("You cant login fail over three times!", "Warning");
    			callQuit();
    		}
    	}
    }
    
    int timesLoginFail = 0;
    static boolean callSignin(String username, String password, boolean errorCallback){
    	if ((AppSession._currentUser = AccountAdapter.signin(username, password)) != null){
    		Main.callMain();
    		if (_currentStage != null){
    			_currentStage.close();
    			_currentStage = null;
    		}
    	} else if (errorCallback) {
    		return false; 
    	}
    	return true;
    }
}
