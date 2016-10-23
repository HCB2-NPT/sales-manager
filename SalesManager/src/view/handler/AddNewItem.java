package view.handler;

import java.io.File;

import org.apache.log4j.Logger;

import com.jfoenix.controls.*;

import dao.hibernate_adapters.CategoryAdapter;
import dao.hibernate_adapters.DramAdapter;
import dao.hibernate_adapters.ItemAdapter;
import helper.AppUtil;
import helper.FXUtil_Autocomplete;
import helper.JavaAppProjectURL;
import helper.MessageBox;
import helper.ObservableListConverter;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
					_currentStage.showAndWait();
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
	
    private static Logger logger = Logger.getLogger(AddNewItem.class);
    
	@FXML
    private JFXTextField _name;

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
    	String n = _name.getText();
    	pojo.Category c = _cat.getSelectionModel().getSelectedItem();
    	pojo.Dram d = _dram.getSelectionModel().getSelectedItem();
    	Double cost = 0d;
    	try {cost = Double.valueOf(_price.getText());} catch (Exception e) {
    		MessageBox.Show("Item's Price just number.", "Warn");
    		return;
    	}
    	if (n.equals("") || c == null || d == null){
    		MessageBox.Show("Try again after you check fields.", "Warn");
    		return;
    	}
    	pojo.Item i = new pojo.Item();
    	i.setName(n);
    	i.setCat(c);
    	i.setDram(d);
    	i.setCost(cost);
    	i.setImg(_imgURL);
    	if (ItemAdapter.insert(i))
    		logger.info("Save a New Item into Database: " + n);
    	else
    		logger.warn("Save fail!");
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
        assert _cat != null : "fx:id=\"_cat\" was not injected: check your FXML file 'AddNewItem.fxml'.";
        assert _dram != null : "fx:id=\"_dram\" was not injected: check your FXML file 'AddNewItem.fxml'.";
        assert _price != null : "fx:id=\"_price\" was not injected: check your FXML file 'AddNewItem.fxml'.";
        assert _img != null : "fx:id=\"_img\" was not injected: check your FXML file 'AddNewItem.fxml'.";
        
        _cat.setItems(ObservableListConverter.L2OL(CategoryAdapter.getAll()));
        _dram.setItems(ObservableListConverter.L2OL(DramAdapter.getAll()));
        new FXUtil_Autocomplete<pojo.Category>(_cat);
        new FXUtil_Autocomplete<pojo.Dram>(_dram);
    }
}
