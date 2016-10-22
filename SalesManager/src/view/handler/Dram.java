package view.handler;

import org.apache.log4j.Logger;
import com.jfoenix.controls.JFXTextField;
import dao.hibernate_adapters.DramAdapter;
import helper.ITableCellEvent;
import helper.ObservableListConverter;
import helper.TableViewHelper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;

public class Dram {
	private static final Logger logger = Logger.getLogger(Dram.class);

    @FXML
    private JFXTextField tfnew;

    @FXML
    private TableView<pojo.Dram> table;
    
    @FXML
    private TableColumn<pojo.Dram, Integer> table_cId;

    @FXML
    private TableColumn<pojo.Dram, String> table_cName;
    
    @FXML
    private TableColumn<pojo.Dram, String> table_cDesc;
    
    @FXML
    private TableColumn<pojo.Dram, String> cState;
    
    @FXML
    private TableColumn<pojo.Account, Boolean> cButtonRemove;

    @FXML
    void add() {
    	String name = tfnew.getText();
    	tfnew.clear();
    	pojo.Dram p = new pojo.Dram();
    	p.setCreated(true);
    	p.setName(name);
    	table.getItems().add(p);
    	table.refresh();
    	Main.callMsg("Added a new Dram, but not saved yet...");
    	logger.info("Add Dram: " + name);
    }

    @FXML
    void refresh() {
    	table.setItems(ObservableListConverter.L2OL(DramAdapter.getAll()));
    	logger.debug("Refresh");
    }

    @FXML
    void save() {
    	for (pojo.Dram p : table.getItems()) {
			if (p.getCreated()){
				DramAdapter.insert(p);
				logger.info("Save Dram: " + p.getName());
			}else if (p.getEdited()){
				DramAdapter.update(p);
				logger.info("Update Dram: " + p.getName());
			}
		}
    	Main.callMsg("Save success!");
    	logger.info("Save");
    	refresh();
    }

    @FXML
    void initialize() {
        assert tfnew != null : "fx:id=\"tfnew\" was not injected: check your FXML file 'Dram.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'Dram.fxml'.";
        assert table_cId != null : "fx:id=\"table_cId\" was not injected: check your FXML file 'Dram.fxml'.";
        assert table_cName != null : "fx:id=\"table_cName\" was not injected: check your FXML file 'Dram.fxml'.";
        assert table_cDesc != null : "fx:id=\"table_cDesc\" was not injected: check your FXML file 'Dram.fxml'.";
        assert cState != null : "fx:id=\"cState\" was not injected: check your FXML file 'Dram.fxml'.";
        
        table_cId.setCellValueFactory(TableViewHelper.getPropertyValueFactory("dramId"));
        table_cName.setCellValueFactory(TableViewHelper.getPropertyValueFactory("name"));
        table_cDesc.setCellValueFactory(TableViewHelper.getPropertyValueFactory("desc"));
        table_cDesc.setCellFactory(TableViewHelper.getCellFactory());
        table_cDesc.setOnEditCommit(
            new EventHandler<CellEditEvent<pojo.Dram, String>>() {
                @Override
                public void handle(CellEditEvent<pojo.Dram, String> t) {
                	pojo.Dram i = (pojo.Dram)t.getTableView().getItems().get(t.getTablePosition().getRow()); 
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
			public void commit(Integer index, Object newValue) {
				table.getItems().remove(table.getItems().get(index));
			}
		}));
        table.setItems(ObservableListConverter.L2OL(DramAdapter.getAll()));
    }
}
