package view.handler;

import com.jfoenix.controls.JFXTextField;

import dao.hibernate_adapters.CustomerAdapter;
import helper.MessageBox;
import helper.ObservableListConverter;
import helper.TableViewHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import pojo.*;

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
    private TableView<pojo.Customer> tb_listcustomer;

    @FXML
    private TableColumn<pojo.Customer, String> tb_cName;

    @FXML
    private TableColumn<pojo.Customer, String> tb_cpersonalid;

    @FXML
    private TableColumn<pojo.Customer, String> tb_cPhone;

    @FXML
    private TableColumn<pojo.Customer, String> tb_cCompany;

    @FXML
    void Add_Click() {
    	if (CustomerAdapter.get(txt_personalid.getText())!=null) {
			MessageBox.Show("Personal ID had exsisted", "Alert");
		}
    	if (txt_name!= null && !txt_name.getText().equals("") ) {
			if (txt_personalid!=null && !txt_personalid.getText().equals("")) {
				pojo.Customer c = new pojo.Customer();
				c.setName(txt_name.getText());
				c.setPersonalId(txt_personalid.getText());
				c.setCompany(txt_company.getText());
				c.setPhoneNumber(txt_phone.getText());
				c.setCreated(true);
				tb_listcustomer.getItems().add(c);
			}
			else {
				MessageBox.Show("PersonalID cannot Empty", "Alert");
			}
			
		}else {
			MessageBox.Show("Name cannot Empty", "Alert");
		}
    }

    @FXML
    void Refresh_Click() {
    	tb_listcustomer.setItems(ObservableListConverter.L2OL(CustomerAdapter.getAll()));
    }

    @FXML
    void Search_Click() {
    	if (!txt_namecustomerSearch.getText().equals("")) {
    		String s = txt_namecustomerSearch.getText().toLowerCase();
        	ObservableList<pojo.Customer> list = FXCollections.observableArrayList();
        	for (pojo.Customer i : tb_listcustomer.getItems()) {
    			if (i.getName().toLowerCase().contains(s)) {
    				list.add(i);
    			}
    		}
        	if (list.size()<=0) {
        		MessageBox.Show("Can't find Customer have name ''" + s +"''!", "Alert");
        		return;
    		}
        	tb_listcustomer.getItems().clear();
        	tb_listcustomer.setItems(list);	
		}
    	
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

        tb_cName.setCellValueFactory(TableViewHelper.getPropertyValueFactory("name"));
		tb_cpersonalid.setCellValueFactory(TableViewHelper.getPropertyValueFactory("personalId"));
		tb_cPhone.setCellValueFactory(TableViewHelper.getPropertyValueFactory("phoneNumber"));
		tb_cCompany.setCellValueFactory(TableViewHelper.getPropertyValueFactory("company"));
		
		tb_listcustomer.setItems(ObservableListConverter.L2OL(CustomerAdapter.getAll()));
    }
}
