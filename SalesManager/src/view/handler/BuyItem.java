package view.handler;

import java.time.LocalDate;
import com.jfoenix.controls.*;
import application.AppSession;
import dao.hibernate_adapters.ItemAdapter;
import dao.hibernate_adapters.ProviderAdapter;
import helper.FXUtil_Autocomplete;
import helper.ITableCellEvent;
import helper.ObservableListConverter;
import helper.TableViewHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BuyItem {
	@FXML
    private JFXComboBox<pojo.Provider> _pro;

    @FXML
    private JFXTextField _creator;

    @FXML
    private JFXDatePicker _dateCreate;

    @FXML
    private JFXComboBox<pojo.Item> _item;

    @FXML
    private TableView<pojo.ImportExt> table;

    @FXML
    private TableColumn<pojo.ImportExt, String> cName;

    @FXML
    private TableColumn<pojo.ImportExt, String> cPrice;

    @FXML
    private TableColumn<pojo.ImportExt, Integer> cNum;

    @FXML
    private TableColumn<pojo.ImportExt, String> cTotalPrice;

    @FXML
    private TableColumn<pojo.ImportExt, Boolean> cButtonRemove;

    @FXML
    private JFXTextField _total;

    @FXML
    private JFXTextField _mypay;

    @FXML
    private JFXTextField _return;

    @FXML
    void addDetail() {

    }

    @FXML
    void newItem() {
    	AddNewItem.callAddNewItem();
    	
    }

    @FXML
    void refresh() {
    	table.getItems().clear();
    	_pro.setItems(ObservableListConverter.L2OL(ProviderAdapter.getAll()));
    	_item.setItems(ObservableListConverter.L2OL(ItemAdapter.getAll()));
    }

    @FXML
    void save() {

    }

    @FXML
    void initialize() {
        assert _pro != null : "fx:id=\"_pro\" was not injected: check your FXML file 'BuyItem.fxml'.";
        assert _creator != null : "fx:id=\"_creator\" was not injected: check your FXML file 'BuyItem.fxml'.";
        assert _dateCreate != null : "fx:id=\"_dateCreate\" was not injected: check your FXML file 'BuyItem.fxml'.";
        assert _item != null : "fx:id=\"_item\" was not injected: check your FXML file 'BuyItem.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'BuyItem.fxml'.";
        assert cName != null : "fx:id=\"cName\" was not injected: check your FXML file 'BuyItem.fxml'.";
        assert cPrice != null : "fx:id=\"cPrice\" was not injected: check your FXML file 'BuyItem.fxml'.";
        assert cNum != null : "fx:id=\"cNum\" was not injected: check your FXML file 'BuyItem.fxml'.";
        assert cTotalPrice != null : "fx:id=\"cTotalPrice\" was not injected: check your FXML file 'BuyItem.fxml'.";
        assert cButtonRemove != null : "fx:id=\"cButtonRemove\" was not injected: check your FXML file 'BuyItem.fxml'.";
        assert _total != null : "fx:id=\"_total\" was not injected: check your FXML file 'BuyItem.fxml'.";
        assert _mypay != null : "fx:id=\"_mypay\" was not injected: check your FXML file 'BuyItem.fxml'.";
        assert _return != null : "fx:id=\"_return\" was not injected: check your FXML file 'BuyItem.fxml'.";
        
        cName.setCellValueFactory(TableViewHelper.getPropertyValueFactory("itemName"));
        cPrice.setCellValueFactory(TableViewHelper.getPropertyValueFactory("costFormat"));
        cNum.setCellValueFactory(TableViewHelper.getPropertyValueFactory("num"));
        cTotalPrice.setCellValueFactory(TableViewHelper.getPropertyValueFactory("totalPrice"));
        cButtonRemove.setCellValueFactory(TableViewHelper.getPropertyValueFactory("created"));
        cButtonRemove.setCellFactory(TableViewHelper.getButtonCellFactory(null, "../view/img/Delete-48 (Red).png", new ITableCellEvent() {
			@Override
			public void commit(Integer index, Object newValue) {
				table.getItems().remove(table.getItems().get(index));
			}
		}));
        
        new FXUtil_Autocomplete<pojo.Provider>(_pro);
        new FXUtil_Autocomplete<pojo.Item>(_item);
        
        _dateCreate.setValue(LocalDate.now());
        _creator.setText(String.format("%1$s (Id-%2$s)", AppSession._currentUser.getName(), AppSession._currentUser.getAccountId()));
        
        refresh();
    }
}
