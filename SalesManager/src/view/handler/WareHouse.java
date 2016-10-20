package view.handler;

import com.jfoenix.controls.*;
import dao.hibernate_adapters.ItemAdapter;
import helper.List2ObList;
import helper.TableViewHelper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
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
    	FocusItem(txt_name.getText());
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
        /*
        tb_cName.setCellFactory(TableViewHelper.getCellFactory());
        tb_cName.setOnEditCommit(
            new EventHandler<CellEditEvent<Item, String>>() {
                @Override
                public void handle(CellEditEvent<Item, String> t) {
                	Item i = (Item)t.getTableView().getItems().get(t.getTablePosition().getRow()); 
                    i.setName(t.getNewValue());
                    i.setEdited(true);
                }
             }
        );
        */
        tb_cNum.setCellValueFactory(TableViewHelper.getPropertyValueFactory("num"));
        
        tb_cSellPrice.setCellValueFactory(TableViewHelper.getPropertyValueFactory("costFormat"));
        
        tb_ListItem.setItems(List2ObList.L2OL(ItemAdapter.getAll()));
        tb_ListItem.setEditable(true);
    }
    
    @FXML
    public void KeyPress_txtName(KeyEvent e){
    	if (e!= null) {
			if (e.getCode() == KeyCode.ENTER) {
				FocusItem(txt_name.getText());
			}
		}
    }
    
    public void FocusItem(String s){
    	int index = -1;
    	for (Item i : tb_ListItem.getItems()) {
    		index++;
			if (i.getName().contains(s)) {
				tb_ListItem.getSelectionModel().select(i);
				tb_ListItem.getFocusModel().focus(index);
			}
		}
    }
}
