package view.handler;

import org.apache.log4j.Logger;
import com.jfoenix.controls.*;
import application.AppSession;
import dao.hibernate_adapters.AccountAdapter;
import dao.hibernate_adapters.PermissionAdapter;
import helper.FXUtil_Autocomplete;
import helper.ITableCellEvent;
import helper.ObservableListConverter;
import helper.TableViewHelper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;

public class AccountOsin {
	private static final Logger logger = Logger.getLogger(AccountOsin.class);
	
	@FXML
    private JFXTextField _name;

    @FXML
    private JFXTextField _username;

    @FXML
    private JFXComboBox<pojo.Permission> _perm;

    @FXML
    private TableView<pojo.Account> table;

    @FXML
    private TableColumn<pojo.Account, Integer> cId;

    @FXML
    private TableColumn<pojo.Account, String> cName;

    @FXML
    private TableColumn<pojo.Account, String> cUsername;

    @FXML
    private TableColumn<pojo.Account, pojo.Permission> cPerm;

    @FXML
    private TableColumn<pojo.Account, Boolean> cIsDeleted;

    @FXML
    private TableColumn<pojo.Account, String> cState;
    
    @FXML
    private TableColumn<pojo.Account, Boolean> cButtonRemove;
    
    @FXML
    private Label __currentUser;

    @FXML
    void refresh() {
    	table.setItems(ObservableListConverter.L2OL(AccountAdapter.getByPermission(AppSession._currentUser.getPermissionId())));
    	logger.debug("Refresh");
    }

    @FXML
    void save() {
    	for (pojo.Account p : table.getItems()) {
			if (p.getCreated()){
				AccountAdapter.signup(p);
				logger.info("Save Account: " + p.getName());
			}else if (p.getEdited()){
				AccountAdapter.update(p);
				logger.info("Update Account: " + p.getName());
			}
		}
    	Main.callMsg("Save success!");
    	logger.info("Save");
		refresh();
    }
    
    @FXML
    void add() {
    	String name = _name.getText();
    	String user = _username.getText();
    	pojo.Permission perm = _perm.getSelectionModel().getSelectedItem();
    	_name.clear();
    	_username.clear();
    	_perm.getSelectionModel().clearSelection();
    	pojo.Account p = new pojo.Account();
    	p.setCreated(true);
    	p.setName(name);
    	p.setUsername(user);
    	p.setPermission(perm);
    	table.getItems().add(p);
    	table.refresh();
    	Main.callMsg("Added a new Account, but not saved yet...");
    	logger.info("Add Account: " + name);
    }

    @FXML
    void initialize() {
        assert _name != null : "fx:id=\"_name\" was not injected: check your FXML file 'AccountOsin.fxml'.";
        assert _username != null : "fx:id=\"_username\" was not injected: check your FXML file 'AccountOsin.fxml'.";
        assert _perm != null : "fx:id=\"_perm\" was not injected: check your FXML file 'AccountOsin.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'AccountOsin.fxml'.";
        assert cId != null : "fx:id=\"cId\" was not injected: check your FXML file 'AccountOsin.fxml'.";
        assert cName != null : "fx:id=\"cName\" was not injected: check your FXML file 'AccountOsin.fxml'.";
        assert cUsername != null : "fx:id=\"cUsername\" was not injected: check your FXML file 'AccountOsin.fxml'.";
        assert cPerm != null : "fx:id=\"cPerm\" was not injected: check your FXML file 'AccountOsin.fxml'.";
        assert cIsDeleted != null : "fx:id=\"cIsDeleted\" was not injected: check your FXML file 'AccountOsin.fxml'.";
        assert cState != null : "fx:id=\"cState\" was not injected: check your FXML file 'AccountOsin.fxml'.";
        
        __currentUser.setText(String.format("Current User: %1$s", AppSession._currentUser.getName()));
        
        cId.setCellValueFactory(TableViewHelper.getPropertyValueFactory("accountId"));
        cName.setCellValueFactory(TableViewHelper.getPropertyValueFactory("name"));
        cName.setCellFactory(TableViewHelper.getCellFactory());
        cName.setOnEditCommit(
            new EventHandler<CellEditEvent<pojo.Account, String>>() {
                @Override
                public void handle(CellEditEvent<pojo.Account, String> t) {
                	pojo.Account i = (pojo.Account)t.getTableView().getItems().get(t.getTablePosition().getRow());
                	if (t.getNewValue().equals(i.getName()))
                		return;
                    i.setName(t.getNewValue());
                    i.setEdited(true);
                    table.refresh();
                }
             }
        );
        cUsername.setCellValueFactory(TableViewHelper.getPropertyValueFactory("username"));
        cUsername.setCellFactory(TableViewHelper.getCellFactory());
        cUsername.setOnEditCommit(
            new EventHandler<CellEditEvent<pojo.Account, String>>() {
                @Override
                public void handle(CellEditEvent<pojo.Account, String> t) {
                	pojo.Account i = (pojo.Account)t.getTableView().getItems().get(t.getTablePosition().getRow());
                	if (t.getNewValue().equals(i.getUsername()))
                		return;
                    i.setUsername(t.getNewValue());
                    i.setEdited(true);
                    table.refresh();
                }
             }
        );
        cPerm.setCellValueFactory(TableViewHelper.getPropertyValueFactory("permFormat"));
        cPerm.setCellFactory(TableViewHelper.getComboBoxCellFactory(ObservableListConverter.L2OL(PermissionAdapter.getByPermission(AppSession._currentUser.getPermissionId())), new ITableCellEvent() {
			@Override
			public void commit(Integer index, Object newValue) {
				pojo.Account i = table.getItems().get(index);
				i.setPermission((pojo.Permission)newValue);
				i.setEdited(true);
				table.refresh();
			}
		}));
        cIsDeleted.setCellValueFactory(TableViewHelper.getPropertyValueFactory("isDeleted"));
        cIsDeleted.setCellFactory(TableViewHelper.getCheckBoxCellFactory(new ITableCellEvent() {
			@Override
			public void commit(Integer index, Object newValue) {
				pojo.Account i = table.getItems().get(index);
				i.setIsDeleted((boolean)newValue);
				i.setEdited(true);
				table.refresh();
			}
		}));
        cState.setCellValueFactory(TableViewHelper.getPropertyValueFactory("objectState"));
        cButtonRemove.setCellValueFactory(TableViewHelper.getPropertyValueFactory("created"));
        cButtonRemove.setCellFactory(TableViewHelper.getButtonCellFactory(null, "../view/img/Delete-48 (Red).png", new ITableCellEvent() {
			@Override
			public void commit(Integer index, Object newValue) {
				table.getItems().remove(table.getItems().get(index));
			}
		}));
        table.setItems(ObservableListConverter.L2OL(AccountAdapter.getByPermission(AppSession._currentUser.getPermissionId())));
        
        _perm.setItems(ObservableListConverter.L2OL(PermissionAdapter.getAll()));
        new FXUtil_Autocomplete<>(_perm);
    }
}
