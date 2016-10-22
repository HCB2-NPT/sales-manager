package helper;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObservableListConverter {
	public static <T> ObservableList<T> L2OL(List<T> list){
		ObservableList<T> o = FXCollections.observableArrayList();
		for (T t : list) {
			o.add(t);
		}
		return o;
	}
}
