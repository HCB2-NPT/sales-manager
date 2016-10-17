package view.handler;

import org.apache.log4j.Logger;
import com.jfoenix.controls.JFXTextField;

import dao.hibernate_adapters.ProviderAdapter;
import helper.List2ObList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class Provider {
	private static final Logger logger = Logger.getLogger(Provider.class);

    @FXML
    private JFXTextField tfnew;

    @FXML
    private TableView<pojo.Provider> table;
    
    @FXML
    private TableColumn<pojo.Provider, Integer> table_cId;

    @FXML
    private TableColumn<pojo.Provider, String> table_cName;

    @FXML
    void add() {

    }

    @FXML
    void refresh() {

    }

    @FXML
    void save() {

    }

    @FXML
    void initialize() {
        assert tfnew != null : "fx:id=\"tfnew\" was not injected: check your FXML file 'Provider.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'Provider.fxml'.";
        
        table_cId.setCellValueFactory(new PropertyValueFactory<pojo.Provider, Integer>("providerId"));
        table_cName.setCellValueFactory(new PropertyValueFactory<pojo.Provider, String>("name"));
        table.setItems(List2ObList.L2OL(ProviderAdapter.getAll()));
    }
}
