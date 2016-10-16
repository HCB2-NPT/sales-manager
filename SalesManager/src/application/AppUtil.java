package application;

import org.apache.log4j.Logger;
import config.AppConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AppUtil {
	private static final Logger logger = Logger.getLogger(AppUtil.class);
	
	public static <T> T callPane(String panePath){
		T root = null;
		if (panePath != null){
			try {
				root = (T) FXMLLoader.load(AppSession._resourceProvider.getResource(panePath));
			}catch(Exception e){
				logger.error("Can not create scene. Path: " + panePath);
				//e.printStackTrace();
			}
		}
		return root;
	}
	
	public static Stage callForm(String scenePath, String cssPath){
		Stage s = null;
		if (scenePath != null){
			Scene scene = new Scene((Pane)callPane(scenePath));
			if (cssPath != null)
				scene.getStylesheets().add(AppSession._resourceProvider.getResource(cssPath).toExternalForm());
			if (scene != null){
				s = new Stage();
				s.getIcons().add(new Image(AppSession._resourceProvider.getResource(AppConfig.APP_ICON).toExternalForm()));
				s.setScene(scene);
				s.setTitle(AppConfig.APP_NAME);
			}
		}
		return s;
	}
}
