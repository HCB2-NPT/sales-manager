package view.handler;

import org.apache.log4j.Logger;
import com.jfoenix.controls.JFXTextField;

import dao.hibernate_adapters.PermissionAdapter;
import helper.List2ObList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Permission {
	private static final Logger logger = Logger.getLogger(Permission.class);

    @FXML
    private JFXTextField tfnew;

    @FXML
    private TableView<pojo.Permission> table;
    
    @FXML
    private TableColumn<pojo.Permission, Integer> table_cId;

    @FXML
    private TableColumn<pojo.Permission, String> table_cName;

    @FXML
    void add() {
    	String name = tfnew.getText();
    	tfnew.clear();
    	pojo.Permission p = new pojo.Permission();
    	p.setCreated(true);
    	p.setName(name);
    	table.getItems().add(p);
    	Main.callMsg("Added a new Permission, but not saved yet...");
    	logger.info("Add Permission: " + name);
    }

    @FXML
    void refresh() {
    	table.setItems(List2ObList.L2OL(PermissionAdapter.getAll()));
    	logger.debug("Refresh");
    }

    @FXML
    void save() {
    	boolean k = false;
    	for (pojo.Permission p : table.getItems()) {
			if (p.getCreated()){
				k = k || PermissionAdapter.insert(p);
				logger.info("Save Permission: " + p.getName());
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
        assert tfnew != null : "fx:id=\"tfnew\" was not injected: check your FXML file 'Permission.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'Permission.fxml'.";
        
        table_cId.setCellValueFactory(new PropertyValueFactory<pojo.Permission, Integer>("permissionId"));
        table_cName.setCellValueFactory(new PropertyValueFactory<pojo.Permission, String>("name"));
        table.setItems(List2ObList.L2OL(PermissionAdapter.getAll()));
    }
}
