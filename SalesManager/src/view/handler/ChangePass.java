package view.handler;

import org.apache.log4j.Logger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import application.AppSession;
import dao.hibernate_adapters.AccountAdapter;
import helper.AppUtil;
import helper.MessageBox;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pojo.Account;

public class ChangePass {
	private static final Logger logger = Logger.getLogger(ChangePass.class);
	
	static FXMLLoader _formLoader = null;
    static Stage _currentStage = null;
    public static void callChangePass(){
    	if (_currentStage == null){
			_formLoader = AppUtil.callFXMLLoader("../view/ChangePass.fxml");
			if (_formLoader != null){
				_currentStage = AppUtil.callForm(_formLoader, null);
				if (_currentStage != null){
					_currentStage.centerOnScreen();
					_currentStage.setResizable(false);
					_currentStage.setAlwaysOnTop(true);
					_currentStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						public void handle(WindowEvent event) {
							callQuit();
						}
					});
					_currentStage.show();
			    }
			}
    	}
    }
    
    static void callQuit(){
    	if (_currentStage != null){
    		_currentStage.close();
    		_currentStage = null;
    	}
    }
	
	@FXML
    private JFXTextField user;

    @FXML
    private JFXPasswordField opass;

    @FXML
    private JFXPasswordField npass;

    @FXML
    private JFXPasswordField cnpass;

    @FXML
    void confirm() {
    	if (npass.getText().equals(cnpass.getText())){ 
    		Account temp = AccountAdapter.signin(AppSession._currentUser.getUsername(), opass.getText());
    		if (temp != null && temp.getAccountId() == AppSession._currentUser.getAccountId()){
	    		if (AccountAdapter.changePassword(npass.getText(), AppSession._currentUser.getAccountId())){
	    			AppSession._currentUser = AccountAdapter.signin(AppSession._currentUser.getUsername(), npass.getText());
	    			Main.callMsg("Your password changed.");
	    	        logger.info(AppSession._currentUser.getAccountId() + " changed password.");
	    	        MessageBox.Show("Your password changed.", "Message");
	    		}else{
	    			Main.callMsg("Fail changing password.");
	    	        logger.info("Fail changing password.");
	    	        MessageBox.Show("Fail changing password.", "Error");
	    		}
    		}else{
    			MessageBox.Show("Oldpass wrong!", "Notify");
    		}
    	}else{
    		MessageBox.Show("Newpass ? Confirm-Newpass.", "Notify");
    	}
    }

    @FXML
    void quit() {
    	callQuit();
    	Main.callMsg("Back to MainForm.");
    }

    @FXML
    void initialize() {
        assert user != null : "fx:id=\"user\" was not injected: check your FXML file 'ChangePass.fxml'.";
        assert opass != null : "fx:id=\"opass\" was not injected: check your FXML file 'ChangePass.fxml'.";
        assert npass != null : "fx:id=\"npass\" was not injected: check your FXML file 'ChangePass.fxml'.";
        assert cnpass != null : "fx:id=\"cnpass\" was not injected: check your FXML file 'ChangePass.fxml'.";
        
        user.setText(String.format("%1$5s - %2$s", AppSession._currentUser.getAccountId(), AppSession._currentUser.getName()));
        Main.callMsg("Ready for changing your password.");
        logger.info(AppSession._currentUser.getAccountId() + " changing password...");
    }
}
