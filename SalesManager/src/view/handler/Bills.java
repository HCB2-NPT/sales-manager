package view.handler;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Optional;

import com.jfoenix.controls.*;

import dao.hibernate_adapters.InvoiceAdapter;
import dao.hibernate_adapters.InvoiceExtAdapter;
import helper.ITableCellEvent;
import helper.MessageBox;
import helper.TableViewHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pojo.Invoice;
import pojo.InvoiceExt;

public class Bills {
	
	private ObservableList<Invoice> list_delete = FXCollections.observableArrayList();
	@FXML
    private JFXDatePicker datepicker_field;

    @FXML
    private TableView<pojo.Invoice> tb_ListBill;

    @FXML
    private TableColumn<Invoice, Integer> tb_cID;

    @FXML
    private TableColumn<Invoice, String> tb_cCustomer;
    
    @FXML
    private TableColumn<Invoice, Date> tb_cDate;

    @FXML
    private TableColumn<Invoice, String> tb_cTotalMoney;

    @FXML
    private TableColumn<Invoice, Boolean> tb_cDelete;

    @FXML
    void All_Click() {
    	tb_ListBill.getItems().clear();
    	tb_ListBill.getItems().addAll(InvoiceAdapter.getAll());
    }

    @FXML
    void Refresh_Click() {
    	datepicker_field.setValue(LocalDate.now());
    	tb_ListBill.getItems().clear();
    	tb_ListBill.getItems().addAll(InvoiceAdapter.getAll());
    	tb_ListBill.refresh();
    	list_delete= FXCollections.observableArrayList();
    }

    @FXML
    void Save_Click() {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Save Changed ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
		    //do stuff
			for (Invoice inv : list_delete) {
					InvoiceAdapter.delete(inv);
			}
		}
    }
    

    @FXML
    void date_SelectedChange() {
    	list_delete= FXCollections.observableArrayList();
    	tb_ListBill.getItems().clear();
    	for (Invoice invoice : InvoiceAdapter.getAll()) {
			if (invoice.getDate().equals(Date.valueOf(datepicker_field.getValue()))) {
				tb_ListBill.getItems().add(invoice);
			}
		}
    	tb_ListBill.refresh();
    }

    @FXML
    void initialize() {
        assert datepicker_field != null : "fx:id=\"datepicker_field\" was not injected: check your FXML file 'Bills.fxml'.";
        assert tb_ListBill != null : "fx:id=\"tb_ListBill\" was not injected: check your FXML file 'Bills.fxml'.";
        assert tb_cID != null : "fx:id=\"tb_cID\" was not injected: check your FXML file 'Bills.fxml'.";
        assert tb_cCustomer != null : "fx:id=\"tb_cCustomer\" was not injected: check your FXML file 'Bills.fxml'.";
        assert tb_cTotalMoney != null : "fx:id=\"tb_cTotalMoney\" was not injected: check your FXML file 'Bills.fxml'.";
        assert tb_cDelete != null : "fx:id=\"tb_cDelete\" was not injected: check your FXML file 'Bills.fxml'.";
        assert tb_cDate != null : "fx:id=\"tb_cDelete\" was not injected: check your FXML file 'Bills.fxml'.";

        tb_cID.setCellValueFactory(TableViewHelper.getPropertyValueFactory("invoiceId"));
        tb_cCustomer.setCellValueFactory(TableViewHelper.getPropertyValueFactory("CustomerFormat"));
        tb_cDate.setCellValueFactory(TableViewHelper.getPropertyValueFactory("Date"));
        tb_cTotalMoney.setCellValueFactory(TableViewHelper.getPropertyValueFactory("TotalMoney"));
        tb_cDelete.setCellValueFactory(TableViewHelper.getPropertyValueFactory("InDB"));
        tb_cDelete.setCellFactory(TableViewHelper.getButtonCellFactory(null, "../view/img/Delete-48 (Red).png", new ITableCellEvent() {
			@Override
			public void commit(Integer index, Object newValue) {
				Invoice inv = tb_ListBill.getItems().get(index);
				inv.setIsDeleted(true);
				
				Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + inv.getInvoiceId() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
				alert.showAndWait();

				if (alert.getResult() == ButtonType.YES) {
				    //do stuff
					list_delete.add(inv);
					tb_ListBill.getItems().remove(tb_ListBill.getItems().get(index));
					tb_ListBill.refresh();
					return;
				}
			}
		}));
        
        tb_ListBill.getItems().addAll(InvoiceAdapter.getAll());
        tb_ListBill.refresh();
        
    }
}
