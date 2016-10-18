package view.handler;

import helper.AppUtil;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Guide {
	static FXMLLoader _formLoader = null;
    static Stage _currentStage = null;
    public static void callGuide(){
    	if (_currentStage == null){
			_formLoader = AppUtil.callFXMLLoader("../view/Guide.fxml");
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
}
