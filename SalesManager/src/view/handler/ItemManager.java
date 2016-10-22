package view.handler;

import java.io.File;
import org.apache.log4j.Logger;
import com.jfoenix.controls.JFXTextField;
import dao.hibernate_adapters.CategoryAdapter;
import dao.hibernate_adapters.DramAdapter;
import dao.hibernate_adapters.ItemAdapter;
import helper.ITableCellEvent;
import helper.JavaAppProjectURL;
import helper.ObservableListConverter;
import helper.MessageBox;
import helper.TableViewHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import pojo.Item;

public class ItemManager {
	private static final Logger logger = Logger.getLogger(ItemManager.class);
	
	@FXML
    private TableView<pojo.Item> table;

    @FXML
    private TableColumn<pojo.Item, Integer> cId;

    @FXML
    private TableColumn<pojo.Item, String> cName;

    @FXML
    private TableColumn<pojo.Item, String> cPrice;

    @FXML
    private TableColumn<pojo.Item, Integer> cNumber;

    @FXML
    private TableColumn<pojo.Item, pojo.Category> cCat;

    @FXML
    private TableColumn<pojo.Item, pojo.Dram> cDram;
    
    @FXML
    private TableColumn<pojo.Item, String> cImg;
    
    @FXML
    private TableColumn<pojo.Item, Boolean> cShowImg;

    @FXML
    private TableColumn<pojo.Item, String> cState;

    @FXML
    private TableColumn<pojo.Item, Boolean> cButtonRemove;

    @FXML
    private JFXTextField _searchBox;
    
    @FXML
    void save() {
    	for (pojo.Item p : table.getItems()) {
    		System.out.println(p.getName());
			if (p.getCreated()){
				ItemAdapter.insert(p);
				logger.info("Save Account: " + p.getName());
			}else if (p.getEdited()){
				ItemAdapter.update(p);
				logger.info("Update Account: " + p.getName());
			}
		}
    	Main.callMsg("Save success!");
    	logger.info("Save");
		refresh();
    }
    
    @FXML
    void addNew() {
    	pojo.Item newItem = new pojo.Item();
    	newItem.setCreated(true);
    	table.getItems().add(newItem);
    	table.getSelectionModel().select(newItem);
    	table.scrollTo(newItem);
    	table.refresh();
    }

    @FXML
    void search() {
    	if (!_searchBox.getText().equals("")) {
    		FocusItem(_searchBox.getText().toLowerCase());
    	}
    }
    
    @FXML
    void refresh() {
    	table.setItems(ObservableListConverter.L2OL(ItemAdapter.getAll()));
    }
    
    @FXML
    void searchEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER && !_searchBox.getText().equals("")) {
			FocusItem(_searchBox.getText().toLowerCase());
		}
    }
    
    public void FocusItem(String s){
    	ObservableList<Item> list = FXCollections.observableArrayList();
    	for (Item i : table.getItems()) {
			if (i.getName().toLowerCase().contains(s)) {
				list.add(i);
			}
		}
    	if (list.size()<=0) {
    		MessageBox.Show("Can't find item have name ''" + s +"''!", "Alert");
    		return;
		}
    	table.getItems().clear();
    	table.setItems(list);
    }

    @FXML
    void initialize() {
    	assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'ItemManager.fxml'.";
        assert cId != null : "fx:id=\"cId\" was not injected: check your FXML file 'ItemManager.fxml'.";
        assert cName != null : "fx:id=\"cName\" was not injected: check your FXML file 'ItemManager.fxml'.";
        assert cPrice != null : "fx:id=\"cPrice\" was not injected: check your FXML file 'ItemManager.fxml'.";
        assert cNumber != null : "fx:id=\"cNumber\" was not injected: check your FXML file 'ItemManager.fxml'.";
        assert cCat != null : "fx:id=\"cCat\" was not injected: check your FXML file 'ItemManager.fxml'.";
        assert cDram != null : "fx:id=\"cDram\" was not injected: check your FXML file 'ItemManager.fxml'.";
        assert cState != null : "fx:id=\"cState\" was not injected: check your FXML file 'ItemManager.fxml'.";
        assert cButtonRemove != null : "fx:id=\"cButtonRemove\" was not injected: check your FXML file 'ItemManager.fxml'.";
        assert _searchBox != null : "fx:id=\"_searchBox\" was not injected: check your FXML file 'ItemManager.fxml'.";
        
        cId.setCellValueFactory(TableViewHelper.getPropertyValueFactory("itemId"));
        cName.setCellValueFactory(TableViewHelper.getPropertyValueFactory("name"));
        cName.setCellFactory(TableViewHelper.getCellFactory());
        cName.setOnEditCommit(
            new EventHandler<CellEditEvent<pojo.Item, String>>() {
                @Override
                public void handle(CellEditEvent<pojo.Item, String> t) {
                	pojo.Item i = (pojo.Item)t.getTableView().getItems().get(t.getTablePosition().getRow());
                	if (t.getNewValue().equals(i.getName()))
                		return;
                    i.setName(t.getNewValue());
                    i.setEdited(true);
                    table.refresh();
                }
             }
        );
        cPrice.setCellValueFactory(TableViewHelper.getPropertyValueFactory("costFormat"));
        cPrice.setCellFactory(TableViewHelper.getCellFactory());
        cPrice.setOnEditCommit(
            new EventHandler<CellEditEvent<pojo.Item, String>>() {
                @Override
                public void handle(CellEditEvent<pojo.Item, String> t) {
                	pojo.Item i = (pojo.Item)t.getTableView().getItems().get(t.getTablePosition().getRow());
                	if (t.getNewValue().equals(i.getName()))
                		return;
                    i.setCostFormat(t.getNewValue());
                    i.setEdited(true);
                    table.refresh();
                }
             }
        );
        cNumber.setCellValueFactory(TableViewHelper.getPropertyValueFactory("num"));
        cCat.setCellValueFactory(TableViewHelper.getPropertyValueFactory("categoryFormat"));
        cCat.setCellFactory(TableViewHelper.getComboBoxCellFactory(ObservableListConverter.L2OL(CategoryAdapter.getAll()), new ITableCellEvent() {
			@Override
			public void commit(Integer index, Object newValue) {
				pojo.Item i = table.getItems().get(index);
				i.setCat((pojo.Category)newValue);
				i.setEdited(true);
				table.refresh();
			}
		}));
        cDram.setCellValueFactory(TableViewHelper.getPropertyValueFactory("dramFormat"));
        cDram.setCellFactory(TableViewHelper.getComboBoxCellFactory(ObservableListConverter.L2OL(DramAdapter.getAll()), new ITableCellEvent() {
			@Override
			public void commit(Integer index, Object newValue) {
				pojo.Item i = table.getItems().get(index);
				i.setDram((pojo.Dram)newValue);
				i.setEdited(true);
				table.refresh();
			}
		}));
        cImg.setCellValueFactory(TableViewHelper.getPropertyValueFactory("img"));
        cImg.setCellFactory(TableViewHelper.getButtonTextCellFactory(null, "../view/img/File Filled-48.png", new ITableCellEvent() {
			@Override
			public void commit(Integer index, Object newValue) {
				pojo.Item i = table.getItems().get(index);
				FileChooser fileChooser = new FileChooser();
		    	fileChooser.setTitle("Select Image");
		    	fileChooser.setInitialDirectory(new File(JavaAppProjectURL.FOLDER_TARGET));
		    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files (*.png/jpg/jpeg)", "*.png", "*.jpg", "*.jpeg"));
		    	File selectedFile = fileChooser.showOpenDialog(Main._currentStage);
		    	if (selectedFile != null) {
		    		i.setImg(selectedFile.getName());
		    		i.setEdited(true);
		    		table.refresh();
		    	}
			}
		}));
        cShowImg.setCellValueFactory(TableViewHelper.getPropertyValueFactory("canShowImg"));
        cShowImg.setCellFactory(TableViewHelper.getButtonCellFactory("Show", "../view/img/Image File-48.png", new ITableCellEvent() {
			@Override
			public void commit(Integer index, Object newValue) {
				pojo.Item i = table.getItems().get(index);
				ShowImg.callShowImg();
				ShowImg.showImg(i.getImg());
			}
		}));
        cState.setCellValueFactory(TableViewHelper.getPropertyValueFactory("objectState"));
        cButtonRemove.setCellValueFactory(TableViewHelper.getPropertyValueFactory("created"));
        cButtonRemove.setCellFactory(TableViewHelper.getButtonCellFactory(null, "../view/img/Delete-48 (Red).png", new ITableCellEvent() {
			@Override
			public void commit(Integer index, Object newValue) {
				table.getItems().remove(table.getItems().get(index));
			}
		}));
        table.setItems(ObservableListConverter.L2OL(ItemAdapter.getAll()));
    }
}
