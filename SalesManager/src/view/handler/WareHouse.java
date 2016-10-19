package view.handler;

import org.hibernate.mapping.Property;

import com.jfoenix.controls.*;

import dao.hibernate_adapters.DramAdapter;
import dao.hibernate_adapters.ItemAdapter;
import helper.List2ObList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Item;

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
    void Search_Click() {

    }

    @FXML
    void initialize() {
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'WareHouse.fxml'.";
        assert btn_search != null : "fx:id=\"btn_search\" was not injected: check your FXML file 'WareHouse.fxml'.";
        assert tb_ListItem != null : "fx:id=\"tb_ListItem\" was not injected: check your FXML file 'WareHouse.fxml'.";
        assert tb_cName != null : "fx:id=\"tb_cName\" was not injected: check your FXML file 'WareHouse.fxml'.";
        assert tb_cNum != null : "fx:id=\"tb_cNum\" was not injected: check your FXML file 'WareHouse.fxml'.";
        
        tb_cName.setCellValueFactory(new PropertyValueFactory<pojo.Item,String>("name"));
        tb_cNum.setCellValueFactory(new PropertyValueFactory<pojo.Item,Integer>("num"));
        tb_ListItem.setItems(List2ObList.L2OL(ItemAdapter.getAll()));
    }
}
