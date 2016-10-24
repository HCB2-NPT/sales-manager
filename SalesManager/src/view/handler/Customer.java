package view.handler;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

public class Customer {
	@FXML
    private JFXTextField txt_name;

    @FXML
    private JFXTextField txt_personalid;

    @FXML
    private JFXTextField txt_phone;

    @FXML
    private JFXTextField txt_company;

    @FXML
    private JFXTextField txt_namecustomerSearch;

    @FXML
    private TableView<Customer> tb_listcustomer;

    @FXML
    private TableColumn<Customer, String> tb_cName;

    @FXML
    private TableColumn<Customer, String> tb_cpersonalid;

    @FXML
    private TableColumn<Customer, String> tb_cPhone;

    @FXML
    private TableColumn<Customer, String> tb_cCompany;

    @FXML
    void Add_Click() {

    }

    @FXML
    void Refresh_Click() {

    }

    @FXML
    void Search_Click() {

    }

    @FXML
    void save() {

    }

    @FXML
    void initialize() {
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'Customer.fxml'.";
        assert txt_personalid != null : "fx:id=\"txt_personalid\" was not injected: check your FXML file 'Customer.fxml'.";
        assert txt_phone != null : "fx:id=\"txt_phone\" was not injected: check your FXML file 'Customer.fxml'.";
        assert txt_company != null : "fx:id=\"txt_company\" was not injected: check your FXML file 'Customer.fxml'.";
        assert txt_namecustomerSearch != null : "fx:id=\"txt_namecustomerSearch\" was not injected: check your FXML file 'Customer.fxml'.";
        assert tb_listcustomer != null : "fx:id=\"tb_listcustomer\" was not injected: check your FXML file 'Customer.fxml'.";
        assert tb_cName != null : "fx:id=\"tb_cName\" was not injected: check your FXML file 'Customer.fxml'.";
        assert tb_cpersonalid != null : "fx:id=\"tb_cpersonalid\" was not injected: check your FXML file 'Customer.fxml'.";
        assert tb_cPhone != null : "fx:id=\"tb_cPhone\" was not injected: check your FXML file 'Customer.fxml'.";
        assert tb_cCompany != null : "fx:id=\"tb_cCompany\" was not injected: check your FXML file 'Customer.fxml'.";

    }
}
