package view.handler;

import com.jfoenix.controls.*;
import dao.hibernate_adapters.ItemAdapter;
import helper.ObservableListConverter;
import helper.MessageBox;
import helper.TableViewHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pojo.*;

public class WareHouse {
	@FXML
    private JFXTextField txt_name;

    @FXML
    private JFXButton btn_search;

    @FXML
    private TableView<Item> tb_ListItem;

    @FXML
    private TableColumn<Item, String> tb_cName;

    @FXML
    private TableColumn<Item, Integer> tb_cNum;

    @FXML
    private TableColumn<Item, Integer> tb_cID;

    @FXML
    private TableColumn<Item, String> tb_cSellPrice;
    
    @FXML
    void Search_Click() {
    	if (!txt_name.getText().equals("")) {
    		FocusItem(txt_name.getText());
		}
    }

    @FXML
    void initialize() {
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'WareHouse.fxml'.";
        assert btn_search != null : "fx:id=\"btn_search\" was not injected: check your FXML file 'WareHouse.fxml'.";
        assert tb_ListItem != null : "fx:id=\"tb_ListItem\" was not injected: check your FXML file 'WareHouse.fxml'.";
        assert tb_cName != null : "fx:id=\"tb_cName\" was not injected: check your FXML file 'WareHouse.fxml'.";
        assert tb_cNum != null : "fx:id=\"tb_cNum\" was not injected: check your FXML file 'WareHouse.fxml'.";
        
        tb_cID.setCellValueFactory(TableViewHelper.getPropertyValueFactory("itemId"));
        tb_cName.setCellValueFactory(TableViewHelper.getPropertyValueFactory("name"));
        tb_cNum.setCellValueFactory(TableViewHelper.getPropertyValueFactory("num"));
        tb_cSellPrice.setCellValueFactory(TableViewHelper.getPropertyValueFactory("costFormat"));
        tb_ListItem.setItems(ObservableListConverter.L2OL(ItemAdapter.getAll()));
    }
    
    @FXML
    public void KeyPress_txtName(KeyEvent e){
		if (e.getCode() == KeyCode.ENTER && !txt_name.getText().equals("")) {
			FocusItem(txt_name.getText());
		}
    }
    
    @FXML
    public void Refresh_Click(){
    	txt_name.setText("");
    	tb_ListItem.getItems().clear();
    	tb_ListItem.setItems(ObservableListConverter.L2OL(ItemAdapter.getAll()));
    }
    
    public void FocusItem(String s){
    	s = s.toLowerCase();
    	ObservableList<Item> list = FXCollections.observableArrayList();
    	for (Item i : tb_ListItem.getItems()) {
			if (i.getName().toLowerCase().contains(s)) {
				list.add(i);
			}
		}
    	if (list.size()<=0) {
    		MessageBox.Show("Can't find item have name ''" + s +"''!", "Alert");
    		return;
		}
    	tb_ListItem.getItems().clear();
    	tb_ListItem.setItems(list);
    }
}
