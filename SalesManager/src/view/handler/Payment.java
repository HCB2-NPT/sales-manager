package view.handler;

import java.sql.Date;

import com.jfoenix.controls.*;

import dao.hibernate_adapters.ImportAdapter;
import dao.hibernate_adapters.InvoiceAdapter;
import helper.TableViewHelper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pojo.Import;
import pojo.Invoice;

public class Payment {
	   @FXML
	    private JFXDatePicker datepicker;

	    @FXML
	    private TableView<Import> tb_ListPayment;

	    @FXML
	    private TableColumn<Import, Integer> tb_cID;

	    @FXML
	    private TableColumn<Import, String> tb_cCreater;

	    @FXML
	    private TableColumn<Import, Date> tb_cDate;

	    @FXML
	    private TableColumn<Import, String> tb_cTotalMoney;

	    @FXML
	    void Refresh_Click() {
	    	tb_ListPayment.getItems().clear();
	        tb_ListPayment.getItems().addAll(ImportAdapter.getAll());
	        tb_ListPayment.refresh();
	    }

	    @FXML
	    void date_SelectedChange() {
	    	tb_ListPayment.getItems().clear();
	    	for (Import imp: ImportAdapter.getAll()) {
				if (imp.getDate().equals(Date.valueOf(datepicker.getValue()))) {
					tb_ListPayment.getItems().add(imp);
				}
			}
	    	tb_ListPayment.refresh();
	    }

	    @FXML
	    void initialize() {
	        assert datepicker != null : "fx:id=\"datepicker\" was not injected: check your FXML file 'Payment.fxml'.";
	        assert tb_ListPayment != null : "fx:id=\"tb_ListPayment\" was not injected: check your FXML file 'Payment.fxml'.";
	        assert tb_cID != null : "fx:id=\"tb_cID\" was not injected: check your FXML file 'Payment.fxml'.";
	        assert tb_cCreater != null : "fx:id=\"tb_cCreater\" was not injected: check your FXML file 'Payment.fxml'.";
	        assert tb_cDate != null : "fx:id=\"tb_cDate\" was not injected: check your FXML file 'Payment.fxml'.";
	        assert tb_cTotalMoney != null : "fx:id=\"tb_cTotalMoney\" was not injected: check your FXML file 'Payment.fxml'.";

	        tb_cID.setCellValueFactory(TableViewHelper.getPropertyValueFactory("importId"));
	        tb_cDate.setCellValueFactory(TableViewHelper.getPropertyValueFactory("date"));
	        tb_cCreater.setCellValueFactory(TableViewHelper.getPropertyValueFactory("CreaterName"));
	        tb_cTotalMoney.setCellValueFactory(TableViewHelper.getPropertyValueFactory("TotalMoney"));
	        
	        tb_ListPayment.getItems().addAll(ImportAdapter.getAll());
	        
    	}
}
