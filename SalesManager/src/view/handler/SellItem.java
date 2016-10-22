package view.handler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.log4j.Logger;

import com.jfoenix.controls.*;
import application.AppSession;
import dao.hibernate_adapters.CustomerAdapter;
import dao.hibernate_adapters.ItemAdapter;
import helper.FXUtil_Autocomplete;
import helper.MessageBox;
import helper.ObservableListConverter;
import helper.TableViewHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import pojo.Customer;
import pojo.Invoice;
import pojo.InvoiceExt;
import pojo.Item;

public class SellItem {
	private static final Logger logger = Logger.getLogger(SellItem.class);
	ObservableList<Item> list = FXCollections.observableArrayList();
	@FXML
    private JFXComboBox<String> cb_nameitem;
    @FXML
    private JFXTextField txt_number;
    @FXML
    private JFXButton btn_inc;
    @FXML
    private JFXButton btn_dec;
    @FXML
    private JFXTextField txt_personalcustomer;
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
    private TableColumn<Item, String> tb_cName;
    @FXML
    private TableColumn<Item, String> tb_cCat;
    @FXML
    private TableColumn<Item, String> tb_cPrice;
    @FXML
    private TableColumn<Item, Integer> tb_cNum;
    @FXML
    private TableColumn<Item, String> tb_cTotal;
    @FXML
    private TableColumn<Item, Boolean> tb_cDelete;

    @FXML
    void btndec_Click() {
    	if (Integer.valueOf(txt_number.getText())<=1) {
			return;
		}
    	int a = Integer.valueOf(txt_number.getText());
    	a--;
    	txt_number.setText(""+a);
    }

    @FXML
    void btninc_Click() {
    	if (Integer.valueOf(txt_number.getText())>=2000) {
			return;
		}
    	int a = Integer.valueOf(txt_number.getText());
    	a++;
    	txt_number.setText(""+a);
    }
    @FXML
    void btnAdd_Click() {
    	if (cb_nameitem.getSelectionModel().getSelectedItem()!=null) {
    		if (cb_nameitem.getSelectionModel().getSelectedItem().toString().equals("")) {
    			return;
    		}
        	else{
        		Item item = new Item();
        		for (Item i : list) {
    				if (i.getName().equals(cb_nameitem.getSelectionModel().getSelectedItem().toString())) {
    					item = i;
    					item.setNum(2);
    					table_insertitem.getItems().add(item);
    					table_insertitem.refresh();
    					return;
    				}
    			}
        	}	
		}
    }

    @FXML
    void number_KeyPress(KeyEvent e){
    	if (Integer.valueOf(txt_number.getText())>=2000) {
    		txt_number.setText("2000");
			return;
		}
    	if (e.getCode().isLetterKey() || e.getCode().isFunctionKey()) {
			txt_number.setEditable(false);
		}
    	else {
			txt_number.setEditable(true);
		}
    }
    @FXML
    void btnFinish_Click() throws ParseException {
    	if (txt_personalcustomer.getText().equals("")) {
			MessageBox.Show("Customer ID cannot Empty!", "Warning");
		}else{
			if (table_insertitem.getItems().size()>=1) {
				if (datepicker_create.getValue().equals(LocalDate.now())) {
					Invoice in = new Invoice();
					String instant = LocalDate.now(ZoneId.systemDefault()).toString();
				    DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
					Date res = df.parse(instant);
					
					in.setDate(res);
					in.setCreater(AppSession._currentUser);
					in.setCreated(true);
					Customer c = CustomerAdapter.get(txt_personalcustomer.getText());
					in.setCustomer(c);
					in.setIsPayment(false);
					in.setPaymentDate(null);
					
					ObservableList<InvoiceExt> list_ix = FXCollections.observableArrayList();
					for (Item item : table_insertitem.getItems()) {
						InvoiceExt ix = new InvoiceExt();
					}
				}
			}
		}
    }
    @FXML
    public void cbMouse_Click(){
    	
    }

    @FXML
    void initialize() {
        assert cb_nameitem != null : "fx:id=\"cb_nameitem\" was not injected: check your FXML file 'SellItem.fxml'.";
        assert datepicker_create != null : "fx:id=\"datepicker_create\" was not injected: check your FXML file 'SellItem.fxml'.";
        assert table_insertitem != null : "fx:id=\"table_insertitem\" was not injected: check your FXML file 'SellItem.fxml'.";
        assert txt_totalmoney != null : "fx:id=\"txt_totalmoney\" was not injected: check your FXML file 'SellItem.fxml'.";
        assert txt_paymoney != null : "fx:id=\"txt_paymoney\" was not injected: check your FXML file 'SellItem.fxml'.";
        assert txt_returnmoney != null : "fx:id=\"txt_returnmoney\" was not injected: check your FXML file 'SellItem.fxml'.";

        datepicker_create.setValue(LocalDate.now());
        ObservableList<String> listitem = FXCollections.observableArrayList();
        for (Item item : ObservableListConverter.L2OL(ItemAdapter.getAll())) {
			listitem.add(item.getName());
			list.add(item);
		}
		cb_nameitem.getItems().addAll(listitem);
		new FXUtil_Autocomplete<String>(cb_nameitem);
		
		tb_cName.setCellValueFactory(TableViewHelper.getPropertyValueFactory("name"));
		tb_cCat.setCellValueFactory(TableViewHelper.getPropertyValueFactory("CategoryFormat"));
		tb_cNum.setCellValueFactory(TableViewHelper.getPropertyValueFactory("num"));
		tb_cPrice.setCellValueFactory(TableViewHelper.getPropertyValueFactory("CostFormat"));
		tb_cTotal.setCellValueFactory(TableViewHelper.getPropertyValueFactory("TotalFormat"));
		tb_cDelete.setStyle("-fx-alignment: CENTER;");
		tb_cDelete.setCellFactory(new Callback<TableColumn<Item,Boolean>, TableCell<Item,Boolean>>() {
			
			@Override
			public TableCell<Item, Boolean> call(TableColumn<Item, Boolean> param) {
				//return new CustomCell(table_insertitem);
				return null;
			}
			
		});
        //cb_nameitem.getItems().add(listitem);
        //cb_nameitem = new JFXComboBox<Item>(listitem);
        //helper.FXUtil_Autocomplete.autoCompleteComboBoxPlus(cb_nameitem, (typedText, itemToCompare) -> itemToCompare.getName().toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.getAge().toString().equals(typedText));
        //FXUtil_Autocomplete.getComboBoxValue(cb_nameitem);
    }
}
