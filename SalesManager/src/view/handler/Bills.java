package view.handler;

import com.jfoenix.controls.*;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Bills {
	@FXML
    private JFXDatePicker datepicker_field;

    @FXML
    private TableView<pojo.Invoice> tb_ListBill;

    @FXML
    void All_Click() {

    }

    @FXML
    void Refresh_Click() {

    }

    @FXML
    void Save_Click() {

    }

    @FXML
    void initialize() {
        assert datepicker_field != null : "fx:id=\"datepicker_field\" was not injected: check your FXML file 'Bills.fxml'.";
        assert tb_ListBill != null : "fx:id=\"tb_ListBill\" was not injected: check your FXML file 'Bills.fxml'.";

        
    }
}
