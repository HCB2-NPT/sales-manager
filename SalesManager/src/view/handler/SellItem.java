package view.handler;

import java.time.LocalDate;
import java.util.Date;

import org.jboss.logging.Message;

import com.jfoenix.controls.*;

import dao.hibernate_adapters.ItemAdapter;
import helper.FXUtil_Autocomplete;
import helper.List2ObList;
import helper.MessageBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.*;
import pojo.Item;

public class SellItem {
	@FXML
    private JFXComboBox<String> cb_nameitem;

    @FXML
    private JFXTextField txt_namecustomer;

    @FXML
    private JFXDatePicker datepicker_create;

    @FXML
    private TableView<Item> table_insertitem;

    @FXML
    private JFXTextField txt_totalmoney;

    @FXML
    private JFXTextField txt_paymoney;

    @FXML
    private JFXTextField txt_returnmoney;

    @FXML
    void btnAdd_Click() {

    }

    @FXML
    void btnFinish_Click() {

    }
    @FXML
    public void cbMouse_Click(){
    	
    }

    @FXML
    void initialize() {
        assert cb_nameitem != null : "fx:id=\"cb_nameitem\" was not injected: check your FXML file 'SellItem.fxml'.";
        assert txt_namecustomer != null : "fx:id=\"txt_namecustomer\" was not injected: check your FXML file 'SellItem.fxml'.";
        assert datepicker_create != null : "fx:id=\"datepicker_create\" was not injected: check your FXML file 'SellItem.fxml'.";
        assert table_insertitem != null : "fx:id=\"table_insertitem\" was not injected: check your FXML file 'SellItem.fxml'.";
        assert txt_totalmoney != null : "fx:id=\"txt_totalmoney\" was not injected: check your FXML file 'SellItem.fxml'.";
        assert txt_paymoney != null : "fx:id=\"txt_paymoney\" was not injected: check your FXML file 'SellItem.fxml'.";
        assert txt_returnmoney != null : "fx:id=\"txt_returnmoney\" was not injected: check your FXML file 'SellItem.fxml'.";

        datepicker_create.setValue(LocalDate.now());
        ObservableList<String> listitem = FXCollections.observableArrayList();
        for (Item item : List2ObList.L2OL(ItemAdapter.getAll())) {
			listitem.add(item.getName());
		}
        
		cb_nameitem.getItems().addAll(listitem);
		new FXUtil_Autocomplete(cb_nameitem);
        //cb_nameitem.getItems().add(listitem);
        //cb_nameitem = new JFXComboBox<Item>(listitem);
        //helper.FXUtil_Autocomplete.autoCompleteComboBoxPlus(cb_nameitem, (typedText, itemToCompare) -> itemToCompare.getName().toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.getAge().toString().equals(typedText));
        //FXUtil_Autocomplete.getComboBoxValue(cb_nameitem);
    }
}
