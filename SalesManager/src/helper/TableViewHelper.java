package helper;

import application.AppSession;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class TableViewHelper {
	public static <A,B> PropertyValueFactory<A,B> getPropertyValueFactory(String propertyName){
		return new PropertyValueFactory<A,B>(propertyName);
	}
	
	public static <A, B> Callback<TableColumn<A,B>, TableCell<A,B>> getCellFactory(){
    	return new Callback<TableColumn<A,B>, TableCell<A,B>>() {
			@Override
			public TableCell<A,B> call(TableColumn<A,B> param) {
				return new javafx.scene.control.cell.TextFieldTableCell<A,B>(new StringConverter<B>() {
                    @Override
                    public String toString(B t) {
                    	if (t == null)
                    		return "";
                        return t.toString();
                    }
                    @Override
                    public B fromString(String string) {
                    	if (string == null)
                    		return null;
                        return (B)string;
                    }                                    
                });
			}
		};
    }
	
	public static <A> Callback<TableColumn<A,Boolean>, TableCell<A,Boolean>> getCheckBoxCellFactory(ITableCellEvent cellEvent){
		return new Callback<TableColumn<A,Boolean>, TableCell<A,Boolean>>() {
			@Override
			public TableCell<A,Boolean> call(TableColumn<A,Boolean> param) {
				CheckBox checkBox = new CheckBox();
			    TableCell<A, Boolean> cell = new TableCell<A, Boolean>() {
			        @Override
			        public void updateItem(Boolean item, boolean empty) {
			        	super.updateItem(item, empty);
			        	checkBox.setVisible(!empty);
			        	if (item != null){
			        		this.setItem(item);
			        		checkBox.setSelected(item);
			        	}
			        }
			    };
			    checkBox.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try{
							cellEvent.commit(cell.getIndex(), checkBox.isSelected());
						}catch (Exception e){
						}
					}
				});
			    cell.setGraphic(checkBox);
			    cell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			    cell.setAlignment(Pos.CENTER);
			    return cell;
			}
		};
    }
	
	public static <A,B> Callback<TableColumn<A,B>, TableCell<A,B>> getComboBoxCellFactory(ObservableList<B> OL, ITableCellEvent cellEvent){
		return new Callback<TableColumn<A,B>, TableCell<A,B>>() {
			@Override
			public TableCell<A,B> call(TableColumn<A,B> param) {
				ComboBox<B> comboBox = new ComboBox<B>();
				comboBox.setItems(OL);
				comboBox.setMaxWidth(Integer.MAX_VALUE);
			    TableCell<A, B> cell = new TableCell<A, B>() {
			        @Override
			        public void updateItem(B item, boolean empty) {
			        	super.updateItem(item, empty);
			        	comboBox.setVisible(!empty);
			        	if (item != null){
			        		this.setItem(item);
			        		comboBox.getSelectionModel().select(item);
			        	}
			        }
			    };
			    comboBox.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try{
							cellEvent.commit(cell.getIndex(), comboBox.getSelectionModel().getSelectedItem());
						}catch (Exception e){
						}
					}
				});
			    cell.setGraphic(comboBox);
			    cell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			    cell.setAlignment(Pos.CENTER);
			    return cell;
			}
		};
    }
	
	public static <A> Callback<TableColumn<A,Boolean>, TableCell<A,Boolean>> getButtonCellFactory(String text, String pathImg, ITableCellEvent cellEvent){
		return new Callback<TableColumn<A,Boolean>, TableCell<A,Boolean>>() {
			@Override
			public TableCell<A,Boolean> call(TableColumn<A,Boolean> param) {
				Button button = new Button();
				if (text == null || !text.equals("")){
					button.setText(text);
				}
				if (text == null || !pathImg.equals("")){
					ImageView icon = new ImageView();
			    	icon.setImage(new Image(AppSession._resourceProvider.getResource(pathImg).toExternalForm()));
			    	icon.setFitWidth(24);
			    	icon.setFitHeight(24);
			    	button.setGraphic(icon);
				}
				
				button.setContentDisplay(ContentDisplay.LEFT);
		    	if (text != null && text.equals(""))
		    		button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		    	if (pathImg != null && pathImg.equals(""))
		    		button.setContentDisplay(ContentDisplay.TEXT_ONLY);
			    
		    	TableCell<A, Boolean> cell = new TableCell<A, Boolean>() {
			        @Override
			        public void updateItem(Boolean item, boolean empty) {
			        	super.updateItem(item, empty);
			        	button.setVisible(false);
			        	if (item != null){
			        		button.setVisible(item);
			        		this.setItem(item);
			        	}
			        }
			    };
			    button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try{
							cellEvent.commit(cell.getIndex(), null);
						}catch (Exception e){
						}
					}
				});
			    cell.setGraphic(button);
			    cell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			    cell.setAlignment(Pos.CENTER);
			    return cell;
			}
		};
    }
	
	public static <A> Callback<TableColumn<A,String>, TableCell<A,String>> getButtonTextCellFactory(String text, String pathImg, ITableCellEvent cellEvent){
		return new Callback<TableColumn<A,String>, TableCell<A,String>>() {
			@Override
			public TableCell<A,String> call(TableColumn<A,String> param) {
				TextField tf = new TextField();
				tf.setPrefWidth(Integer.MAX_VALUE);
				
				Button button = new Button();
				if (text == null || !text.equals("")){
					button.setText(text);
				}
				if (text == null || !pathImg.equals("")){
					ImageView icon = new ImageView();
			    	icon.setImage(new Image(AppSession._resourceProvider.getResource(pathImg).toExternalForm()));
			    	icon.setFitWidth(24);
			    	icon.setFitHeight(24);
			    	button.setGraphic(icon);
				}
				
				button.setContentDisplay(ContentDisplay.CENTER);
		    	if (text != null && text.equals(""))
		    		button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		    	if (pathImg != null && pathImg.equals(""))
		    		button.setContentDisplay(ContentDisplay.TEXT_ONLY);
		    	button.setPrefWidth(Region.USE_PREF_SIZE);
		    	
		    	GridPane grid = new GridPane();
		    	grid.setHgap(4);
		    	grid.add(tf, 0, 0);
		    	grid.add(button, 1, 0);
		    	GridPane.setHalignment(tf, HPos.CENTER);
		    	
		    	AnchorPane out = new AnchorPane();
		    	out.getChildren().add(grid);
		    	AnchorPane.setLeftAnchor(grid, 0d);
		    	AnchorPane.setRightAnchor(grid, 0d);
			    
		    	TableCell<A, String> cell = new TableCell<A, String>() {
			        @Override
			        public void updateItem(String item, boolean empty) {
			        	super.updateItem(item, empty);
			        	button.setVisible(!empty);
			        	if (item != null){
			        		this.setItem(item);
			        		tf.setText(item);
			        	}else{
			        		tf.setText("");
			        	}
			        }
			    };
			    button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try{
							cellEvent.commit(cell.getIndex(), null);
						}catch (Exception e){
						}
					}
				});
			    tf.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
			    		try{
							cellEvent.commit(cell.getIndex(), tf.getText());
						}catch (Exception e){
						}
			    	};
				});
			    cell.setGraphic(out);
			    cell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			    cell.setAlignment(Pos.CENTER);
			    return cell;
			}
		};
    }
}