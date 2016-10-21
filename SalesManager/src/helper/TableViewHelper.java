package helper;

import application.AppSession;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	
	public static <A> Callback<TableColumn<A,String>, TableCell<A,String>> _getCellFactory(){
		return javafx.scene.control.cell.TextFieldTableCell.forTableColumn();
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
						Object item = cell.getTableRow().getItem();
						if (item != null)
							cellEvent.commit(item, checkBox.isSelected());
					}
				});
			    cell.setGraphic(checkBox);
			    cell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			    cell.setAlignment(Pos.CENTER);
			    return cell;
			}
		};
    }
	
	public static <A> Callback<TableColumn<A,Boolean>, TableCell<A,Boolean>> _getCheckBoxCellFactory(TableColumn<A,Boolean> column){
		return javafx.scene.control.cell.CheckBoxTableCell.forTableColumn(column);
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
						Object item = cell.getTableRow().getItem();
						if (item != null)
							cellEvent.commit(item, comboBox.getSelectionModel().getSelectedItem());
					}
				});
			    cell.setGraphic(comboBox);
			    cell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			    cell.setAlignment(Pos.CENTER);
			    return cell;
			}
		};
    }
	
	public static <A,B> Callback<TableColumn<A,B>, TableCell<A,B>> _getComboBoxCellFactory(ObservableList<B> OL){
		return javafx.scene.control.cell.ComboBoxTableCell.forTableColumn(OL);
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
				
				button.setContentDisplay(ContentDisplay.CENTER);
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
						Object item = cell.getTableRow().getItem();
						int index = cell.getTableRow().getIndex();
						if (item != null)
							cellEvent.commit(item, index);
					}
				});
			    cell.setGraphic(button);
			    cell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			    cell.setAlignment(Pos.CENTER);
			    return cell;
			}
		};
    }
}