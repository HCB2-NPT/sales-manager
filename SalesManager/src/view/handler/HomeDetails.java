package view.handler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeDetails {
	@FXML
    private Label _name;

    @FXML
    private Label _desc;

    @FXML
    private Label _addr;
    
    @FXML
    void initialize() {
        assert _name != null : "fx:id=\"_name\" was not injected: check your FXML file 'HomeDetails.fxml'.";
        assert _desc != null : "fx:id=\"_desc\" was not injected: check your FXML file 'HomeDetails.fxml'.";
        assert _addr != null : "fx:id=\"_addr\" was not injected: check your FXML file 'HomeDetails.fxml'.";

    }
    
    public void setName(String name){
    	_name.setText(name);
    }
    
    public void setDesc(String desc){
    	_desc.setText(desc);
    }
    
    public void setAddr(String addr){
    	_addr.setText(addr);
    }
}
