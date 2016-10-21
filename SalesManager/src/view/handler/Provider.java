package view.handler;

import org.apache.log4j.Logger;
import com.jfoenix.controls.JFXTextField;
import dao.hibernate_adapters.ProviderAdapter;
import helper.ITableCellEvent;
import helper.List2ObList;
import helper.TableViewHelper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;

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
    private TableColumn<pojo.Provider, String> table_cDesc;
    
    @FXML
    private TableColumn<pojo.Provider, String> cState;
    
    @FXML
    private TableColumn<pojo.Account, Boolean> cButtonRemove;

    @FXML
    void add() {
    	String name = tfnew.getText();
    	tfnew.clear();
    	pojo.Provider p = new pojo.Provider();
    	p.setCreated(true);
    	p.setName(name);
    	table.getItems().add(p);
    	table.refresh();
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
				logger.info("Save Provider: " + p.getName());
			}else if (p.getEdited()){
				k = k || ProviderAdapter.update(p);
				logger.info("Update Provider: " + p.getName());
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
        assert table_cId != null : "fx:id=\"table_cId\" was not injected: check your FXML file 'Provider.fxml'.";
        assert table_cName != null : "fx:id=\"table_cName\" was not injected: check your FXML file 'Provider.fxml'.";
        assert table_cDesc != null : "fx:id=\"table_cDesc\" was not injected: check your FXML file 'Provider.fxml'.";
        assert cState != null : "fx:id=\"cState\" was not injected: check your FXML file 'Provider.fxml'.";
        
        table_cId.setCellValueFactory(TableViewHelper.getPropertyValueFactory("providerId"));
        table_cName.setCellValueFactory(TableViewHelper.getPropertyValueFactory("name"));
        table_cDesc.setCellValueFactory(TableViewHelper.getPropertyValueFactory("desc"));
        table_cDesc.setCellFactory(TableViewHelper.getCellFactory());
        table_cDesc.setOnEditCommit(
            new EventHandler<CellEditEvent<pojo.Provider, String>>() {
                @Override
                public void handle(CellEditEvent<pojo.Provider, String> t) {
                	pojo.Provider i = (pojo.Provider)t.getTableView().getItems().get(t.getTablePosition().getRow()); 
                	if (t.getNewValue().equals(i.getDesc()))
                		return;
                    i.setDesc(t.getNewValue());
                    i.setEdited(true);
                    table.refresh();
                }
             }
        );
        cState.setCellValueFactory(TableViewHelper.getPropertyValueFactory("objectState"));
        cButtonRemove.setCellValueFactory(TableViewHelper.getPropertyValueFactory("created"));
        cButtonRemove.setCellFactory(TableViewHelper.getButtonCellFactory(null, "../view/img/Delete-48 (Red).png", new ITableCellEvent() {
			@Override
			public void commit(Object item, Object newValue) {
				table.getItems().remove((int)newValue);
			}
		}));
        table.setItems(List2ObList.L2OL(ProviderAdapter.getAll()));
    }
}
