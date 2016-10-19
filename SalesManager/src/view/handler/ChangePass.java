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

public class ChangePass {
	private static final Logger logger = Logger.getLogger(ChangePass.class);
	
	static FXMLLoader _formLoader = null;
    static Stage _currentStage = null;
    public static void callAbout(){
    	if (_currentStage == null){
			_formLoader = AppUtil.callFXMLLoader("../view/ChangePass.fxml");
			if (_formLoader != null){
				_currentStage = AppUtil.callForm(_formLoader, null);
				if (_currentStage != null){
					_currentStage.setResizable(false);
					_currentStage.setAlwaysOnTop(true);
					_currentStage.setX(0);
					_currentStage.setY(0);
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

    }

    @FXML
    void quit() {

    }

    @FXML
    void initialize() {
        assert user != null : "fx:id=\"user\" was not injected: check your FXML file 'ChangePass.fxml'.";
        assert opass != null : "fx:id=\"opass\" was not injected: check your FXML file 'ChangePass.fxml'.";
        assert npass != null : "fx:id=\"npass\" was not injected: check your FXML file 'ChangePass.fxml'.";
        assert cnpass != null : "fx:id=\"cnpass\" was not injected: check your FXML file 'ChangePass.fxml'.";

    }
}
