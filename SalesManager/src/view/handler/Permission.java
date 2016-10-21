package view.handler;

import org.apache.log4j.Logger;
import com.jfoenix.controls.JFXTextField;
import dao.hibernate_adapters.PermissionAdapter;
import helper.List2ObList;
import helper.TableViewHelper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;

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
    private TableColumn<pojo.Permission, String> table_cDesc;
    
    @FXML
    private TableColumn<pojo.Permission, String> cState;

    @FXML
    void add() {
    	String name = tfnew.getText();
    	tfnew.clear();
    	pojo.Permission p = new pojo.Permission();
    	p.setCreated(true);
    	p.setName(name);
    	table.getItems().add(p);
    	table.refresh();
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
			}else if (p.getEdited()){
				k = k || PermissionAdapter.update(p);
				logger.info("Update Permission: " + p.getName());
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
        assert table_cId != null : "fx:id=\"table_cId\" was not injected: check your FXML file 'Permission.fxml'.";
        assert table_cName != null : "fx:id=\"table_cName\" was not injected: check your FXML file 'Permission.fxml'.";
        assert table_cDesc != null : "fx:id=\"table_cDesc\" was not injected: check your FXML file 'Permission.fxml'.";
        assert cState != null : "fx:id=\"cState\" was not injected: check your FXML file 'Permission.fxml'.";
        
        table_cId.setCellValueFactory(TableViewHelper.getPropertyValueFactory("permissionId"));
        table_cName.setCellValueFactory(TableViewHelper.getPropertyValueFactory("name"));
        table_cDesc.setCellValueFactory(TableViewHelper.getPropertyValueFactory("desc"));
        table_cDesc.setCellFactory(TableViewHelper.getCellFactory());
        table_cDesc.setOnEditCommit(
            new EventHandler<CellEditEvent<pojo.Permission, String>>() {
                @Override
                public void handle(CellEditEvent<pojo.Permission, String> t) {
                	pojo.Permission i = (pojo.Permission)t.getTableView().getItems().get(t.getTablePosition().getRow()); 
                	if (t.getNewValue().equals(i.getDesc()))
                		return;
                    i.setDesc(t.getNewValue());
                    i.setEdited(true);
                    table.refresh();
                }
             }
        );
        cState.setCellValueFactory(TableViewHelper.getPropertyValueFactory("objectState"));
        table.setItems(List2ObList.L2OL(PermissionAdapter.getAll()));
    }
}
