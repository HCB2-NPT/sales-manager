package view.handler;

import org.apache.log4j.Logger;
import com.jfoenix.controls.JFXTextField;

import dao.hibernate_adapters.DramAdapter;
import helper.List2ObList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    void add() {
    	String name = tfnew.getText();
    	tfnew.clear();
    	pojo.Dram p = new pojo.Dram();
    	p.setCreated(true);
    	p.setName(name);
    	table.getItems().add(p);
    	logger.info("Add Dram: " + name);
    }

    @FXML
    void refresh() {
    	table.setItems(List2ObList.L2OL(DramAdapter.getAll()));
    	logger.debug("Refresh");
    }

    @FXML
    void save() {
    	boolean k = false;
    	for (pojo.Dram p : table.getItems()) {
			if (p.getCreated()){
				k = k || DramAdapter.insert(p);
				logger.info("Save Dram: " + p.getName());
			}
		}
    	logger.info("Save");
    	if (k){
    		refresh();
    	}
    }

    @FXML
    void initialize() {
        assert tfnew != null : "fx:id=\"tfnew\" was not injected: check your FXML file 'Dram.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'Dram.fxml'.";
        
        table_cId.setCellValueFactory(new PropertyValueFactory<pojo.Dram, Integer>("dramId"));
        table_cName.setCellValueFactory(new PropertyValueFactory<pojo.Dram, String>("name"));
        table.setItems(List2ObList.L2OL(DramAdapter.getAll()));
    }
}
