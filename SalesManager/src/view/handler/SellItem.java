package view.handler;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jboss.logging.Message;
import org.w3c.dom.css.ElementCSSInlineStyle;

import com.jfoenix.controls.*;
import application.AppSession;
import dao.hibernate_adapters.AccountAdapter;
import dao.hibernate_adapters.CustomerAdapter;
import dao.hibernate_adapters.InvoiceAdapter;
import dao.hibernate_adapters.InvoiceExtAdapter;
import dao.hibernate_adapters.ItemAdapter;
import helper.FXUtil_Autocomplete;
import helper.ITableCellEvent;
import helper.MessageBox;
import helper.ObservableListConverter;
import helper.TableViewHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import pojo.Account;
import pojo.Customer;
import pojo.Invoice;
import pojo.InvoiceExt;
import pojo.Item;

public class SellItem {
	private static final Logger logger = Logger.getLogger(SellItem.class);
	ObservableList<Item> list = FXCollections.observableArrayList();
	@FXML
    private JFXComboBox<Item> cb_nameitem;
    @FXML
    private JFXCheckBox chk_Debt;
    @FXML
    private JFXTextField txt_number;
    @FXML
    private JFXTextField txt_personalcustomer;
    @FXML
    private JFXDatePicker datepicker_create;
    @FXML
    private TableView<InvoiceExt> table_insertitem;
    @FXML
    private JFXTextField txt_totalmoney;
    @FXML
    private TableColumn<Invoice, String> tb_cName;
    @FXML
    private TableColumn<Invoice, String> tb_cCat;
    @FXML
    private TableColumn<Invoice, String> tb_cPrice;
    @FXML
    private TableColumn<Invoice, Integer> tb_cNum;
    @FXML
    private TableColumn<Invoice, String> tb_cTotal;
    @FXML
    private TableColumn<Invoice, Boolean> tb_cDelete;

    private int max_num=2000;
    @FXML
    void btnAdd_Click() {
    	if (cb_nameitem.getSelectionModel().getSelectedItem()!=null) {
    		if (cb_nameitem.getSelectionModel().getSelectedItem().toString().equals("")) {
    			return;
    		}
        	else{
        		Item item = cb_nameitem.getSelectionModel().getSelectedItem();
				if (table_insertitem.getItems().size()>0) {
					for (InvoiceExt invoice : table_insertitem.getItems()) {
						if (invoice.getItemId()==item.getItemId()) {
							if (invoice.getNum()<item.getNum()) {
								invoice.setNum(invoice.getNum()+1);
								table_insertitem.refresh();
								txt_totalmoney.setText(String.valueOf(new DecimalFormat("#,###.00").format(getTotalMoney())));
								return;
							}
							return;
						}
					}
				}
				InvoiceExt invoice_ex = new InvoiceExt();
				invoice_ex.setItem(cb_nameitem.getSelectionModel().getSelectedItem());
				invoice_ex.setNum(1);
				invoice_ex.setCost(item.getCost());
				invoice_ex.setItemId(item.getItemId());
				invoice_ex.setCreated(true);
				table_insertitem.getItems().add(invoice_ex);
				table_insertitem.refresh();
				txt_totalmoney.setText(String.valueOf(new DecimalFormat("#,###.00").format(getTotalMoney())));
				return;
        	}	
		}
    }

    @FXML
    void cb_Action() {
    	txt_number.setText(String.valueOf(cb_nameitem.getSelectionModel().getSelectedItem().getNum()));
    }
    
    
    public double getTotalMoney(){
    	double a = 0;
    	for (InvoiceExt item : table_insertitem.getItems()) {
			a+= item.getCost()*item.getNum();
		}
    	return a;
    }
    @FXML
    void number_KeyPress(KeyEvent e){
    	if (Integer.valueOf(txt_number.getText())>=max_num) {
    		txt_number.setText(String.valueOf(max_num));
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
			Customer c = CustomerAdapter.get(txt_personalcustomer.getText());
			if (c==null) {
				MessageBox.Show("Customer not exists!", "Alert");
				return;
			}
			if (table_insertitem.getItems().size()>=1) {
				if (datepicker_create.getValue().compareTo(LocalDate.now())>=0) {
					logger.info("Begin Create New Invoice");
					//create invoice
					
					Invoice in = new Invoice();
					in.setCreater(AppSession._currentUser);
					in.setDate(java.sql.Date.valueOf(LocalDate.now()));
					in.setCreated(true);
					in.setCreaterId(AppSession._currentUser.getAccountId());
					in.setCustomer(c);
					in.setCustomerId(c.getCustomerId());
					if (chk_Debt.isSelected()) {
						in.setIsPayment(true);
						in.setPaymentDate(java.sql.Date.valueOf(datepicker_create.getValue()));
					}
					else {
						in.setIsPayment(false);
						in.setPaymentDate(null);
					}
					
					ObservableList<InvoiceExt> list_ix = FXCollections.observableArrayList();
					list_ix.addAll(table_insertitem.getItems());
					
					//add invoice - invoiceExt
					InvoiceAdapter.insert(in);
					logger.info("Saved New Invoice");
					
					List<Invoice> l = InvoiceAdapter.getAll();
					int max = 0;
					for (Invoice invoice : l) {
						if (invoice.getInvoiceId() > max) {
							max = invoice.getInvoiceId();
						}
					}
					for (InvoiceExt invoiceExt : list_ix) {
						invoiceExt.setInvoiceId(max);
						invoiceExt.setItemId(invoiceExt.getItem().getItemId());
					}
					InvoiceExtAdapter.insertList(list_ix);
					
					//decrease number item in warehouse
					for (InvoiceExt invoiceExt : list_ix) {
						Item item = invoiceExt.getItem();
						ItemAdapter.addNumber(-invoiceExt.getNum(), item.getItemId());

						
						logger.info("Number of " + item.getName() +"________ -" + invoiceExt.getNum());
					}
					logger.info("Saved List Item of Invoice");
					logger.info("End Save NewVoice");
					MessageBox.Show("Transaction Complete!", "Success");
					ClearAll();
				}
				else
					MessageBox.Show("Day to Pay DebtMoney must after today  ", "Warning");
			}	
		}
    }
    
    @FXML
    void chk_Action() {
    	if (chk_Debt.isSelected()) 
			datepicker_create.setDisable(false);
    	else
    		datepicker_create.setDisable(true);
    }

    @FXML
    void initialize() {
        assert cb_nameitem != null : "fx:id=\"cb_nameitem\" was not injected: check your FXML file 'SellItem.fxml'.";
        assert datepicker_create != null : "fx:id=\"datepicker_create\" was not injected: check your FXML file 'SellItem.fxml'.";
        assert table_insertitem != null : "fx:id=\"table_insertitem\" was not injected: check your FXML file 'SellItem.fxml'.";
        assert txt_totalmoney != null : "fx:id=\"txt_totalmoney\" was not injected: check your FXML file 'SellItem.fxml'.";

        datepicker_create.setValue(LocalDate.now());
        datepicker_create.setDisable(true);
        ObservableList<String> listitem = FXCollections.observableArrayList();
        for (Item item : ObservableListConverter.L2OL(ItemAdapter.getAll())) {
			listitem.add(item.getName());
			list.add(item);
		}
		cb_nameitem.setItems(ObservableListConverter.L2OL(ItemAdapter.getAll()));
		new FXUtil_Autocomplete<Item>(cb_nameitem);
		
		tb_cName.setCellValueFactory(TableViewHelper.getPropertyValueFactory("NameFormat"));
		tb_cCat.setCellValueFactory(TableViewHelper.getPropertyValueFactory("CategoryFormat"));
		tb_cNum.setCellValueFactory(TableViewHelper.getPropertyValueFactory("num"));
		tb_cPrice.setCellValueFactory(TableViewHelper.getPropertyValueFactory("CostFormat"));
		tb_cTotal.setCellValueFactory(TableViewHelper.getPropertyValueFactory("TotalFormat"));
		
		tb_cDelete.setStyle("-fx-alignment: CENTER;");
		tb_cDelete.setCellValueFactory(TableViewHelper.getPropertyValueFactory("Created"));
		tb_cDelete.setCellFactory(TableViewHelper.getButtonCellFactory(null, "../view/img/Delete-48 (Red).png", new ITableCellEvent() {
			@Override
			public void commit(Integer index, Object newValue) {
				table_insertitem.getItems().remove(table_insertitem.getItems().get(index));
				table_insertitem.refresh();
				txt_totalmoney.setText(String.valueOf(new DecimalFormat("#,###.00").format(getTotalMoney())));
				return;
			}
		}));
    }

    @FXML
    void btnRefresh_Click() {
		ClearAll();
    }
    
    void ClearAll(){
    	list.clear(); 
    	list.addAll(ItemAdapter.getAll());
    	cb_nameitem.setItems(ObservableListConverter.L2OL(ItemAdapter.getAll()));
    	new FXUtil_Autocomplete<Item>(cb_nameitem);
		txt_personalcustomer.setText("Chung");
		txt_number.setText("1");
		txt_totalmoney.setText(".00");
		table_insertitem.getItems().clear();
		table_insertitem.refresh();
    }
}
