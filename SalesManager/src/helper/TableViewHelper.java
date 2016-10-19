package helper;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
				return new TextFieldTableCell<A,B>(new StringConverter<B>() {
                    @Override
                    public String toString(B t) {
                        return t.toString();
                    }
                    @Override
                    public B fromString(String string) {
                        return (B)string;
                    }                                    
                });
			}
		};
    }
}