package view.handler;

import org.apache.log4j.Logger;

import com.jfoenix.controls.*;

import config.AppConfig;
import helper.AppUtil;
import helper.FileIniCreater;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Details {
	private static final Logger logger = Logger.getLogger(Details.class);
	
	static FXMLLoader _formLoader = null;
    static Stage _currentStage = null;
    public static void callDetails(){
    	if (_currentStage == null){
			_formLoader = AppUtil.callFXMLLoader("../view/Details.fxml");
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
    private JFXTextField _name;

    @FXML
    private JFXTextArea _des;

    @FXML
    private JFXTextField _address;

    @FXML
    void save() {
    	FileIniCreater.setValue(AppConfig.DETAILS_INI, "Name", _name.getText());
    	FileIniCreater.setValue(AppConfig.DETAILS_INI, "Addr", _address.getText());
    	FileIniCreater.setValue(AppConfig.DETAILS_INI, "Desc", _des.getText());
    	logger.info("Save new shop's details.");
    }
    
    @FXML
    void initialize() {
        assert _name != null : "fx:id=\"_name\" was not injected: check your FXML file 'Details.fxml'.";
        assert _des != null : "fx:id=\"_des\" was not injected: check your FXML file 'Details.fxml'.";
        assert _address != null : "fx:id=\"_address\" was not injected: check your FXML file 'Details.fxml'.";
        
        if (FileIniCreater.load(AppConfig.DETAILS_INI, AppConfig.DETAILS_INI_FORMAT)){
        	_name.setText(FileIniCreater.getValue(AppConfig.DETAILS_INI, "Name"));
        	_address.setText(FileIniCreater.getValue(AppConfig.DETAILS_INI, "Addr"));
        	_des.setText(FileIniCreater.getValue(AppConfig.DETAILS_INI, "Desc"));
        }
    }
}
