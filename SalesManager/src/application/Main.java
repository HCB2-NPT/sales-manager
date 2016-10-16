package application;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import config.AppConfig;
import dao.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.handler.Login;

public class Main extends Application {
	private static final Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		HibernateUtil.tryConnectDatabase();
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws InterruptedException {
		logger.info("Start Application.");
		primaryStage.close();
		Login.callLogin(true);
	}
	
	public static Stage callForm(String scenePath, String cssPath){
		Stage s = null;
		if (scenePath != null){
			try {
				BorderPane root = (BorderPane) FXMLLoader.load(AppSession._resourceProvider.getResource(scenePath));
				Scene scene = new Scene(root);
				if (cssPath != null)
					scene.getStylesheets().add(AppSession._resourceProvider.getResource(cssPath).toExternalForm());
				
				s = new Stage();
				s.getIcons().add(new Image(AppSession._resourceProvider.getResource(AppConfig.APP_ICON).toExternalForm()));
				s.setScene(scene);
				s.setTitle(AppConfig.APP_NAME);
			} catch(Exception e) {
				logger.error("Can not create form");
				e.printStackTrace();
			}
		}
		return s;
	}
}
