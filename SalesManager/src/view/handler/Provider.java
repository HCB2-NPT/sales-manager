package view.handler;

import org.apache.log4j.Logger;
import com.jfoenix.controls.JFXTextField;

import dao.hibernate_adapters.ProviderAdapter;
import helper.List2ObList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    	String name = tfnew.getText();
    	tfnew.clear();
    	pojo.Provider p = new pojo.Provider();
    	p.setCreated(true);
    	p.setName(name);
    	table.getItems().add(p);
    	Main.callMsg("Added a new Provider, but not saved yet...");
    	logger.info("Add Provider: " + name);
    }

    @FXML
    void refresh() {
    	table.setItems(List2ObList.L2OL(ProviderAdapter.getAll()));
    	logger.debug("Refresh");
    }

    @FXML
    void save() {
    	boolean k = false;
    	for (pojo.Provider p : table.getItems()) {
			if (p.getCreated()){
				k = k || ProviderAdapter.insert(p);
				logger.info("Save provider: " + p.getName());
			}
		}
    	Main.callMsg("Save success!");
    	logger.info("Save");
    	if (k){
    		refresh();
    	}
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
