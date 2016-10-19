package view.handler;

import org.apache.log4j.Logger;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;

public class Details {
	private static final Logger logger = Logger.getLogger(Details.class);
	
	@FXML
    private JFXTextField _name;

    @FXML
    private JFXTextArea _des;

    @FXML
    private JFXTextField _address;

    @FXML
    void initialize() {
        assert _name != null : "fx:id=\"_name\" was not injected: check your FXML file 'Details.fxml'.";
        assert _des != null : "fx:id=\"_des\" was not injected: check your FXML file 'Details.fxml'.";
        assert _address != null : "fx:id=\"_address\" was not injected: check your FXML file 'Details.fxml'.";

    }
}
