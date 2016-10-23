package view.handler;

import java.sql.Date;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;

import com.jfoenix.controls.*;

import dao.hibernate_adapters.InvoiceAdapter;
import helper.ITableCellEvent;
import helper.MessageBox;
import helper.TableViewHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pojo.Invoice;

public class Debt {
		private static final Logger logger = Logger.getLogger(SellItem.class);
	    @FXML
	    private JFXTextField txt_BillID;

	    @FXML
	    private TableView<Invoice> tb_ListDebt;

	    @FXML
	    private TableColumn<Invoice, Integer> tb_cBillID;

	    @FXML
	    private TableColumn<Invoice, String> tb_cNameCustomer;

	    @FXML
	    private TableColumn<Invoice, Date> tb_cPayDate;

	    @FXML
	    private TableColumn<Invoice, String> tb_cDebt;

	    @FXML
	    private TableColumn<Invoice, Boolean> tb_cPay;

	    @FXML
	    void KeyPress(KeyEvent e) {
	    	if (e.getCode() == KeyCode.ENTER && !txt_BillID.getText().equals("")) {
    			Focus();
			}
	    }
	    
	    @FXML
	    void Refresh_Click(){
	    	tb_ListDebt.getItems().clear();
        	tb_ListDebt.getItems().addAll(InvoiceAdapter.getAllDebtor());
        	tb_ListDebt.refresh();
	    }

	    @FXML
	    void btnSearch_Click() {
	    	if (!txt_BillID.getText().equals("")) {
	    		Focus();
	    	}
	    }
	    
	    void Focus(){
    		String s = txt_BillID.getText().toLowerCase();
        	ObservableList<Invoice> list = FXCollections.observableArrayList();
        	for (Invoice i : tb_ListDebt.getItems()) {
    			if (String.valueOf(i.getInvoiceId()).toLowerCase().contains(s)) {
    				list.add(i);
    			}
    		}
        	if (list.size()<=0) {
        		MessageBox.Show("Can't find item have name ''" + s +"''!", "Alert");
        		return;
    		}
        	tb_ListDebt.getItems().clear();
        	tb_ListDebt.setItems(list);
        	tb_ListDebt.refresh();
	    }

	    @FXML
	    void initialize() {
	        assert txt_BillID != null : "fx:id=\"txt_BillID\" was not injected: check your FXML file 'Debt.fxml'.";
	        assert tb_ListDebt != null : "fx:id=\"tb_ListDebt\" was not injected: check your FXML file 'Debt.fxml'.";
	        assert tb_cBillID != null : "fx:id=\"tb_cBillID\" was not injected: check your FXML file 'Debt.fxml'.";
	        assert tb_cNameCustomer != null : "fx:id=\"tb_cNameCustomer\" was not injected: check your FXML file 'Debt.fxml'.";
	        assert tb_cPayDate != null : "fx:id=\"tb_cPayDate\" was not injected: check your FXML file 'Debt.fxml'.";
	        assert tb_cDebt != null : "fx:id=\"tb_cDebt\" was not injected: check your FXML file 'Debt.fxml'.";
	        assert tb_cPay != null : "fx:id=\"tb_cPay\" was not injected: check your FXML file 'Debt.fxml'.";

	        tb_cBillID.setCellValueFactory(TableViewHelper.getPropertyValueFactory("invoiceId"));
			tb_cNameCustomer.setCellValueFactory(TableViewHelper.getPropertyValueFactory("CustomerFormat"));
			tb_cDebt.setCellValueFactory(TableViewHelper.getPropertyValueFactory("TotalMoney"));
			tb_cPayDate.setCellValueFactory(TableViewHelper.getPropertyValueFactory("paymentDate"));
			
			tb_cPay.setStyle("-fx-alignment: CENTER;");
			tb_cPay.setCellValueFactory(TableViewHelper.getPropertyValueFactory("isPayment"));
			tb_cPay.setCellFactory(TableViewHelper.getButtonCellFactory(null, "../view/img/Checkmark-48 (Green).png", new ITableCellEvent() {
				@Override
				public void commit(Integer index, Object newValue) {
					
					Alert alert = new Alert(AlertType.CONFIRMATION, "The Customer Was Paid Money ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
					alert.showAndWait();

					if (alert.getResult() == ButtonType.YES) {
						Invoice invoice = tb_ListDebt.getItems().get(index);
						logger.info("Executing Bill ID: " +  invoice.getInvoiceId());
						tb_ListDebt.getItems().remove(tb_ListDebt.getItems().get(index));
						tb_ListDebt.refresh();
						invoice.setIsPayment(false);
						InvoiceAdapter.updatePaid(invoice);
						logger.info("Process success Bill ID: " +  invoice.getInvoiceId());
						return;
					}
				}
			}));
			
			tb_ListDebt.getItems().addAll(InvoiceAdapter.getAllDebtor());
	    }
}
