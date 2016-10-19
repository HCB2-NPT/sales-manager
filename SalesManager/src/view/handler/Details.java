package view.handler;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;

public class Details {
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
