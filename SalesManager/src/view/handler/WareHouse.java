package view.handler;

import com.jfoenix.controls.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class WareHouse {
	@FXML
    private JFXTextField txt_name;

    @FXML
    private JFXButton btn_search;

    @FXML
    private TableView<pojo.Item> tb_ListItem;

    @FXML
    void Search_Click() {

    }

    @FXML
    void initialize() {
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'WareHouse.fxml'.";
        assert btn_search != null : "fx:id=\"btn_search\" was not injected: check your FXML file 'WareHouse.fxml'.";
        assert tb_ListItem != null : "fx:id=\"tb_ListItem\" was not injected: check your FXML file 'WareHouse.fxml'.";

        
    }
}
