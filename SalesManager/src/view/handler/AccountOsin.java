package view.handler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.*;

import application.AppSession;
import dao.hibernate_adapters.AccountAdapter;
import helper.List2ObList;
import helper.TableViewHelper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;

public class AccountOsin {
	@FXML
    private JFXTextField _name;

    @FXML
    private JFXTextField _username;

    @FXML
    private JFXComboBox<String> _perm;

    @FXML
    private TableView<pojo.Account> table;

    @FXML
    private TableColumn<pojo.Account, Integer> cId;

    @FXML
    private TableColumn<pojo.Account, String> cName;

    @FXML
    private TableColumn<pojo.Account, String> cUsername;

    @FXML
    private TableColumn<pojo.Account, String> cPerm;

    @FXML
    private TableColumn<pojo.Account, Boolean> cIsDeleted;

    @FXML
    private TableColumn<pojo.Account, String> cState;
    
    @FXML
    private Label __currentUser;

    @FXML
    private Label __DateNow;

    @FXML
    void refresh() {

    }

    @FXML
    void save() {

    }
    
    @FXML
    void add() {

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
        Timer t = new Timer();
        t.schedule(new TimerTask() {
			@Override
			public void run() {
				__DateNow.setText(new Date().toString());
			}
		}, 0, 3000);
        
        cId.setCellValueFactory(TableViewHelper.getPropertyValueFactory("accountId"));
        cName.setCellValueFactory(TableViewHelper.getPropertyValueFactory("name"));
        cName.setCellFactory(TableViewHelper.getCellFactory());
        cName.setOnEditCommit(
            new EventHandler<CellEditEvent<pojo.Account, String>>() {
                @Override
                public void handle(CellEditEvent<pojo.Account, String> t) {
                	pojo.Account i = (pojo.Account)t.getTableView().getItems().get(t.getTablePosition().getRow()); 
                    i.setName(t.getNewValue());
                    i.setEdited(true);
                    table.refresh();
                }
             }
        );
        cUsername.setCellValueFactory(TableViewHelper.getPropertyValueFactory("username"));
        cPerm.setCellValueFactory(TableViewHelper.getPropertyValueFactory("permFormat"));
        cIsDeleted.setCellValueFactory(TableViewHelper.getPropertyValueFactory("isDeleted"));
        cState.setCellValueFactory(TableViewHelper.getPropertyValueFactory("objectState"));
        table.setItems(List2ObList.L2OL(AccountAdapter.getAll()));
    }
}
