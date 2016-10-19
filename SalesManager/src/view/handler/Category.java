package view.handler;

import org.apache.log4j.Logger;
import com.jfoenix.controls.JFXTextField;

import dao.hibernate_adapters.CategoryAdapter;
import helper.List2ObList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Category {
	private static final Logger logger = Logger.getLogger(Category.class);

    @FXML
    private JFXTextField tfnew;

    @FXML
    private TableView<pojo.Category> table;
    
    @FXML
    private TableColumn<pojo.Category, Integer> table_cId;

    @FXML
    private TableColumn<pojo.Category, String> table_cName;

    @FXML
    void add() {
    	String name = tfnew.getText();
    	tfnew.clear();
    	pojo.Category p = new pojo.Category();
    	p.setCreated(true);
    	p.setName(name);
    	table.getItems().add(p);
    	Main.callMsg("Added a new Category, but not saved yet...");
    	logger.info("Add Category: " + name);
    }

    @FXML
    void refresh() {
    	table.setItems(List2ObList.L2OL(CategoryAdapter.getAll()));
    	logger.debug("Refresh");
    }

    @FXML
    void save() {
    	boolean k = false;
    	for (pojo.Category p : table.getItems()) {
			if (p.getCreated()){
				k = k || CategoryAdapter.insert(p);
				logger.info("Save Category: " + p.getName());
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
        assert tfnew != null : "fx:id=\"tfnew\" was not injected: check your FXML file 'Category.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'Category.fxml'.";
        
        table_cId.setCellValueFactory(new PropertyValueFactory<pojo.Category, Integer>("catId"));
        table_cName.setCellValueFactory(new PropertyValueFactory<pojo.Category, String>("name"));
        table.setItems(List2ObList.L2OL(CategoryAdapter.getAll()));
    }
}
