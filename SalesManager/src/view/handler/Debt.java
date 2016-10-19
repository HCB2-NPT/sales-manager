package view.handler;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Debt {
	@FXML
	private Accordion acc_menu;
	
	@FXML
	void initialize() {
	    assert acc_menu != null : "fx:id=\"acc_menu\" was not injected: check your FXML file 'Debt.fxml'.";
	    acc_menu.getPanes().get(0).setExpanded(true);
	}
}
