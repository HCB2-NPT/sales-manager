package view.handler;

import java.io.File;

import com.jfoenix.controls.*;

import helper.AppUtil;
import helper.JavaAppProjectURL;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.FileChooser.ExtensionFilter;

public class AddNewItem {
	static FXMLLoader _formLoader = null;
    static Stage _currentStage = null;
    public static void callAddNewItem(){
    	if (_currentStage == null){
			_formLoader = AppUtil.callFXMLLoader("../view/AddNewItem.fxml");
			if (_formLoader != null){
				_currentStage = AppUtil.callForm(_formLoader, null);
				if (_currentStage != null){
					_currentStage.centerOnScreen();
					_currentStage.setResizable(false);
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
    private Label _proLabel;
	
	@FXML
    private JFXTextField _name;

    @FXML
    private JFXComboBox<pojo.Provider> _pro;

    @FXML
    private JFXComboBox<pojo.Category> _cat;

    @FXML
    private JFXComboBox<pojo.Dram> _dram;

    @FXML
    private JFXTextField _price;

    @FXML
    private ImageView _img;
    private String _imgURL;

    @FXML
    void add() {
    	
    }

    @FXML
    void cancel() {
    	callQuit();
    }

    @FXML
    void selectImg() {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Image");
    	fileChooser.setInitialDirectory(new File(JavaAppProjectURL.FOLDER_TARGET));
    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files (*.png/jpg/jpeg)", "*.png", "*.jpg", "*.jpeg"));
    	File selectedFile = fileChooser.showOpenDialog(Main._currentStage);
    	if (selectedFile != null) {
    		_imgURL = selectedFile.getName();
    		_img.setImage(new Image("file:" + JavaAppProjectURL.FOLDER_TARGET + "\\img\\" + _imgURL));
    	}
    }

    @FXML
    void initialize() {
        assert _name != null : "fx:id=\"_name\" was not injected: check your FXML file 'AddNewItem.fxml'.";
        assert _pro != null : "fx:id=\"_pro\" was not injected: check your FXML file 'AddNewItem.fxml'.";
        assert _cat != null : "fx:id=\"_cat\" was not injected: check your FXML file 'AddNewItem.fxml'.";
        assert _dram != null : "fx:id=\"_dram\" was not injected: check your FXML file 'AddNewItem.fxml'.";
        assert _price != null : "fx:id=\"_price\" was not injected: check your FXML file 'AddNewItem.fxml'.";
        assert _img != null : "fx:id=\"_img\" was not injected: check your FXML file 'AddNewItem.fxml'.";
        
        
    }
}
