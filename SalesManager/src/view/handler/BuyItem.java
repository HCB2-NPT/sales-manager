package view.handler;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;

import org.apache.log4j.Logger;

import com.jfoenix.controls.*;
import application.AppSession;
import dao.hibernate_adapters.ImportAdapter;
import dao.hibernate_adapters.ImportExtAdapter;
import dao.hibernate_adapters.ItemAdapter;
import dao.hibernate_adapters.ProviderAdapter;
import helper.FXUtil_Autocomplete;
import helper.ITableCellEvent;
import helper.ObservableListConverter;
import helper.TableViewHelper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pojo.ImportExt;
import javafx.scene.control.TableColumn.CellEditEvent;

public class BuyItem {
	private static Logger logger = Logger.getLogger(BuyItem.class);
	
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
    private TableColumn<pojo.ImportExt, String> cNum;

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
    	pojo.Provider p = _pro.getSelectionModel().getSelectedItem();
    	pojo.Item i = _item.getSelectionModel().getSelectedItem();
    	if (p == null || i == null)
    		return;
    	for (pojo.ImportExt ie : table.getItems()) {
			if (ie.getItem().getItemId() == i.getItemId() && ie.getProvider().getProviderId() == p.getProviderId()){
				table.getSelectionModel().clearSelection();
				table.getSelectionModel().select(ie);
				table.scrollTo(ie);
				return;
			}
		}
    	pojo.ImportExt ie = new pojo.ImportExt();
    	ie.setProvider(p);
    	ie.setItem(i);
    	ie.setNum(1);
    	ie.setCreated(true);
    	table.getItems().add(ie);
    	table.refresh();
    	_total.setText(total());
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
    	pojo.Import im = new pojo.Import();
    	im.setCreaterId(AppSession._currentUser.getAccountId());
    	im.setDate(new Date());
    	if (ImportAdapter.insert(im)){
    		pojo.Import i = ImportAdapter.getCreatedLast();
    		if (i != null){
		    	int importId = i.getImportId();
		    	for (pojo.ImportExt ie : table.getItems()) {
		    		ie.setImportId(importId);
		    		ie.setProviderId(ie.getProvider().getProviderId());
		    		ie.setItemId(ie.getItem().getItemId());
					ImportExtAdapter.insert(ie);
					ItemAdapter.addNumber(ie.getNum(), ie.getItemId());
				}
		    	refresh();
		    	logger.info("Save new Import! Id-" + importId);
    		}
    	}
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
        cPrice.setCellFactory(TableViewHelper.getCellFactory());
        cPrice.setOnEditCommit(
            new EventHandler<CellEditEvent<pojo.ImportExt, String>>() {
                @Override
                public void handle(CellEditEvent<pojo.ImportExt, String> t) {
                	pojo.ImportExt i = (pojo.ImportExt)t.getTableView().getItems().get(t.getTablePosition().getRow());
                    i.setCostFormat(t.getNewValue());
                    i.setEdited(true);
                    table.refresh();
                    _total.setText(total());
                }
             }
        );
        cNum.setCellValueFactory(TableViewHelper.getPropertyValueFactory("num"));
        cNum.setCellFactory(TableViewHelper.getCellFactory());
        cNum.setOnEditCommit(
            new EventHandler<CellEditEvent<pojo.ImportExt, String>>() {
                @Override
                public void handle(CellEditEvent<pojo.ImportExt, String> t) {
                	int value;
                	try {value = Integer.valueOf(t.getNewValue());}catch(Exception e){table.refresh();return;}
                	pojo.ImportExt i = (pojo.ImportExt)t.getTableView().getItems().get(t.getTablePosition().getRow());
	                i.setNum(value);
	                i.setEdited(true);
                    table.refresh();
                    _total.setText(total());
                }
             }
        );
        cTotalPrice.setCellValueFactory(TableViewHelper.getPropertyValueFactory("totalPrice"));
        cButtonRemove.setCellValueFactory(TableViewHelper.getPropertyValueFactory("created"));
        cButtonRemove.setCellFactory(TableViewHelper.getButtonCellFactory(null, "../view/img/Delete-48 (Red).png", new ITableCellEvent() {
			@Override
			public void commit(Integer index, Object newValue) {
				table.getItems().remove(table.getItems().get(index));
			}
		}));
        
        _dateCreate.setValue(LocalDate.now());
        _creator.setText(String.format("%1$s (Id-%2$s)", AppSession._currentUser.getName(), AppSession._currentUser.getAccountId()));
        
        refresh();
        
        new FXUtil_Autocomplete<pojo.Provider>(_pro);
        new FXUtil_Autocomplete<pojo.Item>(_item);
        
        _total.setText(total());
    }
    
    String total(){
    	double s = 0;
    	for (ImportExt ie : table.getItems()) {
			s += ie.getCost() * ie.getNum();
		}
    	return new DecimalFormat("#,###.00").format(s);
    }
    
    @FXML
    void datepicker_Change() {
    	_dateCreate.setValue(LocalDate.now());
    }
}
