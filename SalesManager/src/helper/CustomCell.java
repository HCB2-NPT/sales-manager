package helper;

import com.jfoenix.controls.JFXButton;

import application.AppSession;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pojo.Item;

public class CustomCell extends TableCell<Item, Boolean> {
    final JFXButton cellButton = new JFXButton();
     
    public CustomCell(){
    cellButton.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) {
			MessageBox.Show(""+((TableRow<Item>)cellButton.getParent().getParent()).getIndex(), "test");
			}
		});
    }
    
    public CustomCell(TableView<Item> tb){
    	
    	ImageView icon = new ImageView();
    	Image img = new Image(AppSession._resourceProvider.getResource("../view/img/Delete-48 (Red).png").toExternalForm());
    	
    	icon.setImage(img);
    	icon.setFitWidth(24);
    	icon.setFitHeight(24);
    	cellButton.setGraphic(icon);
    	cellButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        cellButton.setOnAction(new EventHandler<ActionEvent>() {
    		
    		@Override
    		public void handle(ActionEvent arg0) {
    			tb.getItems().remove(((TableRow<Item>)cellButton.getParent().getParent()).getIndex());
    			tb.refresh();
    			//MessageBox.Show(""+((TableRow<Item>)cellButton.getParent().getParent()).getIndex(), "test");
    			}
    		});
        }
 
        //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(cellButton);
        }
    }
};