package helper;

import java.util.stream.Stream;

import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Bounds;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Window;

public class FXUtil_Autocomplete<T> extends JFXComboBox<T>{

	private JFXComboBox<T> cmb;
	String filter = "";
	private ObservableList<T> originalItems;

	public FXUtil_Autocomplete(JFXComboBox<T> cmb) {
		this.cmb = cmb;
		originalItems = FXCollections.observableArrayList(cmb.getItems());
		cmb.setTooltip(new Tooltip());
		cmb.setOnKeyPressed(this::handleOnKeyPressed);
		cmb.setOnHidden(this::handleOnHiding);
	}

	public void handleOnKeyPressed(KeyEvent e) {
		ObservableList<T> filteredList = FXCollections.observableArrayList();
		KeyCode code = e.getCode();
		
		if (code.isLetterKey()) {
			filter += e.getText();
		}
		if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
			filter = filter.substring(0, filter.length() - 1);
			cmb.getItems().setAll(originalItems);
		}
		if (code == KeyCode.ESCAPE) {
			filter = "";
		}
		if (filter.length() == 0) {
			filteredList = originalItems;
			cmb.getTooltip().hide();
		} else {
			Stream<T> itens = cmb.getItems().stream();
			String nameitem = filter.toString().toLowerCase();
			itens.filter(el -> el.toString().toLowerCase().contains(nameitem)).forEach(filteredList::add);
			cmb.getTooltip().setText(nameitem);
			
			Bounds boundsInScene = cmb.localToScene(cmb.getBoundsInLocal());
			Window stage = cmb.getScene().getWindow();
			double posX = boundsInScene.getMinX() + 8;//stage.getX() + cmb.getBoundsInParent().getMinX();
			double posY = boundsInScene.getMinY() + 8;//stage.getY() + cmb.getBoundsInParent().getMinY();
			Font f = new Font("System", 13);

			cmb.getTooltip().show(stage, posX, posY);
			cmb.show();
		}
		cmb.getItems().setAll(filteredList);
	}

	public void handleOnHiding(Event e) {
		filter = "";
		cmb.getTooltip().hide();
		T s = cmb.getSelectionModel().getSelectedItem();
		cmb.getItems().setAll(originalItems);
		cmb.getSelectionModel().select(s);
	}

}