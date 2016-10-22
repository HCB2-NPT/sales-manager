package view.handler;

import java.awt.MouseInfo;
import helper.AppUtil;
import helper.JavaAppProjectURL;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class ShowImg {
	static FXMLLoader _formLoader = null;
    static Stage _currentStage = null;
    public static void callShowImg(){
    	if (_currentStage == null){
			_formLoader = AppUtil.callFXMLLoader("../view/ShowImg.fxml");
			if (_formLoader != null){
				_currentStage = AppUtil.callForm(_formLoader, null);
				if (_currentStage != null){
					_currentStage.initStyle(StageStyle.UNDECORATED);
					_currentStage.setResizable(false);
					_currentStage.setAlwaysOnTop(true);
					_currentStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						public void handle(WindowEvent event) {
							callQuit();
						}
					});
					_currentStage.setOnShown(new EventHandler<WindowEvent>() {
						@Override
						public void handle(WindowEvent event) {
							_currentStage.setX(MouseInfo.getPointerInfo().getLocation().getX() - _currentStage.getWidth() / 2);
							_currentStage.setY(MouseInfo.getPointerInfo().getLocation().getY() - _currentStage.getHeight() / 2);
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
    
    public static void showImg(String imgPath){
    	if (_currentStage != null){
    		//((ShowImg)_formLoader.getController())._image.setImage(new Image("file:" + imgPath));
    		try{
	            String folderPath = JavaAppProjectURL.FOLDER_TARGET + "\\img\\";
	    		((ShowImg)_formLoader.getController())._image.setImage(new Image("file:" + folderPath + imgPath));
    		}catch(Exception e){}
    	}
    }
    
    @FXML
    private ImageView _image;
    
    @FXML
    void mouseOut(MouseEvent event) {
    	callQuit();
    }

    @FXML
    void initialize() {
        assert _image != null : "fx:id=\"_image\" was not injected: check your FXML file 'ShowImg.fxml'.";

    }
}
